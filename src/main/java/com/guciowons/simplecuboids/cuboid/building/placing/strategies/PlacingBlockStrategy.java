package com.guciowons.simplecuboids.cuboid.building.placing.strategies;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface PlacingBlockStrategy {
    boolean shouldCancel(Location placedBlockLocation, Player player);
}
