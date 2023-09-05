package uk.deathtrap.chargedcreepersbreakbedrock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ChargedCreepersBreakBedrock extends JavaPlugin implements Listener {

    private int maxBedrockBreak; // Declare maxBedrockBreak variable
    @Override
    public void onEnable() {
        // Load the config or set defaults
        saveDefaultConfig();
        reloadConfig();
        FileConfiguration config = getConfig();
        maxBedrockBreak = config.getInt("max_bedrock_break", 10);
        getLogger().info("Max Bedrock Break: " + maxBedrockBreak);
        getServer().getPluginManager().registerEvents(this, this);
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

                int maxBedrockBreak = getConfig().getInt("max_bedrock_break", 30);

                // Shuffle the list of bedrock blocks randomly
                Collections.shuffle(bedrockBlocks);

                // Remove bedrock blocks up to the configured limit
                int blocksRemoved = 0;
                for (Block block : bedrockBlocks) {
                    if (blocksRemoved >= maxBedrockBreak) {
                        break;
                    }
                    // Replace bedrock with fire
                    block.setType(Material.FIRE);
                    blocksRemoved++;
                }
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
