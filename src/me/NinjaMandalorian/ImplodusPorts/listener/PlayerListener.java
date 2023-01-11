package me.NinjaMandalorian.ImplodusPorts.listener;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.NinjaMandalorian.ImplodusPorts.object.Port;
import me.NinjaMandalorian.ImplodusPorts.ui.BaseButton;
import me.NinjaMandalorian.ImplodusPorts.ui.BaseMenu;
import me.NinjaMandalorian.ImplodusPorts.ui.PortMenu;

public class PlayerListener implements Listener {
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        if (block == null) return;
        
        if (block.getType().toString().contains("SIGN")) {
            Sign sign = (Sign) block.getState();
            if (!ChatColor.stripColor(sign.getLine(0)).equals("[Port]")) return;
            Port port = Port.getPort(block.getLocation());
            if (port == null) return;
            e.setCancelled(true);
            
            Action action = e.getAction();
            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                PortMenu.createPortMenu(player, port).open(player);
                return;
            }
        }
    }
}
