package com.vaneck.mclogicfurnace.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AbstractFurnaceScreen.class)
public class AbstractFurnaceScreenMixin {

    public AbstractFurnaceScreen Self() {
        return (AbstractFurnaceScreen) (Object) this;
    }

    @ModifyVariable(at = @At(value = "STORE"), method = "drawBackground", ordinal = 3)
    private int drawBackground(int value) {
//        if (Self().getScreenHandler() instanceof AbstractFurnaceScreenHandler screenHandler) {
//            screenHandler.
//        }
        return value;
    }

}
