package com.guciowons.simplecuboids.cuboid.building.breaking.strategies;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.damage.strategies.DamageCancelStrategy;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BreakingBlockContext {
    private final BreakingBlockStrategy strategy;

    public BreakingBlockContext(BreakingBlockStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean shouldCancel(Cuboid cuboid, Player player, Location location) {
        return strategy.shouldCancel(cuboid, player, location);
    }
}
