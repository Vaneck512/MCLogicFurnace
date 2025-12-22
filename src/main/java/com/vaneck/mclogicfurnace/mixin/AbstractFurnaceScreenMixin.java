package com.vaneck.mclogicfurnace.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceScreen.class)
public class AbstractFurnaceScreenMixin {

    public AbstractFurnaceScreen<AbstractFurnaceScreenHandler> Self() {
        return (AbstractFurnaceScreen) (Object) this;
    }

    @Final
    @Shadow
    private Identifier background;


    @Inject(at = @At(value = "HEAD"), method = "drawBackground")
    private void drawBackground(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {

        HandledScreenAccessor accessor = ((HandledScreenAccessor)this);

        int i = accessor.getX();
        int j = accessor.getY();
        context.drawTexture(background, i, j, 0, 0, accessor.backgroundWidth(), accessor.backgroundHeight());
//        context.drawTexture(background, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
    }

}
