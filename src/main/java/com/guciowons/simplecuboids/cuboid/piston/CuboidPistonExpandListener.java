package com.guciowons.simplecuboids.cuboid.piston;

import com.guciowons.simplecuboids.SimpleCuboids;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

public class CuboidPistonExpandListener implements Listener {
    private SimpleCuboids plugin = SimpleCuboids.getPlugin(SimpleCuboids.class);
    private final CuboidRepository cuboidRepository;

    public CuboidPistonExpandListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onBlockPistonRetract(BlockPistonExtendEvent e) {
        if (e.getBlocks().stream().anyMatch(block -> cuboidRepository.isBlockAtCuboid(block.getX(), block.getZ()))) {
            Block piston = e.getBlock();
            if (!cuboidRepository.isBlockAtCuboid(piston.getX(), piston.getZ()) &&
                    plugin.getConfig().getBoolean("DisablePistons")) {
                e.setCancelled(true);
            }
        }
    }
}
