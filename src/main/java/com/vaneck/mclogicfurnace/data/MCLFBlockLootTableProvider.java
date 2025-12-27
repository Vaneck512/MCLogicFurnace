package com.vaneck.mclogicfurnace.data;

import com.vaneck.mclogicfurnace.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class MCLFBlockLootTableProvider extends FabricBlockLootTableProvider {

    public MCLFBlockLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockLootTables() {
        addDrop(ModBlocks.NFURNACE_BLOCK);
    }
}
