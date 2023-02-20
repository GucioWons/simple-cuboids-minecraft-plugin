package com.guciowons.simplecuboids.cuboid.damage.strategies;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface DamageCancelStrategy {
    boolean shouldCancel(Entity entity, EntityDamageByEntityEvent.DamageCause cause, FileConfiguration config);
}