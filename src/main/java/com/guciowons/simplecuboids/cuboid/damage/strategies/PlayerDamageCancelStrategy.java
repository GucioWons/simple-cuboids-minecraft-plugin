package com.guciowons.simplecuboids.cuboid.damage.strategies;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageCancelStrategy implements DamageCancelStrategy{
    @Override
    public boolean shouldCancel(Entity entity, EntityDamageByEntityEvent.DamageCause cause, FileConfiguration config) {
        if(entity instanceof Player) {
            return config.getBoolean("DisablePlayerDamagePlayers");
        } else {
            return config.getBoolean("DisablePlayerDamageEntities");
        }
    }
}
