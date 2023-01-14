package me.NinjaMandalorian.ImplodusPorts.listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import me.NinjaMandalorian.ImplodusPorts.helper.PortHelper;
import me.NinjaMandalorian.ImplodusPorts.object.Port;
import net.md_5.bungee.api.ChatColor;

public class SignListener implements Listener {

    /**
     * Checker for placing ports.
     * @param e - Event
     */
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        
        if (!e.getLine(0).equalsIgnoreCase("[Port]") || e.getLine(1).strip().equalsIgnoreCase("")) return;
        
        if (player.hasPermission("implodusports.admin.create")) {
            Port port = PortHelper.portFromSign(player, block, e.getLines());
            Port.portCreate(player, port);
            return;
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&6iPorts&9] &cYou do not have permission to create a port."));
            e.setCancelled(true);
            return;
        }
    }
    
}
