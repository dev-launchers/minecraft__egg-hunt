package devlaunchers.eggeconomy.dropevents.monitoring;

import devlaunchers.eggeconomy.EggHunt;
import devlaunchers.eggeconomy.dropevents.DropRule;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Date;

class EggDropLog {

    final int TRIM_INTERVAL = 1000 * 60; // In milliseconds

    private ArrayList<EggDropLogEntry> logEntries = new ArrayList<EggDropLogEntry>();

    public EggDropLog() {
        beginTrimCheckTimer();
    }

    public void addEggDropEntry(Location location, DropRule dropRule) {
        logEntries.add(logEntries.size(), new EggDropLogEntry(location, dropRule)); // appends to the end of the list
    }

    public boolean locationRangeExists(Location location, int range) {
        for (EggDropLogEntry logEntry : logEntries) {
            if (logEntry.getLocation().distance(location) < range)
                return true;
        }
        return false;
    }

    private void beginTrimCheckTimer() {
        // Every X seconds, trim our log
        Bukkit.getScheduler().scheduleSyncRepeatingTask(EggHunt.getInstance(), () -> {
            trimLogEntries();
        }, 0, 20 * 30);
    }

    private void trimLogEntries() {
        long currentTime = new Date().getTime();
        for (int i = 0; i < logEntries.size(); i++) {
            EggDropLogEntry logEntry = logEntries.get(i);
            if (logEntry.getDropTime() < currentTime - logEntry.getDropRule().getProtectionDuration()) {
                // trim!
                logEntries.remove(i);
                i--;
            }
        }
    }
}
