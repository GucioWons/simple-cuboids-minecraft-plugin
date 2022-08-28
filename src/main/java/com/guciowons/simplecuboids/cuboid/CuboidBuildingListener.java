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
        cuboidRepository.isBlockAtCuboid(placedBlockLocation.getBlockX(), placedBlockLocation.getBlockZ())
                .ifPresent(cuboid -> e.getPlayer().sendMessage("Block placed"));
    }

    @EventHandler
    public void onBlockBreakAtCuboid(BlockBreakEvent e){
        Block brokenBlock = e.getBlock();
        Location brokenBlockLocation = brokenBlock.getLocation();
        cuboidRepository.isBlockAtCuboid(brokenBlockLocation.getBlockX(), brokenBlockLocation.getBlockZ())
                .ifPresent(cuboid -> e.getPlayer().sendMessage("Block broken"));
    }
}
