package me.NinjaMandalorian.ImplodusPorts.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {
    
    
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() == null) return;
        Bukkit.getLogger().info( e.getInventory().getHolder().getClass().toString() );
        e.getInventory();
    }
}
