package uk.deathtrap.chargedcreepersbreakbedrock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class ChargedCreepersBreakBedrock extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);

        // Plugin startup logic
        getLogger().info("Enabling Charged Creepers Break Bedrock");
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
                // Get a list of bedrock that could be removed
                Block creeperBlock = creeper.getLocation().getBlock();
                List<Block> bedrockBlocks = getBedrock(creeperBlock);

                bedrockBlocks.forEach(block -> {
                    block.setType(Material.FIRE);
                });
                // TODO
                // remove as many bedrock as the config allows
            }
        }
    }

// Returns a list of bedrock that could be removed
    private List<Block> getBedrock(Block creeperBlock) {
        List<Block> bedrockBlocks = new ArrayList<>(); // Initialize an ArrayList to store the blocks

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 2; y++) {
                for (int z = -1; z <= 1; z++) {
                    Block block = creeperBlock.getRelative(x, y, z);

                    // Check if the block is bedrock
                    if (block.getType() == Material.BEDROCK) {
                        // add to the list we return
                        bedrockBlocks.add(block);
                    }
                }
            }
        }

        return bedrockBlocks; // Return the list of potential bedrock blocks
    }

}
