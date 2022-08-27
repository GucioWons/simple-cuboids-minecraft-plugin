package com.guciowons.simplecuboids.cuboid;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CuboidCreateListener implements Listener {
    private List<Cuboid> cuboids = new ArrayList<>();

    @EventHandler
    public void onCuboidPlace(BlockPlaceEvent e){
        Block placedBlock = e.getBlockPlaced();
        ItemMeta placedBlockMeta = e.getItemInHand().getItemMeta();
        if(placedBlock.getType() == Material.SPONGE){
            if(placedBlockMeta.getDisplayName().equals("Cuboid")){
                e.setCancelled(createCuboid(e.getPlayer(), placedBlock));
            }
        }
    }

    private boolean createCuboid(Player player, Block placedBlock){
        if(!playerHasCuboid(player)) {
            cuboids.add(
                    new Cuboid(placedBlock.getX(), placedBlock.getY(), placedBlock.getZ(), player, 20));
            player.sendMessage("Cuboid created!");
            return false;
        }
        else {
            player.sendMessage("You already have cuboid!");
            return true;
        }
    }

    private boolean playerHasCuboid(Player player) {
        return cuboids.stream()
                .anyMatch(cuboid -> cuboid.getPlayer().equals(player));
    }
}
