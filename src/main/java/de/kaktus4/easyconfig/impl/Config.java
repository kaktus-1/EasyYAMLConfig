package de.kaktus4.easyconfig.impl;

import de.kaktus4.easyconfig.structure.INameable;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
@Setter
public class Config implements INameable {

    private String name;

    private File file;
    private FileConfiguration cfg;

    private JavaPlugin javaPlugin;

    public Config(String name) {
        this.name = name;
    }

    public Config(JavaPlugin javaPlugin, String name) {
        this.javaPlugin = javaPlugin;

        this.file = new File(javaPlugin.getDataFolder(), name + ".yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);

        this.name = name;
    }

    public void reload() {
        this.file = new File(javaPlugin.getDataFolder(), name + ".yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
    }

    @SneakyThrows
    public void save() {
        cfg.save(file);
    }

    public void enableCopyDefault() {
        cfg.options().copyDefaults(true);
    }

    public void onStart() {

    }

}