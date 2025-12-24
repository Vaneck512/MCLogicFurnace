package com.vaneck.mclogicfurnace.data;

import com.vaneck.mclogicfurnace.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class MCLFBlockLootTableProvider extends FabricBlockLootTableProvider {
    public MCLFBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.NFURNACE_BLOCK);
    }
}
