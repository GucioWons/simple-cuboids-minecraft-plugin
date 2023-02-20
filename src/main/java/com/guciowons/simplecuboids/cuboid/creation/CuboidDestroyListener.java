package com.guciowons.simplecuboids.cuboid.creation;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CuboidDestroyListener implements Listener {
    private final CuboidRepository cuboidRepository;

    public CuboidDestroyListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onCuboidDestroy(BlockBreakEvent e){
        Block destroyedBlock = e.getBlock();
        if(destroyedBlock.getType() == Material.SPONGE){
            e.setCancelled(checkIfCuboid(destroyedBlock.getLocation(), e.getPlayer()));
        }
    }

    private boolean checkIfCuboid(Location location, Player player){
        return cuboidRepository.getCuboidByLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ())
                .map(cuboid -> destroyCuboid(cuboid, player)).orElse(false);
    }

    private boolean destroyCuboid(Cuboid cuboid, Player player){
        if(cuboid.getPlayer().equals(player)){
            cuboidRepository.deleteCuboid(cuboid);
            player.sendMessage("Cuboid destroyed!");
            return false;
        }else{
            player.sendMessage("Its not your cuboid!");
            return true;
        }
    }
}
