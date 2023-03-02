package com.guciowons.simplecuboids.cuboid.building.placing.strategies;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import com.guciowons.simplecuboids.files.Messages;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlacingCuboidStrategy implements PlacingBlockStrategy{
    private final CuboidRepository cuboidRepository;
    private final String blockName;

    public PlacingCuboidStrategy(CuboidRepository cuboidRepository, String blockName) {
        this.cuboidRepository = cuboidRepository;
        this.blockName = blockName;
    }

    @Override
    public boolean shouldCancel(Location placedBlockLocation, Player player) {
        if (blockName.equals("Cuboid") && !cuboidRepository.playerHasCuboid(player)) {
            cuboidRepository.createCuboid(new Cuboid(
                    placedBlockLocation.getBlockX(),
                    placedBlockLocation.getBlockY(),
                    placedBlockLocation.getBlockZ(), player, 5));
            player.sendMessage(Objects.requireNonNull(Messages.getMessagesFile().getString("Cuboid created")));
            double red = 0 / 255D;
            double green = 127 / 255D;
            double blue = 255 / 255D;
            placedBlockLocation.add(placedBlockLocation.getX() > 0 ? 0.5 : -0.5, 0.0, placedBlockLocation.getZ() > 0 ? 0.5 : -0.5);
            placedBlockLocation.getWorld().spawnParticle(Particle.SPELL_MOB, placedBlockLocation, 100, red, green, blue, 1);
            return false;
        }else{
            player.sendMessage(Objects.requireNonNull(Messages.getMessagesFile().getString("Cuboid exists")));
            return true;
        }
    }
}
