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
        return entityManager.createQuery("""
                        SELECT c FROM Cuboid c
                        WHERE c.locationX = :blockX AND c.locationY = :blockY AND c.locationZ = :blockZ
                        """, Cuboid.class)
                .setParameter("blockX", blockX)
                .setParameter("blockY", blockY)
                .setParameter("blockZ", blockZ)
                .getResultStream()
                .findFirst();
    }

    public boolean isBlockAtCuboid(int blockX, int blockZ){
        return !entityManager.createQuery("""
                        SELECT c FROM Cuboid c
                        WHERE c.locationX - :blockX <= c.size
                        AND c.locationZ - :blockZ <= c.size
                        """, Cuboid.class)
                .setParameter("blockX", blockX)
                .setParameter("blockZ", blockZ)
                .getResultList()
                .isEmpty();
    }

    public Optional<Cuboid> getBlockAtCuboid(int blockX, int blockZ){
        return entityManager.createQuery("""
                        SELECT c FROM Cuboid c
                        WHERE c.locationX - :blockX <= c.size
                        AND c.locationZ - :blockZ <= c.size
                        """, Cuboid.class)
                .setParameter("blockX", blockX)
                .setParameter("blockZ", blockZ)
                .getResultStream()
                .findFirst();
    }
}
