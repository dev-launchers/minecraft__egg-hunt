package devlaunchers.eggeconomy.dropevents;

import devlaunchers.eggeconomy.EggHunt;
import devlaunchers.eggeconomy.dropevents.monitoring.EggDropMonitor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakEggDropper implements Listener {

    private EggDropMonitor eggDropMonitor = new EggDropMonitor();
    private DropStrategy dropStrategy;

    public BlockBreakEggDropper(DropStrategy dropChanceStrategy) {
        this.dropStrategy = dropChanceStrategy;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!e.getPlayer().getWorld().getName().equals(EggHunt.WORLD_NAME))  return;

        Block block = e.getBlock();
        Material material = block.getType();
        Player player = e.getPlayer();
        Location location = block.getLocation();

        DropRule dropRule = dropStrategy.getDropRuleByType(material);
        if (dropStrategy.checkShouldDrop(block.getType()) && eggDropMonitor.canDrop(location, dropRule) && !isHoldingSilkTouch(player)) {
            ItemStack eggItem = EggHunt.getItemUtil().getEggItem().clone();
            if (isHoldingFortune(player)) eggItem.setAmount(2);
            location.getWorld().dropItemNaturally(location, eggItem);

            eggDropMonitor.eggDropped(location, dropRule); // Log this egg drop to prevent abuse
        }
    }

    public boolean isHoldingSilkTouch(Player player) {
        if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            return true;
        }
        return false;
    }

    public boolean isHoldingFortune(Player player) {
        if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            return true;
        }
        return false;
    }
}
