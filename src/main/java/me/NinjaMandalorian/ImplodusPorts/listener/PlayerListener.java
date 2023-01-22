package me.NinjaMandalorian.ImplodusPorts.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;
import me.NinjaMandalorian.ImplodusPorts.handler.TravelHandler;
import me.NinjaMandalorian.ImplodusPorts.object.Port;
import me.NinjaMandalorian.ImplodusPorts.settings.Settings;
import me.NinjaMandalorian.ImplodusPorts.ui.PortMenu;

public class PlayerListener implements Listener {
    
    ArrayList<Player> warnedPlayers = new ArrayList<Player>();
    
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
            
            Action action = e.getAction();
            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                e.setCancelled(true);
                PortMenu.createPortMenu(player, port).open(player);
                return;
            } else if (action.equals(Action.LEFT_CLICK_BLOCK)) {
                if (!player.hasPermission("implodusports.admin.destroy")) {
                    // Preserve sign text
                    e.setCancelled(true);
                }
                return;
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Port port = TravelHandler.getCurrentPort(player);
        if (port == null) return;
        Double distance = port.getSignLocation().distance(e.getTo());
        Double maxDistance = Settings.getWalkRadius(port.getSize());
        
        if (distance < maxDistance * .8) return;
                
        if (distance > maxDistance) {
            TravelHandler.cancelJourney(player);
        } else {
            if (!warnedPlayers.contains(player)) {
                player.sendMessage(ChatColor.RED + "You are nearing the edge of the port. Leaving will cancel your journey.");
                warnedPlayers.add(player);
                Bukkit.getScheduler().scheduleSyncDelayedTask(ImplodusPorts.getInstance(), () -> {warnedPlayers.remove(player);} ,100L);
            }
        }
    }
}
