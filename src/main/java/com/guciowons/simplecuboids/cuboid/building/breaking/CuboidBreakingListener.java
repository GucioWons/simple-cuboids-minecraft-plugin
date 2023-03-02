package com.guciowons.simplecuboids.cuboid.building.breaking;

import com.guciowons.simplecuboids.cuboid.BasicCuboidListener;
import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class CuboidBreakingListener extends BasicCuboidListener {
    public CuboidBreakingListener(CuboidRepository cuboidRepository) {
        super(cuboidRepository);
    }

    @EventHandler
    public void onBlockBreakAtCuboid(BlockBreakEvent e){
        Block brokenBlock = e.getBlock();
        Location brokenBlockLocation = brokenBlock.getLocation();
        cuboidRepository.getBlockAtCuboid(brokenBlockLocation.getBlockX(), brokenBlockLocation.getBlockZ())
                .ifPresent(cuboid -> cancelBlockBreak(cuboid, e));
    }

    private void cancelBlockBreak(Cuboid cuboid, BlockBreakEvent e){
        if(!cuboid.getPlayer().equals(e.getPlayer())){
            e.setCancelled(true);
        }
    }
}
