package com.guciowons.simplecuboids.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DatabaseConfig {
    private static File file;
    private static FileConfiguration databaseConfig;

    public static void setUp(){
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("SimpleCuboids")).getDataFolder(), "database.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                //TODO
            }
        }
        databaseConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getDatabaseConfig(){
        return databaseConfig;
    }

    public static void saveDatabaseConfig(){
        try {
            databaseConfig.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
