package com.vaneck.mclogicfurnace;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MCLogicFurnace implements ModInitializer {

    public static final String MODID = "mclogicfurnace";

    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MCLogicFurnace.MODID, "def_item_group"))
        .icon(() -> new ItemStack(ModBlocks.NFURNACE_BLOCK))
        .build();

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        ModBlockEntities.initialize();
    }
}
