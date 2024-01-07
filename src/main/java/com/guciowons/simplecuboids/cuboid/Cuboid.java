package com.guciowons.simplecuboids.cuboid;

import jakarta.persistence.*;

@Entity
@Table(name = "Cuboid")
public class Cuboid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int locationX;
    private int locationY;
    private int locationZ;
    private String playerId;
    private int size;

    public Cuboid() {
    }

    public Cuboid(int locationX, int locationY, int locationZ, String playerId, int size) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.playerId = playerId;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public int getLocationZ() {
        return locationZ;
    }

    public void setLocationZ(int locationZ) {
        this.locationZ = locationZ;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayer(String playerId) {
        this.playerId = playerId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
