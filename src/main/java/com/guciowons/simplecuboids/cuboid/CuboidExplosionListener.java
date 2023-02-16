package com.guciowons.simplecuboids.cuboid;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

public class CuboidExplosionListener implements Listener {
    private final CuboidRepository cuboidRepository;

    public CuboidExplosionListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e){
        List<Block> blocksToExplode = e.blockList();
        boolean is = blocksToExplode.stream().anyMatch(block -> cuboidRepository.isBlockAtCuboid(block.getX(), block.getZ()));
        if(is){
            e.blockList().clear();
            e.setCancelled(true);
        }
    }
}
