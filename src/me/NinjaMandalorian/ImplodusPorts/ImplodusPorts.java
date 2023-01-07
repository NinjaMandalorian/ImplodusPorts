package me.NinjaMandalorian.ImplodusPorts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.NinjaMandalorian.ImplodusPorts.command.ImplodusPortsCommands;
import me.NinjaMandalorian.ImplodusPorts.data.DataManager;
import me.NinjaMandalorian.ImplodusPorts.object.IconMenu;
import me.NinjaMandalorian.ImplodusPorts.settings.Settings;

public class ImplodusPorts extends JavaPlugin {
    
    private static ImplodusPorts instance;
    
    public void onEnable() {
        instance = this;
        
        // Initialise parts of the plugin
        DataManager.init();
        Settings.init();
        
        new ImplodusPortsCommands();
    }
    
    public void onDisable() {
        Bukkit.getLogger().info("Disabling ImplodusPorts");
    }
    
    public static ImplodusPorts getInstance() {
        return instance;
    }
    
}
