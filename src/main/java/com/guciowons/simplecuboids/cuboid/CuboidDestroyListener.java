package com.guciowons.simplecuboids.cuboid;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CuboidDestroyListener implements Listener {
    @EventHandler
    public void onCuboidDestroy(BlockBreakEvent e){
        Block destroyedBlock = e.getBlock();
        if(destroyedBlock.getType() == Material.SPONGE){
            Location location = destroyedBlock.getLocation();
            CuboidRepository.getCuboidByLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ())
                    .ifPresent(cuboid -> e.setCancelled(destroyCuboid(cuboid, e.getPlayer())));
        }
    }
    private boolean destroyCuboid(Cuboid cuboid, Player player){
        if(cuboid.getPlayer().equals(player)){
            CuboidRepository.deleteCuboid(cuboid);
            player.sendMessage("Cuboid destroyed!");
            return false;
        }else{
            player.sendMessage("Its not your cuboid!");
            return true;
        }
    }
}
