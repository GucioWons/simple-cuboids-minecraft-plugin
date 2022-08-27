package com.guciowons.simplecuboids.cuboid;

import org.bukkit.entity.Player;

public class Cuboid {
    private int locationX;
    private int locationY;
    private int locationZ;
    private Player player;
    private int size;

    public Cuboid(int locationX, int locationY, int locationZ, Player player, int size) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.player = player;
        this.size = size;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getLocationZ() {
        return locationZ;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSize() {
        return size;
    }
}
