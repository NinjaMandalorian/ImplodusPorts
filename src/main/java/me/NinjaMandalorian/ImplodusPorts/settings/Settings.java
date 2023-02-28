package me.NinjaMandalorian.ImplodusPorts.settings;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;

public class Settings {
	
	
    
    private static ImplodusPorts plugin;
    private static FileConfiguration config;
    
    public static Map<String, Object> small;
    public static Map<String, Object> medium;
    public static Map<String, Object> large;
    public static Map<String, Object> mega;
    
    public static void init() {
    	
        plugin = ImplodusPorts.getInstance();
        
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        
        small = sectionToMap((MemorySection) config.getConfigurationSection("sizes.small"));
        medium = sectionToMap((MemorySection) config.getConfigurationSection("sizes.medium"));
        large = sectionToMap((MemorySection) config.getConfigurationSection("sizes.large"));
        mega = sectionToMap((MemorySection) config.getConfigurationSection("sizes.mega"));
        
        
    }
 
    
    public static Double getBaseCost() {
        return config.getDouble("basecost");
    }
    
    public static Map<String, Object> getSizeMap(int size) {
        switch(size) {
        case 1:
            return small;
        case 2:
            return medium;
        case 3:
            return large;
        case 4:
            return mega;
        }
        return null;
    }
    
    public static Double getSizeDistance(int size) {
        return (Double) getSizeMap(size).get("distance");
    }
    
    public static Double getWalkRadius(int size) {
        return (Double) getSizeMap(size).get("walk_radius");
    }
    
    
    /**
     * Reloads the config with the internal config.yml
     */
    public static void reloadConfig() {
    	ImplodusPorts.getInstance().reloadConfig();
    	ImplodusPorts.getInstance().saveDefaultConfig();
    	
    	small = sectionToMap((MemorySection) config.getConfigurationSection("sizes.small"));
        medium = sectionToMap((MemorySection) config.getConfigurationSection("sizes.medium"));
        large = sectionToMap((MemorySection) config.getConfigurationSection("sizes.large"));
        mega = sectionToMap((MemorySection) config.getConfigurationSection("sizes.mega"));
    }
    

    private static Map<String, Object> sectionToMap(MemorySection section) {
        HashMap<String, Object> map = (HashMap<String, Object>) section.getValues(true);
        
        for (Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof MemorySection) map.remove(entry.getKey());
        }
        
        return map;
    }

}
