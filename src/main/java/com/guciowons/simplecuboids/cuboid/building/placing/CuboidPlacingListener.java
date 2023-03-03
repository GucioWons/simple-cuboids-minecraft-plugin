package com.guciowons.simplecuboids.cuboid.building.placing;

import com.guciowons.simplecuboids.cuboid.BasicCuboidListener;
import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import com.guciowons.simplecuboids.cuboid.building.placing.strategies.PlacingBlockContext;
import com.guciowons.simplecuboids.cuboid.building.placing.strategies.PlacingCuboidStrategy;
import com.guciowons.simplecuboids.cuboid.building.placing.strategies.PlacingNormalStrategy;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class CuboidPlacingListener extends BasicCuboidListener {

    public CuboidPlacingListener(CuboidRepository cuboidRepository) {
        super(cuboidRepository);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Block placedBlock = e.getBlock();
        Location placedBlockLocation = placedBlock.getLocation();
        ItemMeta placedBlockMeta = e.getItemInHand().getItemMeta();
        Optional<Cuboid> cuboid = cuboidRepository.getBlockAtCuboid(placedBlockLocation.getBlockX(), placedBlockLocation.getBlockZ());
        PlacingBlockContext context = null;
        if(cuboid.isPresent()){
            context = new PlacingBlockContext(new PlacingNormalStrategy(cuboid.get()));
        }else if(placedBlock.getType() == Material.SPONGE && placedBlockMeta != null){
            context = new PlacingBlockContext(new PlacingCuboidStrategy(cuboidRepository, placedBlockMeta.getDisplayName()));
        }
        if(context != null){
            e.setCancelled(context.shouldCancel(placedBlockLocation, e.getPlayer()));
        }
    }
}
