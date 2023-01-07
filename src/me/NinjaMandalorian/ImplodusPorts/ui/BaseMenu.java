package me.NinjaMandalorian.ImplodusPorts.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BaseMenu implements InventoryHolder {
    
    private Inventory inventory;
    
    public BaseMenu(Inventory inventory, String menuTitle) {
        // Re-creates inventory with BaseMenu as holder
        this.inventory = Bukkit.createInventory(this, inventory.getSize(), menuTitle);
        this.inventory.setContents(inventory.getContents());
    }
    
    public void open(Player player) {
        player.openInventory(inventory);
    }
    
    @Override
    public Inventory getInventory() {
        return null;
    }
    
    
}
