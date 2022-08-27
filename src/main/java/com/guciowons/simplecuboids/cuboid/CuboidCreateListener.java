package com.guciowons.simplecuboids.cuboid;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class CuboidCreateListener implements Listener {
    @EventHandler
    public void onCuboidPlace(BlockPlaceEvent e){
        Block placedBlock = e.getBlockPlaced();
        ItemStack placedBlockItem = e.getItemInHand();
        if(placedBlock.getType() == Material.SPONGE){
            System.out.println("dupa");
        }
    }
}
