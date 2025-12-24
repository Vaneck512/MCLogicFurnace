package com.vaneck.mclogicfurnace.client;

import com.vaneck.mclogicfurnace.data.MCLFBlockLootTableProvider;
import com.vaneck.mclogicfurnace.data.MCLFBlockTagProvider;
import com.vaneck.mclogicfurnace.data.MCLFRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MCLogicFurnaceDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(MCLFRecipeProvider::new);
        pack.addProvider(MCLFBlockLootTableProvider::new);
        pack.addProvider(MCLFBlockTagProvider::new);
    }
}
