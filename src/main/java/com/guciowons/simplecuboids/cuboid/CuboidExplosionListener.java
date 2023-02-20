package com.guciowons.simplecuboids.cuboid;

import com.guciowons.simplecuboids.SimpleCuboids;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

public class CuboidExplosionListener implements Listener {
    private SimpleCuboids plugin = SimpleCuboids.getPlugin(SimpleCuboids.class);
    private final CuboidRepository cuboidRepository;

    public CuboidExplosionListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e){
        List<Block> blocksToExplode = e.blockList();
        blocksToExplode.stream()
                .filter(
                        block -> cuboidRepository.isBlockAtCuboid(block.getX(), block.getZ()) &&
                                plugin.getConfig().getBoolean("DisableExplosions"))
                .findAny().ifPresent(block -> cancelExplosion(e));
    }

    private void cancelExplosion(EntityExplodeEvent e){
        e.blockList().clear();
        e.setCancelled(true);
    }
}