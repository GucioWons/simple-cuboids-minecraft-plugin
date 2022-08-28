package com.guciowons.simplecuboids;

import com.guciowons.simplecuboids.cuboid.CuboidCreateListener;
import com.guciowons.simplecuboids.cuboid.CuboidDestroyListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleCuboids extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new CuboidCreateListener(), this);
        Bukkit.getPluginManager().registerEvents(new CuboidDestroyListener(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
