package me.NinjaMandalorian.ImplodusPorts;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.NinjaMandalorian.ImplodusPorts.command.ImplodusPortsCommands;
import me.NinjaMandalorian.ImplodusPorts.data.DataManager;
import me.NinjaMandalorian.ImplodusPorts.data.PortDataManager;
import me.NinjaMandalorian.ImplodusPorts.listener.SignListener;
import me.NinjaMandalorian.ImplodusPorts.listener.BlockListener;
import me.NinjaMandalorian.ImplodusPorts.listener.InventoryListener;
import me.NinjaMandalorian.ImplodusPorts.listener.PlayerListener;
import me.NinjaMandalorian.ImplodusPorts.object.Port;
import me.NinjaMandalorian.ImplodusPorts.settings.Settings;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.milkbowl.vault.economy.Economy;

/**
 * Main class for ImplodusPorts plugin
 * @author NinjaMandalorian
 *
 */
public class ImplodusPorts extends JavaPlugin {
    
    private static ImplodusPorts instance;
    public static Economy econ;
    
    private BukkitAudiences adventure;
    
    public void onEnable() {
        instance = this;
        
        adventure = BukkitAudiences.create(instance);
        
        // Initialise parts of the plugin
        DataManager.init();
        Settings.init();
        Port.initPorts();
        setupEconomy();
        
        new ImplodusPortsCommands();
        
        PluginManager PluginManager = getServer().getPluginManager();
        PluginManager.registerEvents(new InventoryListener(), instance);
        PluginManager.registerEvents(new PlayerListener(), instance);
        PluginManager.registerEvents(new SignListener(), instance);
        PluginManager.registerEvents(new BlockListener(), instance);
    }
    
    public void onDisable() {
        Bukkit.getLogger().info("Disabling ImplodusPorts");
        PortDataManager.savePortData(Port.getPorts());;
    }
    
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    public static ImplodusPorts getInstance() {
        return instance;
    }
    
    public static Economy getEconomy() {
        return econ;
    }

    public BukkitAudiences getAdventure() {
        return adventure;
    }
    
}
