package me.NinjaMandalorian.ImplodusPorts.handler;

import org.bukkit.entity.Player;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.md_5.bungee.api.ChatColor;

public class MessageHandler {
    
    /**
     * Sends message to the player to for going to next port. 
     * @param player
     */
    public static void sendJourneyNext(Player player) {
        BukkitAudiences adventure = ImplodusPorts.getInstance().getAdventure();
        Audience audience = adventure.player(player);
        
        Component component = Component.text(ChatColor.translateAlternateColorCodes('&', "&f[&aNext Port&f]"))
                .hoverEvent(HoverEvent.showText(Component.text("Click to begin travelling...")))
                .clickEvent(ClickEvent.runCommand("/implodusports:iports next"));
        
        audience.sendMessage(component);
    }
    
}
