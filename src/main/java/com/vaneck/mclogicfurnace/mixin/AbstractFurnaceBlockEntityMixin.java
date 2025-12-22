package com.vaneck.mclogicfurnace.mixin;

import com.vaneck.mclogicfurnace.ModBlocks;
import com.vaneck.mclogicfurnace.block.NFurnaceBlockEntity;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {


    @Shadow
    int burnTime;

    @Shadow
    @Final
    protected PropertyDelegate propertyDelegate;

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

    @Inject(at = @At("RETURN"), method = "getFuelTime", cancellable = true)
    private void getFuelTime(CallbackInfoReturnable<Integer> cir) {
        World world = Self().getWorld();
        if (world == null) return;
        if (world.getBlockEntity(Self().getPos()) instanceof NFurnaceBlockEntity blockEntity) {
            this.burnTime = 512;
            this.propertyDelegate.set(0, 1600);
            this.propertyDelegate.set(1, 1600);
            blockEntity.markDirty();
            System.out.println("set");
            cir.setReturnValue(512) ;
        }
    }

    @Inject(at = @At("RETURN"), method = "getFuelTime")
    protected void getFuelTime(ItemStack fuel, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(20000); //test
    }
}
