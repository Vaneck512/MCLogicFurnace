package com.vaneck.mclogicfurnace.client;

import com.vaneck.mclogicfurnace.data.MCLFBlockLootTableProvider;
import com.vaneck.mclogicfurnace.data.MCLFBlockTagProvider;
import com.vaneck.mclogicfurnace.data.MCLFRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MCLogicFurnaceDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(MCLFRecipeProvider::new);
        fabricDataGenerator.addProvider(MCLFBlockLootTableProvider::new);
        fabricDataGenerator.addProvider(MCLFBlockTagProvider::new);
    }
}
