package com.vaneck.mclogicfurnace;

import com.vaneck.mclogicfurnace.block.NFurnaceBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.vaneck.mclogicfurnace.MCLogicFurnace.MODID;

public final class ModBlocks {

    public static Block NFURNACE_BLOCK = register("netherrack_furnace", new NFurnaceBlock(AbstractBlock.Settings.copy(Blocks.FURNACE)));

    private static <T extends Block> T register(String path, T block) {
        Registry.register(Registries.BLOCK, Identifier.of(MODID, path), block);
        Registry.register(Registries.ITEM, Identifier.of(MODID, path), new BlockItem(block, new Item.Settings()));

        return block;
    }

    public static void initialize() {
    }
}
