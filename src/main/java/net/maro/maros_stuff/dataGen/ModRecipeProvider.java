package net.maro.maros_stuff.dataGen;

import net.maro.maros_stuff.MarosStuff;
import net.maro.maros_stuff.block.ModBlocks;
import net.maro.maros_stuff.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        //shaped recipes ------------------------------------------------------------------------------------------------------------------------------------------
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOCK.get())
                .pattern("R")
                .pattern("B")
                .define('R', Items.RED_WOOL)
                .define('B', Items.BLACK_WOOL)
                .unlockedBy(getHasName(Items.RED_WOOL), has(Items.BLACK_WOOL))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REDSTONE_ALLOY_BLOCK.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.REDSTONE_ALLOY_INGOT.get())
                .unlockedBy(getHasName(ModItems.REDSTONE_ALLOY_INGOT.get()), has(ModItems.REDSTONE_ALLOY_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIAMONDED_QUARTZ_BLOCK.get())
                .pattern("II")
                .pattern("II")
                .define('I', ModItems.DIAMONDED_QUARTZ.get())
                .unlockedBy(getHasName(ModItems.DIAMONDED_QUARTZ.get()), has(ModItems.DIAMONDED_QUARTZ.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENDERIC_ACID_CHUNK.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.ENDERIC_ACID.get())
                .unlockedBy(getHasName(ModItems.ENDERIC_ACID.get()), has(ModItems.ENDERIC_ACID.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENDER_ALLOY_BLOCK.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.ENDER_ALLOY_INGOT.get())
                .unlockedBy(getHasName(ModItems.ENDER_ALLOY_INGOT.get()), has(ModItems.ENDER_ALLOY_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.END_WAND.get())
                .pattern("APA")
                .pattern("AIA")
                .pattern(" I ")
                .define('I', ModItems.ENDER_ALLOY_INGOT.get())
                .define('A', ModItems.ENDERIC_ACID.get())
                .define('P', Items.ENDER_PEARL)
                .unlockedBy(getHasName(ModItems.ENDER_ALLOY_INGOT.get()), has(ModItems.ENDER_ALLOY_INGOT.get()))
                //pro více receptů na jednu věc je potřeba new ResourceLocation
                .save(pWriter, new ResourceLocation(MarosStuff.MOD_ID,"end_wand_crafting"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALLOYER_MK1.get())
                .pattern("III")
                .pattern("IBI")
                .pattern("PPP")
                .define('I', Items.IRON_INGOT)
                .define('B', Items.BLAST_FURNACE)
                .define('P', Items.POLISHED_DEEPSLATE)
                .unlockedBy(getHasName(Items.BLAST_FURNACE), has(Items.BLAST_FURNACE))
                .save(pWriter);

        //shaplessrecipes ------------------------------------------------------------------------------------------------------------------------------------------
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REDSTONE_ALLOY_INGOT.get(), 9)
                .requires(ModBlocks.REDSTONE_ALLOY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.REDSTONE_ALLOY_BLOCK.get()), has(ModBlocks.REDSTONE_ALLOY_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DIAMONDED_QUARTZ.get(), 4)
                .requires(ModBlocks.DIAMONDED_QUARTZ_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.DIAMONDED_QUARTZ_BLOCK.get()), has(ModBlocks.DIAMONDED_QUARTZ_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ENDER_ALLOY_INGOT.get(), 9)
                .requires(ModBlocks.ENDER_ALLOY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ENDER_ALLOY_BLOCK.get()), has(ModBlocks.ENDER_ALLOY_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.END_WAND.get())
                .requires(ModItems.END_WAND_EMPTY.get())
                .requires(ModItems.ENDERIC_ACID.get(),4)
                .unlockedBy(getHasName(ModItems.END_WAND_EMPTY.get()), has(ModItems.END_WAND_EMPTY.get()))
                .save(pWriter, new ResourceLocation(MarosStuff.MOD_ID,"end_wand_repair_acid"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.END_WAND.get())
                .requires(ModItems.END_WAND_EMPTY.get())
                .requires(Items.ENDER_PEARL)
                .unlockedBy(getHasName(ModItems.END_WAND_EMPTY.get()), has(ModItems.END_WAND_EMPTY.get()))
                .save(pWriter, new ResourceLocation(MarosStuff.MOD_ID,"end_wand_repair_pearl"));
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  MarosStuff.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}