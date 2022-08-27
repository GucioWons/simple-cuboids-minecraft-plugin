package com.guciowons.simplecuboids.cuboid;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CuboidRepository {
    private static List<Cuboid> cuboids = new ArrayList<>();

    public static void createCuboid(Cuboid cuboid){
        cuboids.add(cuboid);
    }

    public static boolean playerHasCuboid(Player player){
        return cuboids.stream()
                .anyMatch(cuboid -> cuboid.getPlayer().equals(player));
    }

    public static boolean cuboidExists(int blockX, int blockY, int blockZ) {
        return cuboids.stream()
                .anyMatch(cuboid ->
                        cuboid.getLocationX() == blockX &&
                        cuboid.getLocationY() == blockY &&
                        cuboid.getLocationZ() == blockZ);
    }
}
