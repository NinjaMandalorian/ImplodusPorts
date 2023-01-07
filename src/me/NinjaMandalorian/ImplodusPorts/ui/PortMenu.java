package me.NinjaMandalorian.ImplodusPorts.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PortMenu {

    public static BaseMenu createPortMenu() {
        
        Inventory inventory = Bukkit.createInventory(null, 54);
        inventory.setItem(4, new ItemStack(Material.ACACIA_BOAT));
        
        return new BaseMenu(inventory, "test-title");
            
    }
    
}
