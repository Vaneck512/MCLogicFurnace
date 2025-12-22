package com.vaneck.mclogicfurnace.mixin;

import com.vaneck.mclogicfurnace.block.NFurnaceBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ShovelItem.class, FireChargeItem.class, FlintAndSteelItem.class})
public class LitOrExtinguishMixin {

    @Inject(at = @At("HEAD"), method = "useOnBlock")
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        Hand hand = context.getHand();

        assert playerEntity != null;
        ItemStack itemStack = playerEntity.getStackInHand(hand);

        if (world.getBlockEntity(blockPos) instanceof NFurnaceBlockEntity blockEntity && blockState.get(Properties.LIT) == (itemStack.getItem() instanceof ShovelItem)) {

            if ( itemStack.getItem() instanceof ShovelItem ) {
                if (!blockState.get(Properties.LIT))
                    return;
            }
            else if (itemStack.getItem() instanceof FireChargeItem | itemStack.getItem() instanceof FlintAndSteelItem) {
                if (blockState.get(Properties.LIT))
                    return;
            }

            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
            blockEntity.setActive(!blockState.get(Properties.LIT));
            world.emitGameEvent(playerEntity, GameEvent.BLOCK_CHANGE, blockPos);
            context.getStack().damage(1, playerEntity, (p) -> p.sendToolBreakStatus(context.getHand()));
        }
    }
}
