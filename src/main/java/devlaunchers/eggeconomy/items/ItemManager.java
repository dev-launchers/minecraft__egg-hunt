package devlaunchers.eggeconomy.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    private static ItemStack eggItem;

    public ItemManager() {
        initEggItem();
    }

    public static ItemStack getEggItem() {
        return eggItem;
    }


    private void initEggItem() {
        // Our custom variable which we will be changing around.
        ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);

        // The meta of the paper where we can change the name, and properties of the item.
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.setDisplayName(ChatColor.YELLOW + "Golden Egg");

        // Set some lore
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE+"Collect the most");
        lore.add(ChatColor.WHITE+"golden eggs to");
        lore.add(ChatColor.WHITE+"get Discord Nitro");
        lore.add(ChatColor.WHITE+"and a legendary");
        lore.add(ChatColor.WHITE+"item!");
        meta.setLore(lore);

        // Now add some flags
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        // Add enchantments
        //item.addEnchantment(Enchantment.DAMAGE_ALL, 5);


        // Set the meta of the sword to the edited meta.
        item.setItemMeta(meta);

        this.eggItem = item;
    }

}
