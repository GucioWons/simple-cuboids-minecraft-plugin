package com.guciowons.simplecuboids.cuboid.damage;

import com.guciowons.simplecuboids.SimpleCuboids;
import com.guciowons.simplecuboids.cuboid.BasicCuboidListener;
import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import com.guciowons.simplecuboids.cuboid.damage.strategies.DamageCancelContext;
import com.guciowons.simplecuboids.cuboid.damage.strategies.EntityDamageCancelStrategy;
import com.guciowons.simplecuboids.cuboid.damage.strategies.PlayerDamageCancelStrategy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class CuboidDamageListener extends BasicCuboidListener {

    public CuboidDamageListener(CuboidRepository cuboidRepository) {
        super(cuboidRepository);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        cuboidRepository.getBlockAtCuboid(e.getEntity().getLocation().getBlockX(), e.getEntity().getLocation().getBlockZ())
                .ifPresent(cuboid -> e.setCancelled(whenDamagerIsOwner(e, cuboid)));
    }

    private boolean whenDamagerIsOwner(EntityDamageByEntityEvent e, Cuboid cuboid){
        if(UUID.fromString(cuboid.getPlayerId()).equals(e.getDamager().getUniqueId())){
            return false;
        }else{
            return onCuboidDamage(e.getEntity(), e.getCause(), cuboid);
        }
    }

    private boolean onCuboidDamage(Entity entity, EntityDamageByEntityEvent.DamageCause cause, Cuboid cuboid){
        if(UUID.fromString(cuboid.getPlayerId()).equals(entity.getUniqueId()) && plugin.getConfig().getBoolean("DisableOwnerDamage")) {
            return true;
        } else{
            return whenDamagedIsNotOwner(entity, cause);
        }
    }

    private boolean whenDamagedIsNotOwner(Entity entity, EntityDamageByEntityEvent.DamageCause cause){
        DamageCancelContext context;
        if(entity instanceof Player){
            context = new DamageCancelContext(new PlayerDamageCancelStrategy());
        }else{
            context = new DamageCancelContext(new EntityDamageCancelStrategy());
        }
        return context.shouldCancel(entity, cause, plugin.getConfig());
    }
}
