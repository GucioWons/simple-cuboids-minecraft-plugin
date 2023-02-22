package com.guciowons.simplecuboids.cuboid;

import com.guciowons.simplecuboids.SimpleCuboids;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class CuboidLiquidFlowListener implements Listener {
    private SimpleCuboids plugin = SimpleCuboids.getPlugin(SimpleCuboids.class);
    private final CuboidRepository cuboidRepository;

    public CuboidLiquidFlowListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
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
