package de.kaktus4.easyconfig.impl;

import de.kaktus4.easyconfig.structure.NameableRegistry;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;

public class ConfigRegistry extends NameableRegistry<Config> {

    public ConfigRegistry(JavaPlugin javaPlugin, String packageToScan) {
        try (ScanResult scanResult = new ClassGraph()
                .enableClassInfo()
                .acceptPackages(packageToScan)
                .scan()) {

            scanResult.getSubclasses(Config.class).forEach(classInfo -> {
                try {
                    Class<?> aClass = classInfo.loadClass();
                    Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
                    Config config = (Config) declaredConstructor.newInstance();

                    String configName = config.getName();

                    Config config1 = (Config) aClass
                            .getDeclaredConstructor(JavaPlugin.class, String.class)
                            .newInstance(javaPlugin, configName);

                    register(config1);
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            });
        }

        getObjects().forEach(Config::onStart);
    }

    public void reload() {
        getObjects().forEach(Config::reload);
    }

}