package com.guciowons.simplecuboids.cuboid;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuboidRepository {
    private static List<Cuboid> cuboids = new ArrayList<>();

    public static void createCuboid(Cuboid cuboid){
        cuboids.add(cuboid);
    }

    public static void deleteCuboid(Cuboid cuboid) {
        cuboids.remove(cuboid);
    }

    public static boolean playerHasCuboid(Player player){
        return cuboids.stream()
                .anyMatch(cuboid -> cuboid.getPlayer().equals(player));
    }

    public static Optional<Cuboid> getCuboidByLocation(int blockX, int blockY, int blockZ) {
        return cuboids.stream()
                .filter(cuboid -> cuboid.getLocationX() == blockX &&
                        cuboid.getLocationY() == blockY &&
                        cuboid.getLocationZ() == blockZ)
                .findFirst();
    }

    public static Optional<Cuboid> isBlockAtCuboid(int blockX, int blockZ){
        return cuboids.stream()
                .filter(cuboid -> Math.abs(cuboid.getLocationX() - blockX) <= cuboid.getSize() &&
                        Math.abs(cuboid.getLocationZ() - blockZ) <= cuboid.getSize())
                .findFirst();
    }
}
