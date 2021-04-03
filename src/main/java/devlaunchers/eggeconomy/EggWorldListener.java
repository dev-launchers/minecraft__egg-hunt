package devlaunchers.eggeconomy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;

public class EggWorldListener implements Listener {

    private boolean isEggWorld(World world) {
        return world.getName().equals("egg-world");
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();
        World world = player.getWorld();
        if (isEggWorld(world)) {
            insertEnderChestIntoPlayerInventory(player);

            player.sendMessage("Welcome to Egg Hunt! " +
                    ChatColor.GOLD+"Have  the most GOLDEN EGGS in your Ender Chest by the end of the weekend! Collect Golden Eggs by: \n\n" +
                    ChatColor.GREEN+" - Defeating mobs\n" +
                    ChatColor.GREEN+" - Exploring to find chests\n" +
                    ChatColor.GREEN+" - Mining valuable materials" +
                    "\n\n" +
                    ChatColor.YELLOW+"If you'd like to return home, simply type '/tpmain' in your chat bar!");
        }
    }

    private void teleportPlayerToSpawn(Player player) {
        player.teleport(player.getWorld().getSpawnLocation());
    }

    private void clearPlayerInventory(Player player) {
        player.getInventory().setContents(new ItemStack[0]);
    }

    private void setPlayerHealthFull(Player player) {
        System.out.println("SETTING MAX HEALTH");
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        player.setHealth(maxHealth);
    }

    private void setPlayerFoodLevelFull(Player player) {
        System.out.println("SETTING MAX FOOD");
        player.setFoodLevel(20);
    }

    private void insertEnderChestIntoPlayerInventory(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ENDER_CHEST));
    }

    private void setPlayerActionBar(Player player) {
        player.sendActionBar("STRING");
        //player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("TESTING ACTION BAR"));
    }
}