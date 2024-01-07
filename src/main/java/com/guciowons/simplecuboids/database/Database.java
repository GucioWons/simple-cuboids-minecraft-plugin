package com.guciowons.simplecuboids.database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Database {
    private static EntityManagerFactory entityManagerFactory;

    private static EntityManagerFactory getEntityManagerFactory() {
        if(entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            entityManagerFactory = Persistence.createEntityManagerFactory("");
        }
        return entityManagerFactory;
    }

    private static void closeEntityManagerFactory() {
        if(entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
