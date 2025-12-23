package com.vaneck.mclogicfurnace.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface AbsFurnBEAccessor {
    @Accessor("fuelTime")
    int getFuelTime();

    @Accessor("fuelTime")
    void setFuelTime(int fuelTime);

    @Accessor("burnTime")
    void setBurnTime(int burnTime);

}
