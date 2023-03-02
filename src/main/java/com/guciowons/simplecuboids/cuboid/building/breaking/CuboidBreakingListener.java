package com.guciowons.simplecuboids.cuboid.building.breaking;

import com.guciowons.simplecuboids.cuboid.BasicCuboidListener;
import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import com.guciowons.simplecuboids.cuboid.building.breaking.strategies.BreakingBlockContext;
import com.guciowons.simplecuboids.cuboid.building.breaking.strategies.BreakingCuboidStrategy;
import com.guciowons.simplecuboids.cuboid.building.breaking.strategies.BreakingNormalStrategy;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
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
                .ifPresent(cuboid -> e.setCancelled(shouldBreakBlock(brokenBlock, brokenBlockLocation, cuboid, e.getPlayer())));
    }

    private boolean shouldBreakBlock(Block brokenBlock, Location brokenBlockLocation, Cuboid cuboid, Player player){
        BreakingBlockContext context = null;
        if(brokenBlock.getType() == Material.SPONGE && cuboidRepository.getCuboidByLocation(brokenBlockLocation.getBlockX(), brokenBlockLocation.getBlockY(), brokenBlockLocation.getBlockZ()).isPresent()){
            context = new BreakingBlockContext(new BreakingCuboidStrategy(cuboidRepository));
        }else if(cuboidRepository.isBlockAtCuboid(brokenBlockLocation.getBlockX(), brokenBlockLocation.getBlockZ())){
            context = new BreakingBlockContext(new BreakingNormalStrategy());
        }
        if(context != null){
            return context.shouldCancel(cuboid, player, brokenBlockLocation);
        }else{
            return false;
        }
    }
}
