package com.guciowons.simplecuboids.cuboid.building.placing.strategies;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.building.breaking.strategies.BreakingBlockStrategy;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlacingBlockContext {
    private final PlacingBlockStrategy strategy;

    public PlacingBlockContext(PlacingBlockStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean shouldCancel(Location placedBlockLocation, Player player) {
        return strategy.shouldCancel(placedBlockLocation, player);
    }
}
