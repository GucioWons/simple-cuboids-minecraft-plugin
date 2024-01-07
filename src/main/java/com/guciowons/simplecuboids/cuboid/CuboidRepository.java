package com.guciowons.simplecuboids.cuboid;

import jakarta.persistence.EntityManager;

import java.util.Optional;

public class CuboidRepository {
    private final EntityManager entityManager;

    public CuboidRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createCuboid(Cuboid cuboid){
        entityManager.persist(cuboid);
    }

    public void deleteCuboid(Cuboid cuboid) {
        entityManager.remove(cuboid);
    }

    public boolean playerHasCuboid(String playerId) {
        return !entityManager.createQuery("""
                        SELECT c FROM Cuboid c
                        WHERE c.playerId = :playerId
                        """, Cuboid.class)
                .setParameter("playerId", playerId)
                .getResultList()
                .isEmpty();
    }

    public Optional<Cuboid> getCuboidByLocation(int blockX, int blockY, int blockZ) {
        return Optional.empty();
//                cuboids.stream()
//                .filter(cuboid -> cuboid.getLocationX() == blockX &&
//                        cuboid.getLocationY() == blockY &&
//                        cuboid.getLocationZ() == blockZ)
//                .findFirst();
    }

    public boolean isBlockAtCuboid(int blockX, int blockZ){
        return false;
//                cuboids.stream().anyMatch(cuboid -> Math.abs(cuboid.getLocationX() - blockX) <= cuboid.getSize() &&
//                Math.abs(cuboid.getLocationZ() - blockZ) <= cuboid.getSize());
    }

    public Optional<Cuboid> getBlockAtCuboid(int blockX, int blockZ){
        return Optional.empty();
//                cuboids.stream()
//                .filter(cuboid -> Math.abs(cuboid.getLocationX() - blockX) <= cuboid.getSize() &&
//                        Math.abs(cuboid.getLocationZ() - blockZ) <= cuboid.getSize())
//                .findFirst();
    }
}
