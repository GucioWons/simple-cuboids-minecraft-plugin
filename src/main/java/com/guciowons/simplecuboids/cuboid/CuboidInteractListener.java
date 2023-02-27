package com.guciowons.simplecuboids.cuboid;

import com.guciowons.simplecuboids.SimpleCuboids;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CuboidInteractListener implements Listener {
    private SimpleCuboids plugin = SimpleCuboids.getPlugin(SimpleCuboids.class);
    private final CuboidRepository cuboidRepository;

    public CuboidInteractListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onPlayerDoorOpen(PlayerInteractEvent event) {
        Block interacted = event.getClickedBlock();
        if(interacted != null){
            cuboidRepository.getBlockAtCuboid(interacted.getX(), interacted.getZ())
                    .filter(cuboid -> !cuboid.getPlayer().getUniqueId().equals(event.getPlayer().getUniqueId()))
                    .ifPresent(cuboid -> event.setCancelled(shouldCancelEvent(event.getAction(), interacted)));
        }
    }
    private boolean shouldCancelEvent(Action action, Block interacted){
        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_BLOCK) {
            if(interacted.getType() == Material.CHEST && plugin.getConfig().getBoolean("DisableChestInteractions")){
                return true;
            }
            else return plugin.getConfig().getBoolean("DisableOtherInteractions");
        }else if(action == Action.PHYSICAL){
            if(interacted.getType() == Material.FARMLAND && plugin.getConfig().getBoolean("DisableFarmInteractions")){
                return true;
            }else return plugin.getConfig().getBoolean("DisablePhysicalInteractions");
        }
        return false;
    }
}
