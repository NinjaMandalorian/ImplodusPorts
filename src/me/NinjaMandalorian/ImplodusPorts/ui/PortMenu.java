package me.NinjaMandalorian.ImplodusPorts.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinjaMandalorian.ImplodusPorts.object.Port;

public class PortMenu {

    public static BaseMenu createPortMenu(Player player, Port port) {
        
        
        Inventory inventory = Bukkit.createInventory(null, 54);
        inventory.setItem(4, new ItemStack(Material.ACACIA_BOAT));
        
        return new BaseMenu(inventory, "test-title");
            
    }
    
}
