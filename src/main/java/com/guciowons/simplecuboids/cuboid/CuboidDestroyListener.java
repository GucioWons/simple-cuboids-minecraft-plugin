package com.guciowons.simplecuboids.cuboid;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CuboidDestroyListener implements Listener {
    @EventHandler
    public void onCuboidDestroy(BlockBreakEvent e){
        Block destroyedBlock = e.getBlock();
        if(destroyedBlock.getType() == Material.SPONGE){
            Location location = destroyedBlock.getLocation();
            if(CuboidRepository.cuboidExists(location.getBlockX(), location.getBlockY(), location.getBlockZ())){
                System.out.println("Jest");
            }
            else{
                System.out.println("Nie ma");
            }
        }
    }
}
