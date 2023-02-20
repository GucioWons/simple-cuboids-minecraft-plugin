package com.guciowons.simplecuboids.cuboid.damage.strategies;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageCancelStrategy implements DamageCancelStrategy{
    @Override
    public boolean shouldCancel(Entity entity, EntityDamageByEntityEvent.DamageCause cause, FileConfiguration config) {
        if(entity instanceof Player){
            return whenEntityIsPlayer(cause, config);
        }else{
            return whenEntityIsEntity(cause, config);
        }
    }

    private boolean whenEntityIsPlayer(EntityDamageByEntityEvent.DamageCause cause, FileConfiguration config){
        if(config.getBoolean("DisableEntityDamageEntities")){
            return whenDamageIsEnabled(cause, config);
        }
        return false;
    }

    private boolean whenEntityIsEntity(EntityDamageByEntityEvent.DamageCause cause, FileConfiguration config){
        if(config.getBoolean("DisableEntityDamagePlayers")){
            return whenDamageIsEnabled(cause, config);
        }
        return false;
    }

    private boolean whenDamageIsEnabled(EntityDamageByEntityEvent.DamageCause cause, FileConfiguration config){
        if(cause == EntityDamageByEntityEvent.DamageCause.ENTITY_EXPLOSION){
            return config.getBoolean("DisableExplosionDamage");
        }
        return true;
    }
}
