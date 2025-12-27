package com.vaneck.mclogicfurnace;

import com.vaneck.mclogicfurnace.block.NFurnaceBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.vaneck.mclogicfurnace.MCLogicFurnace.MODID;

public final class ModBlocks {

    public static Block NFURNACE_BLOCK = register("netherrack_furnace",
            new Item.Settings().rarity(Rarity.EPIC).group(MCLogicFurnace.CUSTOM_ITEM_GROUP),
            new NFurnaceBlock(AbstractBlock.Settings.copy(Blocks.FURNACE))
    );

    private static <T extends Block> T register(String path, Item.Settings settings, T block) {
        Registry.register(Registry.BLOCK, Identifier.of(MODID, path), block);
        Registry.register(Registry.ITEM, Identifier.of(MODID, path), new BlockItem(block, settings));

        return block;
    }

    public static void initialize() {
    }
}
