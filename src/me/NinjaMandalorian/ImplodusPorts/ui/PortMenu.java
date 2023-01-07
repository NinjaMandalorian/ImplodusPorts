package me.NinjaMandalorian.ImplodusPorts.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinjaMandalorian.ImplodusPorts.object.Port;
import me.NinjaMandalorian.ImplodusPorts.ui.tasks.MessageTask;

public class PortMenu {

    public static BaseMenu createPortMenu(Player player, Port port) {
        
        BaseMenu builderMenu = BaseMenu.createBuilder()
                .setButton(0, new BaseButton(
                        new ItemStack(Material.BIRCH_BOAT), new MessageTask("Birch boat!"))
                        )
                .title("Bingle bongle")
                .rows(5)
                .build();
        
        return builderMenu;
            
    }
    
}
