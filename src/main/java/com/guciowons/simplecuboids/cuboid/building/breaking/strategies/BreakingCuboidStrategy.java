package com.guciowons.simplecuboids.cuboid.building.breaking.strategies;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import com.guciowons.simplecuboids.files.Messages;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Objects;

public class BreakingCuboidStrategy implements BreakingBlockStrategy{
    private final CuboidRepository cuboidRepository;
    public BreakingCuboidStrategy(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @Override
    public boolean shouldCancel(Cuboid cuboid, Player player, Location location){
        if(cuboid.getPlayer().getUniqueId().equals(player.getUniqueId())){
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
