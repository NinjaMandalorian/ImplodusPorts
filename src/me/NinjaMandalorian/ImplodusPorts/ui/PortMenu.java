package me.NinjaMandalorian.ImplodusPorts.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;
import me.NinjaMandalorian.ImplodusPorts.helper.PortHelper;
import me.NinjaMandalorian.ImplodusPorts.object.Port;
import me.NinjaMandalorian.ImplodusPorts.settings.Settings;
import me.NinjaMandalorian.ImplodusPorts.ui.BaseMenu.Builder;
import me.NinjaMandalorian.ImplodusPorts.ui.tasks.CommandTask;
import me.NinjaMandalorian.ImplodusPorts.ui.tasks.InventoryTask;
import me.NinjaMandalorian.ImplodusPorts.ui.tasks.JourneyTask;
import net.md_5.bungee.api.ChatColor;

public class PortMenu {

    /**
     * Creates the base menu for a port, showing all travel-able ports & a button to open global menu. 
     * @param player - Player opening menu
     * @param port - Port to generate menu from
     * @return BaseMenu of port
     */
    public static BaseMenu createPortMenu(Player player, Port port) {
        
        Builder builder = BaseMenu.createBuilder()
                .setButton(4, portToButton(port, port))
                .setButton(49, BaseButton.create(Material.OAK_CHEST_BOAT).glow()
                        .name("&o&aSee all ports")
                        .task(new InventoryTask(createPortGlobalMenu(player, port))))
                .title("&9Port - " + port.getDisplayName())
                .openMsg("&aOpening port &9" + port.getDisplayName() + "&a...")
                .fillOutline()
                ;
        
        List<Integer> slotList = Arrays.asList(
                10,11,12,13,14,15,16,
                19,20,21,22,23,24,25,
                28,29,30,31,32,33,34,
                37,38,39,40,41,42,43);
        
        int portCount = 0;
        for (Port availablePort : PortHelper.orderPorts(port, port.getNearby())) {
            
            int slot = slotList.get(portCount);
            
            builder = builder.setButton(slot, portToButton(port, availablePort));
            portCount++;
        }
        
        return builder.build();
            
    }
    
    /**
     * Creates the global menu for a port, where every public port is in view 
     * @param player - Player opening menu
     * @param port - Port to generate menu from
     * @return BaseMenu of port
     */
    public static BaseMenu createPortGlobalMenu(Player player, Port port) {

        BaseMenu builderMenu = BaseMenu.createBuilder()
                .setButton(4, portToButton(port, port))
                .title("&cGlobal Port Menu")
                .openMsg("&aOpening &cGlobal Port Menu&a...")
                .fillOutline()
                .build();
        
        return builderMenu;
    }
    
    public static BaseButton portToButton(Port currentPort, Port port) {
        BaseButton portButton = BaseButton.create();
        
        if (currentPort.equals(port)) {
            // Creating button for current port.
            portButton = portButton.itemStack(new ItemStack(Port.getIcon(port.getSize())));
            portButton = portButton.glow().name(port.getDisplayName());
            
            List<String> lore = Arrays.asList(
                    ChatColor.GOLD + "Size: " + portSizeString(port)
                    );
            
            portButton = portButton.lore(lore).task(null);
            
        } else {
            // Creating button for travel-able port.
            portButton = portButton.itemStack(new ItemStack(Port.getIcon(port.getSize())));
            portButton = portButton.name(port.getDisplayName());
            
            int boatSize = Math.min(currentPort.getSize(), port.getSize());
            Map<String, Object> boatMap = Settings.getSizeMap(boatSize);
            
            List<String> lore = Arrays.asList(
                    ChatColor.GOLD + "Size: " + portSizeString(port),
                    ChatColor.GOLD + "Travel Time: ",
                    ChatColor.GOLD + "Cost: " + ImplodusPorts.econ.format(Double.valueOf((Integer) boatMap.get("cost"))),
                    ChatColor.GREEN + "Click to Travel"
                    );
            
            portButton = portButton.lore(lore);
            portButton = portButton.task(new JourneyTask(currentPort, port));
        }
        
        return portButton;
    }
    
    /**
     * Returns string correlating to port size. Includes color.
     * @param port - Port size integer
     * @return Port size string
     */
    public static String portSizeString(Port port) {
        switch(port.getSize()) {
        case 1:
            return ChatColor.AQUA + "Jetty";
        case 2:
            return ChatColor.DARK_AQUA + "Dock";
        case 3:
            return ChatColor.YELLOW + "Harbour";
        case 4:
            return ChatColor.DARK_GREEN + "Megaport";
        }
        return "";
    }
    
}
