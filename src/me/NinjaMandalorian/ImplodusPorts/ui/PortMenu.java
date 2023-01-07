package me.NinjaMandalorian.ImplodusPorts.ui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import me.NinjaMandalorian.ImplodusPorts.object.Port;
import me.NinjaMandalorian.ImplodusPorts.ui.tasks.MessageTask;

public class PortMenu {

    public static BaseMenu createPortMenu(Player player, Port port) {
        
        BaseMenu builderMenu = BaseMenu.createBuilder()
                .setButton(0, BaseButton.create(Material.ACACIA_BOAT).glow().task(new MessageTask("Boing")))
                .title("Bingle bongle")
                .rows(5)
                .build();
        
        return builderMenu;
            
    }
    
}
