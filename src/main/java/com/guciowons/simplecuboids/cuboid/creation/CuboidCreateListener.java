package com.guciowons.simplecuboids.cuboid.creation;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import com.guciowons.simplecuboids.files.Messages;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class CuboidCreateListener implements Listener {
    private final CuboidRepository cuboidRepository;

    public CuboidCreateListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }

    @EventHandler
    public void onCuboidPlace(BlockPlaceEvent e){
        Block placedBlock = e.getBlockPlaced();
        ItemMeta placedBlockMeta = e.getItemInHand().getItemMeta();
        if(placedBlock.getType() == Material.SPONGE && placedBlockMeta != null){
            e.setCancelled(checkBlockMeta(placedBlockMeta, e.getPlayer(), placedBlock));
        }
    }

    private boolean checkBlockMeta(ItemMeta placedBlockMeta, Player player, Block placedBlock){
        if(placedBlockMeta.getDisplayName().equals("Cuboid")){
            return createCuboid(player, placedBlock);
        }else{
            return false;
        }
    }

    private boolean createCuboid(Player player, Block placedBlock){
        if(!cuboidRepository.playerHasCuboid(player)) {
            cuboidRepository.createCuboid(
                    new Cuboid(placedBlock.getX(), placedBlock.getY(), placedBlock.getZ(), player, 5));
            player.sendMessage(Objects.requireNonNull(Messages.getMessagesFile().getString("Cuboid created")));
            return false;
        }
        else {
            player.sendMessage(Objects.requireNonNull(Messages.getMessagesFile().getString("Cuboid exists")));
            return true;
        }
    }
}
