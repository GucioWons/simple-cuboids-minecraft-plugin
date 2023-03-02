package com.guciowons.simplecuboids.cuboid.building.breaking.strategies;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface BreakingBlockStrategy {
    boolean shouldCancel(Cuboid cuboid, Player player, Location location);
}
