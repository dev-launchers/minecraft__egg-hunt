package devlaunchers.eggeconomy.commands;

import devlaunchers.eggeconomy.EggHunt;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveEggCommand implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Create a new ItemStack (type: diamond)
            ItemStack eggItem = EggHunt.getItemUtil().getEggItem().clone();

            int numEggs = 1;
            if (args[0] != null)
                numEggs = Integer.parseInt(args[0]);

            // Set the amount of the ItemStack
            eggItem.setAmount(numEggs);

            // Give the player our items (comma-seperated list of all ItemStack)
            player.getInventory().addItem(eggItem);

            System.out.println("[EggEconomy] [LOG] Gave egg$! Amount: " + numEggs);
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}