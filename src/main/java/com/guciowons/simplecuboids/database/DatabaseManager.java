package com.guciowons.simplecuboids.database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseManager {
    private static EntityManagerFactory entityManagerFactory;

    private static final String url= "localhost:3306/simple_cuboids";

    public static EntityManagerFactory getEntityManagerFactory() {
        if(entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            entityManagerFactory = Persistence.createEntityManagerFactory("simple_cuboids");
        }
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        if(entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
