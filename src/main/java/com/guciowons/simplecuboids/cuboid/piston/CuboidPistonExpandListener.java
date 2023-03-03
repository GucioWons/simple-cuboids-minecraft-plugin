package com.guciowons.simplecuboids.cuboid.piston;

import com.guciowons.simplecuboids.SimpleCuboids;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

public class CuboidPistonExpandListener extends BasicPistonListener {

    public CuboidPistonExpandListener(CuboidRepository cuboidRepository) {
        super(cuboidRepository);
    }

    @EventHandler
    public void onBlockPistonRetract(BlockPistonExtendEvent e) {
        e.setCancelled(cancelPistonEvent(e.getBlocks(), e.getBlock()));
    }
}
