package com.vaneck.mclogicfurnace.block;

import com.vaneck.mclogicfurnace.ModBlockEntities;
import com.vaneck.mclogicfurnace.ModBlocks;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NFurnaceBlock extends AbstractFurnaceBlock {
    public NFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof NFurnaceBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
            player.incrementStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NFurnaceBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        NFurnaceBlockEntity blockEntity = (NFurnaceBlockEntity) world.getBlockEntity(blockPos);
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        boolean correctItem = (item instanceof ShovelItem || item instanceof FireChargeItem || item instanceof FlintAndSteelItem);
        boolean shovelNLit = (item instanceof ShovelItem) != blockEntity.isActive();
        boolean flintNLit = (item instanceof FlintAndSteelItem) == blockEntity.isActive();
        boolean fireNLit = (item instanceof FireChargeItem) == blockEntity.isActive();

        if (shovelNLit && !correctItem) return cancel(blockState, world, blockPos, player, hand, hit);
        else if (flintNLit || fireNLit && !correctItem) return cancel(blockState, world, blockPos, player, hand, hit);

        SoundEvent sound = (item instanceof FireChargeItem)
                ? SoundEvents.ITEM_FIRECHARGE_USE : (item instanceof FlintAndSteelItem)
                ? SoundEvents.ITEM_FLINTANDSTEEL_USE : SoundEvents.BLOCK_FIRE_EXTINGUISH;

        world.playSound(player, blockPos, sound, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);

        blockEntity.setActive(!blockState.get(Properties.LIT));
        world.setBlockState(blockPos, blockState.with(Properties.LIT, blockEntity.isActive()), 11);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
        itemStack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));
        return ActionResult.success(true);
    }

    ActionResult cancel(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        super.onUse(blockState, world, blockPos, player, hand, hit);
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.NFURNACE_BLOCK_ENTITY, NFurnaceBlockEntity::tick);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("tooltip.mclogicfurnace.tooltip1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("tooltip.mclogicfurnace.tooltip2").formatted(Formatting.GRAY));
    }

}

