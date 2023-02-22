package com.guciowons.simplecuboids.cuboid.piston;

import com.guciowons.simplecuboids.SimpleCuboids;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class CuboidPistonRetractListener implements Listener {
    private SimpleCuboids plugin = SimpleCuboids.getPlugin(SimpleCuboids.class);
    private final CuboidRepository cuboidRepository;

    public CuboidPistonRetractListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onBlockPistonRetract(BlockPistonRetractEvent e){
        if(e.isSticky()) {
            if (e.getBlocks().stream().anyMatch(block -> cuboidRepository.isBlockAtCuboid(block.getX(), block.getZ()))) {
                Block piston = e.getBlock();
                if (!cuboidRepository.isBlockAtCuboid(piston.getX(), piston.getZ()) &&
                        plugin.getConfig().getBoolean("DisablePistons")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
