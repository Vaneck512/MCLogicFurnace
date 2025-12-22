package com.vaneck.mclogicfurnace.mixin;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HandledScreen.class)
public interface HandledScreenAccessor {
    @Accessor("x")
    int getX();

    @Accessor("y")
    int getY();

    @Accessor("backgroundWidth")
    int backgroundWidth();

    @Accessor("backgroundHeight")
    int backgroundHeight();

    @Accessor("handler")
    ScreenHandler getHandler();
}
