package com.guciowons.simplecuboids.cuboid;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class CuboidBuildingListener implements Listener {
    private final CuboidRepository cuboidRepository;

    public CuboidBuildingListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onBlockPlaceAtCuboid(BlockPlaceEvent e){
        Block placedBlock = e.getBlock();
        Location placedBlockLocation = placedBlock.getLocation();
        cuboidRepository.getBlockAtCuboid(placedBlockLocation.getBlockX(), placedBlockLocation.getBlockZ())
                .ifPresent(cuboid -> cancelBlockPlace(cuboid, e));
    }

    @EventHandler
    public void onBlockBreakAtCuboid(BlockBreakEvent e){
        Block brokenBlock = e.getBlock();
        Location brokenBlockLocation = brokenBlock.getLocation();
        cuboidRepository.getBlockAtCuboid(brokenBlockLocation.getBlockX(), brokenBlockLocation.getBlockZ())
                .ifPresent(cuboid -> cancelBlockBreak(cuboid, e));
    }

    private void cancelBlockPlace(Cuboid cuboid, BlockPlaceEvent e){
        if(!cuboid.getPlayer().equals(e.getPlayer())){
            e.setCancelled(true);
        }
    }
    private void cancelBlockBreak(Cuboid cuboid, BlockBreakEvent e){
        if(!cuboid.getPlayer().equals(e.getPlayer())){
            e.setCancelled(true);
        }
    }
}
