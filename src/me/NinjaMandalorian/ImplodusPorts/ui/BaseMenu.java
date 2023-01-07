package me.NinjaMandalorian.ImplodusPorts.ui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BaseMenu implements InventoryHolder {
    
    private Inventory inventory;
    
    public BaseMenu(Inventory inventory, String menuTitle) {
        // Re-creates inventory with BaseMenu as holder
        this.inventory = Bukkit.createInventory(this, inventory.getSize());
        this.inventory.setContents(inventory.getContents());
    }
    
    @Override
    public Inventory getInventory() {
        return null;
    }
    
    
}
