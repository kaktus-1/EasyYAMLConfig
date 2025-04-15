package de.kaktus4.rpg.config;

import de.kaktus4.rpg.RPGCore;
import de.kaktus4.rpg.structure.INameable;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

@Getter
@Setter
public class Config implements INameable {

    private String name;
    private File file;
    private FileConfiguration cfg;

    public Config(String name) {
        this.name = name;
        this.file = new File(RPGCore.getInstance().getDataFolder(), name + ".yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
    }

    public void reload() {
        this.file = new File(RPGCore.getInstance().getDataFolder(), name + ".yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
    }

    @SneakyThrows
    public void save() {
        cfg.save(file);
    }

    public void onStart() {

    }

}