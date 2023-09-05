package uk.deathtrap.chargedcreepersbreakbedrock;
//package uk.deathtrap.chargedcreepersbreakbedrock;
//
//import org.bukkit.Material;
//import org.bukkit.block.Block;
//import org.bukkit.entity.Creeper;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.entity.EntityExplodeEvent;
//import org.bukkit.plugin.java.JavaPlugin;
//
//public final class ChargedCreepersBreakBedrock extends JavaPlugin implements Listener {
//
//    @Override
//    public void onEnable() {
//        // Register the event listener
//        getServer().getPluginManager().registerEvents(this, this);
//
//        // Plugin startup logic
//        getLogger().info("Creepers break bedrock enabled!");
//    }
//
//    @Override
//    public void onDisable() {
//        // Plugin shutdown logic
//    }
//
//    @EventHandler
//    public void onCreeperExplosion(EntityExplodeEvent event) {
//        if (event.getEntity() instanceof Creeper) {
//            Creeper creeper = (Creeper) event.getEntity();
//
//            // Check if the creeper is charged
//            if (creeper.isPowered()) {
//                // Get the creeper's location
//                Block creeperBlock = creeper.getLocation().getBlock();
//
//                // Loop through nearby blocks
//                for (int x = -1; x <= 1; x++) {
//                    for (int y = -1; y <= 1; y++) {
//                        for (int z = -1; z <= 1; z++) {
//                            Block block = creeperBlock.getRelative(x, y, z);
//
//                            // Check if the block is bedrock
//                            if (block.getType() == Material.BEDROCK) {
//                                // Remove the bedrock
//                                block.setType(Material.AIR);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChargedCreepersBreakBedrock extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);

        // Plugin startup logic
        getLogger().info("Hello world!1");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onCreeperExplosion(EntityExplodeEvent event) {
        if (event.getEntity() instanceof Creeper) {
            Creeper creeper = (Creeper) event.getEntity();

            // Check if the creeper is charged
            if (creeper.isPowered()) {
                // Get the creeper's location
                Block creeperBlock = creeper.getLocation().getBlock();

                // Loop through blocks around the creeper's location, including above and below
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 2; y++) { // Adjusted the 'y' loop for one block above
                        for (int z = -1; z <= 1; z++) {
                            Block block = creeperBlock.getRelative(x, y, z);

                            // Check if the block is bedrock
                            if (block.getType() == Material.BEDROCK) {
                                // Remove the bedrock
                                block.setType(Material.AIR);
                            }
                        }
                    }
                }
            }
        }
    }
}
