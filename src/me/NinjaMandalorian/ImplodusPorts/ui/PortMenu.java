package me.NinjaMandalorian.ImplodusPorts.ui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import me.NinjaMandalorian.ImplodusPorts.object.Port;
import me.NinjaMandalorian.ImplodusPorts.ui.tasks.MessageTask;

public class PortMenu {

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
        
        
        int portCount = 0;
        for (Port availablePort : port.getNearby()) {
            List<Integer> slotList = Arrays.asList(
            10,11,12,13,14,15,16,
            19,20,21,22,23,24,25,
            28,29,30,31,32,33,34,
            37,38,39,40,41,42,43);
            
            int slot = slotList.get(portCount);
            
            builder = builder.setButton(slot, portToButton(port, availablePort));
            portCount++;
        }
        
        return builder.build();
        BaseMenu builderMenu = BaseMenu.createBuilder()
                .setButton(0, BaseButton.create(Material.ACACIA_BOAT).glow().task(new MessageTask("Boing")))
                .title("Bingle bongle")
                .rows(5)
                .build();
        
        return builderMenu;
            
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
