package devlaunchers.eggeconomy.items;

import devlaunchers.eggeconomy.EggHunt;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;

public class RecipeManager {

    public RecipeManager() {
        //initDiamondToEggRecipe();
        //initEggToDiamondRecipe();
    }

    private void initRecipe(String recipeKey, ItemStack result, String[] recipeShape, HashMap<String, ItemStack> ingredientItemKeys) {
        // create a NamespacedKey for your recipe
        NamespacedKey key = new NamespacedKey(EggHunt.getInstance(), recipeKey);

        // Create our custom recipe variable
        ShapedRecipe recipe = new ShapedRecipe(key, result);

        // Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
        recipe.shape(recipeShape[0], recipeShape[1], recipeShape[2]);

        // Set what the letters represent.
        for (String itemKey : ingredientItemKeys.keySet()) {
            recipe.setIngredient(itemKey.charAt(0), ingredientItemKeys.get(itemKey));
        }

        // Finally, add the recipe to the bukkit recipes
        EggHunt.getInstance().getServer().addRecipe(recipe);
    }

    /*
    private void initDiamondToEggRecipe() {
        ItemStack craftingResult = ItemManager.getEggItem().clone();
        craftingResult.setAmount(9);
        initRecipe(
                "egg",                                 // Recipe internal name
                craftingResult,                                 // Resulting item
                new String[]{                                   // Crafting shape
                        "   ",
                        " D ",
                        "   "
                },
                new HashMap<String, ItemStack>() {{              // Key value pair corresponding to shape entries
                    put("D", new ItemStack(Material.DIAMOND));
                }}
        );

        System.out.println("Initialized Diamond to Egg Recipe!");
    }
    */
}
