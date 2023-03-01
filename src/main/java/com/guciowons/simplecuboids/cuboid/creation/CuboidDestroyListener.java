package com.guciowons.simplecuboids.cuboid.creation;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import com.guciowons.simplecuboids.files.Messages;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

public class CuboidDestroyListener implements Listener {
    private final CuboidRepository cuboidRepository;

    public CuboidDestroyListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    //SMOKE
    @EventHandler
    public void onCuboidDestroy(BlockBreakEvent e){
        Block destroyedBlock = e.getBlock();
        if(destroyedBlock.getType() == Material.SPONGE){
            e.setCancelled(checkIfCuboid(destroyedBlock.getLocation(), e.getPlayer()));
        }
    }

    private boolean checkIfCuboid(Location location, Player player){
        return cuboidRepository.getCuboidByLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ())
                .map(cuboid -> destroyCuboid(location, cuboid, player)).orElse(false);
    }

    private boolean destroyCuboid(Location location, Cuboid cuboid, Player player){
        if(cuboid.getPlayer().equals(player)){
            cuboidRepository.deleteCuboid(cuboid);
            player.sendMessage(Objects.requireNonNull(Messages.getMessagesFile().getString("Cuboid destroyed")));
            location.add(location.getX() > 0 ? 0.5 : -0.5, 0.0, location.getZ() > 0 ? 0.5 : -0.5);
            location.getWorld().spawnParticle(Particle.SOUL, location, 25);
            return false;
        }else{
            player.sendMessage(Objects.requireNonNull(Messages.getMessagesFile().getString("Cuboid not yours")));
            return true;
        }
    }
}
