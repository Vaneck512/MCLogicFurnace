package com.vaneck.mclogicfurnace.data;

import com.vaneck.mclogicfurnace.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;

import java.util.function.Consumer;


public class MCLFRecipeProvider extends FabricRecipeProvider {


    public MCLFRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateRecipes(Consumer<RecipeJsonProvider> consumer) {
        ShapedRecipeJsonBuilder.create(ModBlocks.NFURNACE_BLOCK)
                .pattern("ccc")
                .pattern("c c")
                .pattern("cnc")
                .input('c', ItemTags.STONE_CRAFTING_MATERIALS)
                .input('n', Items.NETHERRACK)
//                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.COBBLESTONE), FabricRecipeProvider.conditionsFromItem(Items.NETHERRACK))
                .offerTo(consumer);
    }
}
