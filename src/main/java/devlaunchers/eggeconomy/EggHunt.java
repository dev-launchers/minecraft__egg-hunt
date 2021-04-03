package devlaunchers.eggeconomy;


import devlaunchers.eggeconomy.commands.GiveEggCommand;
import devlaunchers.eggeconomy.commands.TpEggCommand;
import devlaunchers.eggeconomy.commands.TpMainCommand;
import devlaunchers.eggeconomy.dropevents.BlockBreakEggDropper;
import devlaunchers.eggeconomy.dropevents.DropRule;
import devlaunchers.eggeconomy.dropevents.DropStrategy;
import devlaunchers.eggeconomy.dropevents.MobKillEggDropper;
import devlaunchers.eggeconomy.items.ItemManager;
import devlaunchers.eggeconomy.items.RecipeManager;
import devlaunchers.eggeconomy.populationevents.ChestEggPopulator;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

// TODONE: Make it so events in this plugin only fire in Egg World
// TODONE: Add line breaks in egg descriptions
// TODONE: Test to make sure eggs don't appear in other worlds
// TODONE: Command to teleport players to Egg World
// TODONE: Command to teleport players back from Egg World
// TODONE: Decide on winner's title
/*
   - Egg Lord
   - Egg Master
   - Egmaster
   - Huntsman
   - Huntress
 */

// TODO: Egg storage/deposit?


public final class EggHunt extends JavaPlugin {

    private static JavaPlugin instance;
    private static ItemManager itemManager;
    private static RecipeManager recipeManager;

    public final static String WORLD_NAME = "egg-world";

    private final static int CHANCE_MULTIPLIER = 2;

    @Override
    public void onEnable() {
        System.out.println("\n" +
                "Egg Hunt " +
                "Version 0.0.1 - Release\n" +
                "Created by DevLaunchers. For people, by people.");
        System.out.println("[EggEconomy] [LOG] Plugin initializing..");
        instance = this;
        itemManager = new ItemManager();
        recipeManager = new RecipeManager();

        this.getCommand("giveEgg").setExecutor(new GiveEggCommand());
        this.getCommand("tpEgg").setExecutor(new TpEggCommand());
        this.getCommand("tpMain").setExecutor(new TpMainCommand());

        System.out.println("[EggEconomy] [LOG] giveEgg command loaded into memory.");

        getServer().getPluginManager().registerEvents(new ChestEggPopulator(), this);
        getServer().getPluginManager().registerEvents(new EggWorldListener(), this);

        System.out.println("[EggEconomy] [LOG] ChestEggPopulator loaded successfully!");

        // Detect breaking blocks and maybe drop egg$
        getServer().getPluginManager().registerEvents(new BlockBreakEggDropper(
                new DropStrategy(
                        new HashMap<Object, DropRule>(){{
                                put(Material.DIAMOND_ORE, new DropRule(50, 1, 60));
                                put(Material.EMERALD_ORE, new DropRule(100, 1, 60));
                    }}
                )),
                this);



        // Detect killing mobs and maybe drop egg$
        getServer().getPluginManager().registerEvents(new MobKillEggDropper(
                new DropStrategy(
                        new HashMap<Object, DropRule>(){{
                            put(EntityType.CAVE_SPIDER, new DropRule(8*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.CREEPER, new DropRule(8*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.DROWNED, new DropRule(4*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.ENDERMAN, new DropRule(10*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.HOGLIN, new DropRule(4*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.PHANTOM, new DropRule(8*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.PILLAGER, new DropRule(8*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.SLIME, new DropRule(8*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.SKELETON, new DropRule(8*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.SPIDER, new DropRule(4*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.WITCH, new DropRule(8*CHANCE_MULTIPLIER, 3, 130));
                            put(EntityType.ZOMBIE, new DropRule(8*CHANCE_MULTIPLIER, 3, 130));
                        }}
                )), this);
        System.out.println("[EggEconomy] [LOG] Loading complete. Now earn some awesome egg$!");
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static ItemManager getItemUtil() {
        return itemManager;
    }

    public static RecipeManager getRecipeManager() {
        return recipeManager;
    }

    @Override
    public void onDisable() {
        System.out.println("[EggEconomy] [LOG] Shutting down, Goodbye for now!");
    }


}


// Possible Byproducts:
//    - Currency (Egg$)
//    -

// Possible Actions:
//    - Interacting with others
//       - Making friends
//       - Competition
//       - Starting kingdoms/clans
//       - Business partners
//    - Exploring/Adventuring
//    - Mining
//    - Crafting
//    - Building
//       - Things that impress others
//       - Bases
//       - Outposts
//       - Pretty places
//       - Funny/cute things
//    - Collecting things...
//       - RARE STUFF
//    - Farming
//    - Surviving
//    - Building stories
//    - Meeting people
//    - Fighting monsters
//    - Fighting other players
//    - Selling/Buying/Trading
//    - Pets
//    - Maximizing efficiency
//       - Building auto farms and automated redstone devices


// FOCUS: Which behaviours do we want to encourage?
//  [COMMUNITY, EXPLORATION, BUILDING, CRAFTING]
//  - Interacting with other players
//  -

// Guillermo: Farming Crops, Mining, Killing Mobs, Enchanting, Using Workbenches like Anvils?

// TODO: Give people items when they first start in the server (Instructions, Egg$, Wood, etc.)
// TODO: Random NPCs appear that have to be chased/found, which then give eggs
// TODO: Advancements gives Egg$?
// TODO: Detect different fortune levels

// TODO: Leveling up gives Egg$?

// TODO: (EPIC) PVP challenges for eggs
// TODO: (EPIC) Quests for Egg$
// TODO: (EPIC) Custom advancement system
// TODO: Make it so Piglins can trade Egg$ (Cant find proper event - ON HOLD)

// UNSURE HOW TO IMPLEMENT
// TODO: Player placed egg bounties - players can place bounties on other players
// TODO: Building challenges
// TODO: Reputation score - game gives you more rewards for being a nicer player
// TODO: Reward players for interacting in chat?

// TODONE: Populate eggs in chests that appear while people are out exploring
// TODONE: Detect silk touch when dropping eggs
// TODONE: Create general egg dropping protection scheme that can be reused - based on radius and time period?
// TODONE: Drop egg$ from mining certain blocks (need to monitor so silk touch and farms don't break economy)
// TODONE: Killing enemies give Egg$
// TODONE: Make better config structure for Egg drop chances (make it so types can have separate chances of dropping, and maybe different timeout)
// TODONE: Make it so fortune has a higher chance to drop eggs
