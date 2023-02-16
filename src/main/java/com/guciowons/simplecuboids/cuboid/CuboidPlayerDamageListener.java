package com.guciowons.simplecuboids.cuboid;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CuboidPlayerDamageListener implements Listener {
    private final CuboidRepository cuboidRepository;

    public CuboidPlayerDamageListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            Location l = p.getLocation();
            cuboidRepository.getBlockAtCuboid(l.getBlockX(), l.getBlockZ()).ifPresent(cuboid -> {
                if(cuboid.getPlayer().getUniqueId().equals(p.getUniqueId())){
                    e.setCancelled(true);
                }
            });
        }else{
            Location l = e.getEntity().getLocation();
            cuboidRepository.getBlockAtCuboid(l.getBlockX(), l.getBlockZ()).ifPresent(cuboid -> {
                if(e.getDamager() instanceof Player){
                    Player p = (Player) e.getDamager();
                    if(!cuboid.getPlayer().getUniqueId().equals(p.getUniqueId())) {
                        e.setCancelled(true);
                    }else{
                        System.out.println("Player Works!");
                    }
                }else{
                    System.out.println("Entity works");
                    e.setCancelled(true);
                }
            });

        }
    }
}
