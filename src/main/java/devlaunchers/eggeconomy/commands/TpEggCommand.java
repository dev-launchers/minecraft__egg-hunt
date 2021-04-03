package devlaunchers.eggeconomy.commands;

import devlaunchers.eggeconomy.EggHunt;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TpEggCommand implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Server server = Bukkit.getServer();

            // Teleport player to main world
            server.dispatchCommand(server.getConsoleSender(), "mv tp "+player.getName()+" egg-world");

            System.out.println("[EggEconomy] [LOG] Teleported player to egg! Name: " + player.getName());
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}