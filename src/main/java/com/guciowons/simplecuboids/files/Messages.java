package com.guciowons.simplecuboids.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Messages {
    private static File file;
    private static FileConfiguration messagesFile;

    public static void setUp(){
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("SimpleCuboids")).getDataFolder(), "messages.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                //TODO
            }
        }
        messagesFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getMessagesFile(){
        return messagesFile;
    }

    public static void saveMessages(){
        try {
            messagesFile.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
