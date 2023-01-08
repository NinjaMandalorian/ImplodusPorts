package me.NinjaMandalorian.ImplodusPorts;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.NinjaMandalorian.ImplodusPorts.command.ImplodusPortsCommands;
import me.NinjaMandalorian.ImplodusPorts.data.DataManager;
import me.NinjaMandalorian.ImplodusPorts.listener.InventoryListener;
import me.NinjaMandalorian.ImplodusPorts.settings.Settings;

/**
 * Main class for ImplodusPorts plugin
 * @author NinjaMandalorian
 *
 */
public class ImplodusPorts extends JavaPlugin {
    
    private static ImplodusPorts instance;
    
    public void onEnable() {
        instance = this;
        
        // Initialise parts of the plugin
        DataManager.init();
        Settings.init();
        
        new ImplodusPortsCommands();
        
        PluginManager PluginManager = getServer().getPluginManager();
        PluginManager.registerEvents(new InventoryListener(), instance);
    }
    
    public void onDisable() {
        Bukkit.getLogger().info("Disabling ImplodusPorts");
    }
    
    public static ImplodusPorts getInstance() {
        return instance;
    }
    
}
