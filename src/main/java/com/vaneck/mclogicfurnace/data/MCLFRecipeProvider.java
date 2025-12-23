package com.vaneck.mclogicfurnace.data;

import java.util.function.Consumer;
import com.vaneck.mclogicfurnace.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

public class MCLFRecipeProvider extends FabricRecipeProvider {

    public MCLFRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.NFURNACE_BLOCK, 1)
                .pattern("ccc")
                .pattern("c c")
                .pattern("cnc")
                .input('c', ItemTags.STONE_CRAFTING_MATERIALS)
                .input('n', Items.NETHERRACK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.COBBLESTONE), FabricRecipeProvider.conditionsFromItem(Items.NETHERRACK))
                .offerTo(consumer);
    }
}
