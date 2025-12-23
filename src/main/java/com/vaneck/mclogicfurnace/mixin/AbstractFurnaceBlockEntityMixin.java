package com.vaneck.mclogicfurnace.mixin;

import com.vaneck.mclogicfurnace.ModBlocks;
import com.vaneck.mclogicfurnace.block.NFurnaceBlockEntity;
import net.minecraft.advancement.Advancement;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    @Unique
    public AbstractFurnaceBlockEntity Self() {
        return (AbstractFurnaceBlockEntity) (Object) this;
    }

    @Inject(at = @At("HEAD"), method = "isBurning", cancellable = true)
    private void isBurning(CallbackInfoReturnable<Boolean> cir) {
        World world = Self().getWorld();
        BlockPos pos = Self().getPos();
        if (Self().getCachedState().isOf(ModBlocks.NFURNACE_BLOCK)) {
            assert world != null;
            if (world.getBlockEntity(pos) instanceof NFurnaceBlockEntity blockEntity) {
                world.setBlockState(pos, Self().getCachedState().with(AbstractFurnaceBlock.LIT, blockEntity.isActive()));
                cir.setReturnValue(blockEntity.isActive());
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private static void tick_(World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci) {
        if (blockEntity instanceof NFurnaceBlockEntity nfurnaceBlockEntity) {
            AbsFurnBEAccessor accessor = (AbsFurnBEAccessor) blockEntity;
            if (nfurnaceBlockEntity.isActive()) {
                accessor.setFuelTime(512);
            } else {
                accessor.setFuelTime(0);
            }
            accessor.setBurnTime(accessor.getFuelTime());

            if (!nfurnaceBlockEntity.getInventory().get(1).isEmpty()) {
                String advancement_ = "mclogicfurnace/need_more_fuel";
                String criterion = "mclogicfurnace:fuel";
                if (world == null || world.isClient()) return;

                for (PlayerEntity player : world.getPlayers()) {
                    BlockPos playerPos = player.getBlockPos();
                    if (playerPos.getManhattanDistance(pos) <= 5) {
                        Advancement advancement =
                                Objects.requireNonNull(player.getServer()).
                                        getAdvancementLoader().get(Identifier.of("minecraft", advancement_));

                        ((ServerPlayerEntity) player).getAdvancementTracker().grantCriterion(advancement, criterion);
                    }
                }
            }
        }
    }
}
