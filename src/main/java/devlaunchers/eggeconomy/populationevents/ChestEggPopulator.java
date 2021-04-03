
package devlaunchers.eggeconomy.populationevents;

import devlaunchers.eggeconomy.EggHunt;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class ChestEggPopulator implements Listener {

    private static final int[] eggAmounts = new int[]{0, 1, 2, 4, 8, 16};

    private int getRandomEggAmount() {
        return eggAmounts[(int)(Math.random()*eggAmounts.length)];
    }

    @EventHandler
    public void onChunkPopulate(ChunkPopulateEvent e) {
        if (!e.getWorld().getName().equals(EggHunt.WORLD_NAME))  return;

        BlockState[] tileEntities = e.getChunk().getTileEntities();
        for (BlockState state : tileEntities) {
            if(state.getType() == Material.CHEST) {
                ItemStack eggItemStack = EggHunt.getItemUtil().getEggItem().clone();
                int numEggs = getRandomEggAmount();
                eggItemStack.setAmount(numEggs);

                Chest chest = (Chest)state;//.getBlock();

                tryInsertIntoRandomSlot(chest.getBlockInventory(), eggItemStack);
            }
        }
    }

    private boolean tryInsertIntoRandomSlot(Inventory inventory, ItemStack itemStack) {
        boolean inserted = false;
        int sentinel = 0;
        while (sentinel < 30) {
            sentinel++;
            int randomSlotIndex = (int)(Math.random()*20);
            if (inventory.getItem(randomSlotIndex) != null) {
                inventory.setItem(randomSlotIndex, itemStack);
                inserted = true;
                break;
            }
        }
        return inserted;
    }
}

