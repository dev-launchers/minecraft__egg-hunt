package devlaunchers.eggeconomy.dropevents.monitoring;

import devlaunchers.eggeconomy.dropevents.DropRule;
import org.bukkit.Location;

public class EggDropMonitor {

    // When a egg is dropped, log the:
    //   - Location of the drop
    //   - time of the drop
    //   - The player who caused the drop?

    // When deciding if we should drop another egg:
    //   - Check if a egg has been dropped in this location in the last 5 minutes

    private EggDropLog eggDropLog = new EggDropLog();

    public EggDropMonitor() {}

    public boolean canDrop(Location location, DropRule dropRule) {
        return !eggDropLog.locationRangeExists(location, dropRule.getProtectionRadius());
    }

    public void eggDropped(Location location, DropRule dropRule) {
        eggDropLog.addEggDropEntry(location, dropRule);
    }
}

