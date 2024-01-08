package com.guciowons.simplecuboids.cuboid.building.placing.strategies;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlacingNormalStrategy implements PlacingBlockStrategy{
    private final Cuboid cuboid;

    public PlacingNormalStrategy(Cuboid cuboid) {
        this.cuboid = cuboid;
    }

    @Override
    public boolean shouldCancel(Location placedBlockLocation, Player player) {
        return !UUID.fromString(cuboid.getPlayerId()).equals(player.getUniqueId());
    }
}
