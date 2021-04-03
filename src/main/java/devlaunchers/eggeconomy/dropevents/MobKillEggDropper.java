package devlaunchers.eggeconomy.dropevents;

import devlaunchers.eggeconomy.EggHunt;
import devlaunchers.eggeconomy.dropevents.monitoring.EggDropMonitor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobKillEggDropper implements Listener {

    private EggDropMonitor eggDropMonitor = new EggDropMonitor();
    private DropStrategy dropStrategy;

    public MobKillEggDropper(DropStrategy dropChanceStrategy) {
        this.dropStrategy = dropChanceStrategy;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!e.getEntity().getWorld().getName().equals(EggHunt.WORLD_NAME))  return;

        Entity entity = e.getEntity();
        EntityType entityType = entity.getType();
        Location location = entity.getLocation();
        Entity killer = e.getEntity().getKiller();

        DropRule dropRule = dropStrategy.getDropRuleByType(entityType);
        if (killer instanceof Player &&
            dropStrategy.checkShouldDrop(entity.getType()) &&
            eggDropMonitor.canDrop(location, dropRule)) {
                ItemStack eggItem = EggHunt.getItemUtil().getEggItem().clone();
                location.getWorld().dropItemNaturally(location, eggItem);

                eggDropMonitor.eggDropped(location, dropRule); // Log this egg drop to prevent abuse
        }
    }
}
