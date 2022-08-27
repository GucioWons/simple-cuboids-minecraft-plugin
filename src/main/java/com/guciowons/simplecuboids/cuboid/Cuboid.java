package com.guciowons.simplecuboids.cuboid;

import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class Cuboid {
    private BigDecimal locationX;
    private BigDecimal locationY;
    private BigDecimal locationZ;
    private Player player;
    private int size;

    public Cuboid(BigDecimal locationX, BigDecimal locationY, BigDecimal locationZ, Player player, int size) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.player = player;
        this.size = size;
    }

    public BigDecimal getLocationX() {
        return locationX;
    }

    public BigDecimal getLocationY() {
        return locationY;
    }

    public BigDecimal getLocationZ() {
        return locationZ;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSize() {
        return size;
    }
}
