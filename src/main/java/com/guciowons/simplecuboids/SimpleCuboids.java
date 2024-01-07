package com.guciowons.simplecuboids;

import com.guciowons.simplecuboids.cuboid.*;
import com.guciowons.simplecuboids.cuboid.building.breaking.CuboidBreakingListener;
import com.guciowons.simplecuboids.cuboid.building.placing.CuboidPlacingListener;
import com.guciowons.simplecuboids.cuboid.damage.CuboidDamageListener;
import com.guciowons.simplecuboids.cuboid.interactions.CuboidExplosionListener;
import com.guciowons.simplecuboids.cuboid.interactions.CuboidInteractListener;
import com.guciowons.simplecuboids.cuboid.interactions.CuboidLiquidFlowListener;
import com.guciowons.simplecuboids.cuboid.piston.CuboidPistonExpandListener;
import com.guciowons.simplecuboids.cuboid.piston.CuboidPistonRetractListener;
import com.guciowons.simplecuboids.database.DatabaseManager;
import com.guciowons.simplecuboids.files.DatabaseConfig;
import com.guciowons.simplecuboids.files.Messages;
import jakarta.persistence.EntityManagerFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleCuboids extends JavaPlugin {
    private static EntityManagerFactory entityManagerFactory;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        DatabaseConfig.setUp();
        DatabaseConfig.getMessagesFile().addDefault("url", "example.com");
        DatabaseConfig.getMessagesFile().addDefault("user", "user");
        DatabaseConfig.getMessagesFile().addDefault("password", "password");
        DatabaseConfig.saveMessages();

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        entityManagerFactory = DatabaseManager.getEntityManagerFactory();

        Messages.setUp();
        Messages.getMessagesFile().addDefault("Cuboid created", "Cuboid created!");
        Messages.getMessagesFile().addDefault("Cuboid exists", "You already have cuboid!");
        Messages.getMessagesFile().addDefault("Cuboid destroyed", "Cuboid destroyed!");
        Messages.getMessagesFile().addDefault("Cuboid not yours", "Its not your cuboid!");
        Messages.getMessagesFile().options().copyDefaults(true);
        Messages.saveMessages();

        CuboidRepository cuboidRepository = new CuboidRepository(entityManagerFactory.createEntityManager());
        Bukkit.getPluginManager().registerEvents(new CuboidPlacingListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidBreakingListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidExplosionListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidDamageListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidLiquidFlowListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidPistonRetractListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidPistonExpandListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidInteractListener(cuboidRepository), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        DatabaseManager.closeEntityManagerFactory();
    }
}
