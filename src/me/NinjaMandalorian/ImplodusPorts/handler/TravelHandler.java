package me.NinjaMandalorian.ImplodusPorts.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import me.NinjaMandalorian.ImplodusPorts.object.Port;

// cmd next on click with long "a-->b-->c-->d"
/* if player tries to use port whilst mid travel, shows
 * "You are mid-journey. Are you sure you want to cancel?
 * Cancel travel
 * 
 * For travel function, runs on a delay with the player. map of players and travled is parsed through
 * and if the player leaves the port area, it's just reset to null & does nothing. (Cancel via distance
 * is handled by PlayerListener)
 */

/**
 * Handles all movement of players: stores journeys and teleports
 * @author NinjaMandalorian
 *
 */
public class TravelHandler {

    private static HashMap<Player, ArrayList<Port>> journeys = new HashMap<Player, ArrayList<Port>>();
    
    public static void startJourney(Player player, Port origin, Port destination, String...args) {
        ArrayList<Port> playerJourney = findPath(player, origin, destination);
        
        journeys.put(player, playerJourney);
        next(player);
    }
    
    public static void next(Player player) {
        ArrayList<Port> playerJourney = journeys.get(player);
        if (playerJourney == null) return;
        
        Port currentPort = playerJourney.get(0);
        player.teleport(playerJourney.get(1).getTeleportLocation(), TeleportCause.PLUGIN);
        
        playerJourney.remove(0);
        return;
    }
    
    //private static void teleport
    
    public static ArrayList<Port> findPath(Player player, Port origin, Port destination) {
        return null;
    }
    
}
