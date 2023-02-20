package com.guciowons.simplecuboids.cuboid.damage.strategies;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class DamageCancelContext {
    private final DamageCancelStrategy strategy;

    public DamageCancelContext(DamageCancelStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean shouldCancel(Entity entity, EntityDamageByEntityEvent.DamageCause cause, FileConfiguration config) {
        return strategy.shouldCancel(entity, cause, config);
    }
}
