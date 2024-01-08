package com.guciowons.simplecuboids.database;

import com.guciowons.simplecuboids.files.DatabaseConfig;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if(entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            Map<String, Object> properties = new HashMap<>();
            properties.put("javax.persistence.jdbc.url", getMysqlUrl());
            properties.put("javax.persistence.jdbc.user", getUser());
            properties.put("javax.persistence.jdbc.password", getPassword());
            System.out.println(properties.get("javax.persistence.jdbc.url"));
            System.out.println(properties.get("javax.persistence.jdbc.user"));
            System.out.println(properties.get("javax.persistence.jdbc.password"));
            entityManagerFactory = Persistence.createEntityManagerFactory("simple_cuboids", properties);
        }
        return entityManagerFactory;
    }

    private static String getMysqlUrl() {
        return "jdbc:mysql:" + DatabaseConfig.getDatabaseConfig().getString("url") + "/simple_cuboids";
    }

    private static String getUser() {
        return DatabaseConfig.getDatabaseConfig().getString("user");
    }

    private static String getPassword() {
        String password = DatabaseConfig.getDatabaseConfig().getString("password");
        return "";
    }

    public static void closeEntityManagerFactory() {
        if(entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
