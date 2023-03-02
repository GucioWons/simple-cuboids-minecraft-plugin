package com.guciowons.simplecuboids.cuboid.building.breaking.strategies;

import com.guciowons.simplecuboids.cuboid.Cuboid;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BreakingNormalStrategy implements BreakingBlockStrategy{
    @Override
    public boolean shouldCancel(Cuboid cuboid, Player player, Location location) {
        return !cuboid.getPlayer().getUniqueId().equals(player.getUniqueId());
    }
}
