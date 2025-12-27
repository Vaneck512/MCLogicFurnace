package com.vaneck.mclogicfurnace.data;

import com.vaneck.mclogicfurnace.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class MCLFBlockTagProvider extends FabricTagProvider.BlockTagProvider {


    public MCLFBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(ModBlocks.NFURNACE_BLOCK);
    }
}
