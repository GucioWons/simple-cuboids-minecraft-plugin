package com.guciowons.simplecuboids;

import com.guciowons.simplecuboids.cuboid.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleCuboids extends JavaPlugin {

    @Override
    public void onEnable() {
        CuboidRepository cuboidRepository = new CuboidRepository();
        Bukkit.getPluginManager().registerEvents(new CuboidCreateListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidDestroyListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidBuildingListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidExplosionListener(cuboidRepository), this);
        Bukkit.getPluginManager().registerEvents(new CuboidPlayerDamageListener(cuboidRepository), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
