package com.guciowons.simplecuboids.cuboid.building;

import com.guciowons.simplecuboids.cuboid.BasicCuboidListener;
import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class CuboidPlacingListener extends BasicCuboidListener {

    public CuboidPlacingListener(CuboidRepository cuboidRepository) {
        super(cuboidRepository);
    }

    @EventHandler
    public void onBlockPlaceAtCuboid(BlockPlaceEvent e){
        Block placedBlock = e.getBlock();
        Location placedBlockLocation = placedBlock.getLocation();
        cuboidRepository.getBlockAtCuboid(placedBlockLocation.getBlockX(), placedBlockLocation.getBlockZ())
                .ifPresent(cuboid -> cancelBlockPlace(cuboid, e));
    }

    private void cancelBlockPlace(Cuboid cuboid, BlockPlaceEvent e){
        if(!cuboid.getPlayer().equals(e.getPlayer())){
            e.setCancelled(true);
        }
    }
}
