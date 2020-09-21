package org.l2x9.infernobox;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.l2x9.infernobox.commands.BaseCommand;
import org.l2x9.infernobox.events.PlayerJoin;
import org.l2x9.infernobox.events.PlayerMove;
import org.l2x9.infernobox.events.PlayerQuit;

import java.io.File;
import java.io.IOException;

public final class Infernobox extends JavaPlugin {
    File dyml = new File(getDataFolder(), "data.yml");
    FileConfiguration datayml = new YamlConfiguration();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
        getCommand("infernobox").setExecutor(new BaseCommand(this));
        saveDefaultConfig();
        makeData();
    }

    private void makeData() {
        try {
            if (!dyml.exists()) {
                dyml.createNewFile();
            }
        } catch (IOException ignored) {
        }
        setDyml();
    }

    private void setDyml() {
        try {
            datayml.load(dyml);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    public FileConfiguration getData() {
        return datayml;
    }

    public void saveData() {
        try {
            datayml.save(dyml);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
