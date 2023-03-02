package com.guciowons.simplecuboids.cuboid.piston;

import com.guciowons.simplecuboids.SimpleCuboids;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class CuboidPistonRetractListener extends BasicPistonListener {

    public CuboidPistonRetractListener(CuboidRepository cuboidRepository) {
        super(cuboidRepository);
    }

    @EventHandler
    public void onBlockPistonRetract(BlockPistonRetractEvent e){
        if(e.isSticky()) {
            e.setCancelled(cancelPistonEvent(e.getBlocks(), e.getBlock()));
        }
    }
}
