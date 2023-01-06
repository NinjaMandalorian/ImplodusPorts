package me.NinjaMandalorian.ImplodusPorts;

import org.bukkit.plugin.java.JavaPlugin;

public class ImplodusPorts extends JavaPlugin {
    
    private static ImplodusPorts instance;
    
    public void onEnable() {
        instance = this;
    }
    
    public void onDisable() {
        
    }
    
    public static ImplodusPorts getInstance() {
        return instance;
    }
    
}
