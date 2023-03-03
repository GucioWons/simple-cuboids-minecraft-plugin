package com.guciowons.simplecuboids.cuboid.interactions;

import com.guciowons.simplecuboids.SimpleCuboids;
import com.guciowons.simplecuboids.cuboid.BasicCuboidListener;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class CuboidLiquidFlowListener extends BasicCuboidListener {

    public CuboidLiquidFlowListener(CuboidRepository cuboidRepository) {
        super(cuboidRepository);
    }

    @EventHandler
    public void onLiquidFlowEvent(BlockFromToEvent event) {
        Block block = event.getBlock();
        Block toBlock = event.getToBlock();
        Material blockType = block.getType();
        if (blockType == Material.LAVA && !cuboidRepository.isBlockAtCuboid(block.getX(), block.getZ())
                && cuboidRepository.isBlockAtCuboid(toBlock.getX(), toBlock.getZ())) {
            event.setCancelled(plugin.getConfig().getBoolean("DisableLavaFlow"));
        } else if (blockType == Material.WATER && !cuboidRepository.isBlockAtCuboid(block.getX(), block.getZ())
                && cuboidRepository.isBlockAtCuboid(toBlock.getX(), toBlock.getZ())) {
            event.setCancelled(plugin.getConfig().getBoolean("DisableWaterFlow"));
        }
    }
}
