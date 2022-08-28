package com.guciowons.simplecuboids.cuboid;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class CuboidBuildingListener implements Listener {
    @EventHandler
    public void onBlockPlaceAtCuboid(BlockPlaceEvent e){
        Block placedBlock = e.getBlock();
        Location placedBlockLocation = placedBlock.getLocation();
        CuboidRepository.isBlockAtCuboid(placedBlockLocation.getBlockX(), placedBlockLocation.getBlockZ())
                .ifPresent(cuboid -> e.getPlayer().sendMessage("Cuboid"));
    }
}
