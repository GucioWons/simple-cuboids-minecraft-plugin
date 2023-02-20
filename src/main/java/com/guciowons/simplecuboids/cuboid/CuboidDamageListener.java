package com.guciowons.simplecuboids.cuboid;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CuboidDamageListener implements Listener {
    private final CuboidRepository cuboidRepository;

    public CuboidDamageListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){
            e.setCancelled(onPlayerDamage(e.getEntity().getLocation(), (Player) e.getEntity()));
        }else{
            e.setCancelled(onEntityDamage(e.getEntity().getLocation(), e.getDamager()));
        }
    }

    private boolean onPlayerDamage(Location l, Player p){
        return cuboidRepository.getBlockAtCuboid(l.getBlockX(), l.getBlockZ())
                .filter(cuboid -> cuboid.getPlayer().getUniqueId().equals(p.getUniqueId())).isPresent();
    }

    private boolean onEntityDamage(Location l, Entity damager){
        return cuboidRepository.getBlockAtCuboid(l.getBlockX(), l.getBlockZ())
                .map(cuboid ->onEntityDamageByEntity(damager, cuboid)).orElse(true);
    }

    private boolean onEntityDamageByEntity(Entity damager, Cuboid cuboid){
        if(damager instanceof  Player){
            return onEntityDamageByPlayer((Player) damager, cuboid);
        }else{
            return true;
        }
    }

    private boolean onEntityDamageByPlayer(Player damager, Cuboid cuboid){
        return !cuboid.getPlayer().getUniqueId().equals(damager.getUniqueId());
    }
}
