package me.NinjaMandalorian.ImplodusPorts.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.NinjaMandalorian.ImplodusPorts.ui.BaseMenu;

public class InventoryListener implements Listener {
    
    /**
     * Handles inventories being clicked --> If inventory held by BaseMenu, corresponding task is run.
     * @param e - Event
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof BaseMenu menu) {
            e.setCancelled(true); // Immediately cancels any movement of items.
            
            // If outside of custom inventory, returns.
            int slotNum = e.getRawSlot();
            if (slotNum + 1 > e.getInventory().getSize()) return;
            
            menu.getButton(slotNum).getTask().run(e);
        } else return;
    }
}
