package net.gendevo.stardewarmory.data.client;

import net.gendevo.stardewarmory.StardewArmory;
import net.gendevo.stardewarmory.setup.ModBlocks;
import net.gendevo.stardewarmory.setup.ModItems;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;


import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        // Crafting Recipes
        ShapelessRecipeBuilder.shapeless(ModItems.IRIDIUM_INGOT.get(), 9)
                .requires(ModBlocks.IRIDIUM_BLOCK.get())
                .unlockedBy("has_item", has(ModItems.IRIDIUM_INGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.IRIDIUM_BLOCK.get())
                .define('#', ModItems.IRIDIUM_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ModItems.IRIDIUM_INGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.GALAXY_SWORD.get())
                .define('#', ModItems.PRISMATIC_SHARD.get())
                .define('s', ModItems.REINFORCED_HANDLE.get())
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" s ")
                .unlockedBy("has_item", has(ModItems.PRISMATIC_SHARD.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.OSSIFIED_BLADE.get())
                .define('#', ModItems.BONE_SWORD.get())
                .define('p', ModItems.PREHISTORIC_SCAPULA.get())
                .define('f', ModItems.FEMUR.get())
                .pattern(" # ")
                .pattern(" p ")
                .pattern(" f ")
                .unlockedBy("has_item", has(ModItems.PREHISTORIC_SCAPULA.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.IRON_EDGE.get())
                .define('#', Items.IRON_INGOT)
                .define('n', Items.IRON_NUGGET)
                .define('s', Items.STICK)
                .pattern(" # ")
                .pattern("n#n")
                .pattern(" s ")
                .unlockedBy("has_item", has(Items.IRON_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_EDGE.get())
                .define('#', Items.OBSIDIAN)
                .define('s', Items.STICK)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" s ")
                .unlockedBy("has_item", has(Items.OBSIDIAN))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.CRYSTAL_DAGGER.get())
                .define('q', Items.QUARTZ)
                .define('s', Items.STICK)
                .pattern("   ")
                .pattern(" q ")
                .pattern("s  ")
                .unlockedBy("has_item", has(Items.QUARTZ))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.IRON_DIRK.get())
                .define('i', Items.IRON_INGOT)
                .define('s', Items.STICK)
                .pattern("   ")
                .pattern(" i ")
                .pattern("s  ")
                .unlockedBy("has_item", has(Items.IRON_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.WOOD_MALLET.get())
                .define('w', Items.OAK_PLANKS)
                .define('s', Items.STICK)
                .pattern(" ws")
                .pattern(" sw")
                .pattern("s  ")
                .unlockedBy("has_item", has(Items.STICK))
                .save(consumer);
        //Cooking recipes
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.IRIDIUM_ORE.get()), ModItems.IRIDIUM_INGOT.get(), 1.5f, 200)
                .unlockedBy("has_item", has(ModBlocks.IRIDIUM_ORE.get()))
                .save(consumer, modId("iridium_ingot_smelting"));
        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.IRIDIUM_ORE.get()), ModItems.IRIDIUM_INGOT.get(), 1.5f, 100)
                .unlockedBy("has_item", has(ModBlocks.IRIDIUM_ORE.get()))
                .save(consumer, modId("iridium_ingot_blasting"));
        // Smithing recipes
        SmithingRecipeBuilder.smithing(Ingredient.of(Items.STICK), Ingredient.of(Items.NETHERITE_INGOT), ModItems.REINFORCED_HANDLE.get())
                .unlocks("has_item", has(Items.NETHERITE_INGOT))
                .save(consumer, modId("reinforced_handle_smithing"));
        SmithingRecipeBuilder.smithing(Ingredient.of(ModItems.CUTLASS.get()), Ingredient.of(ModItems.DRAGON_TOOTH.get()), ModItems.DRAGONTOOTH_CUTLASS.get())
                .unlocks("has_item", has(ModItems.DRAGON_TOOTH.get()))
                .save(consumer, modId("dragontooth_cutlass_smithing"));
        SmithingRecipeBuilder.smithing(Ingredient.of(ModItems.PIRATES_SWORD.get()), Ingredient.of(ModItems.DRAGON_TOOTH.get()), ModItems.DRAGONTOOTH_CUTLASS.get())
                .unlocks("has_item", has(ModItems.DRAGON_TOOTH.get()))
                .save(consumer, modId("dragontooth_pirate_smithing"));
        SmithingRecipeBuilder.smithing(Ingredient.of(ModItems.BURGLARS_SHANK.get()), Ingredient.of(ModItems.DRAGON_TOOTH.get()), ModItems.DRAGONTOOTH_SHIV.get())
                .unlocks("has_item", has(ModItems.DRAGON_TOOTH.get()))
                .save(consumer, modId("dragontooth_shiv_smithing"));
        SmithingRecipeBuilder.smithing(Ingredient.of(ModItems.WOOD_CLUB.get()), Ingredient.of(ModItems.DRAGON_TOOTH.get()), ModItems.DRAGONTOOTH_CLUB.get())
                .unlocks("has_item", has(ModItems.DRAGON_TOOTH.get()))
                .save(consumer, modId("dragontooth_club_smithing"));
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(StardewArmory.MOD_ID, path);
    }
}
