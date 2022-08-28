package com.guciowons.simplecuboids.cuboid;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class CuboidBuildingListener implements Listener {
    @EventHandler
    public void onBlockPlaceAtCuboid(BlockPlaceEvent e){
        Block placedBlock = e.getBlock();
        Location placedBlockLocation = placedBlock.getLocation();
        CuboidRepository.isBlockAtCuboid(placedBlockLocation.getBlockX(), placedBlockLocation.getBlockZ())
                .ifPresent(cuboid -> e.getPlayer().sendMessage("Block placed"));
    }

    @EventHandler
    public void onBlockBreakAtCuboid(BlockBreakEvent e){
        Block brokenBlock = e.getBlock();
        Location brokenBlockLocation = brokenBlock.getLocation();
        CuboidRepository.isBlockAtCuboid(brokenBlockLocation.getBlockX(), brokenBlockLocation.getBlockZ())
                .ifPresent(cuboid -> e.getPlayer().sendMessage("Block broken"));
    }
}
