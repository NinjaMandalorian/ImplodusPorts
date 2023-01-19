package me.NinjaMandalorian.ImplodusPorts.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;
import me.NinjaMandalorian.ImplodusPorts.Logger;
import me.NinjaMandalorian.ImplodusPorts.object.Port;
import me.NinjaMandalorian.ImplodusPorts.settings.Settings;
import net.md_5.bungee.api.ChatColor;

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
    private static ArrayList<Player> enroutePlayers = new ArrayList<Player>();
    
    public static void startJourney(Player player, Port origin, Port destination, String...args) {
        Logger.debug(player.getName() + " RUN PORT;"+origin.getId());
        ArrayList<Port> playerJourney = findPath(player, origin, destination);
        
        
        
        journeys.put(player, playerJourney);
        scheduleNext(player);
    }
    
    public static void scheduleNext(Player player) {
        if (enroutePlayers.contains(player)) return;
        enroutePlayers.add(player);
        
        Long time = getJourneyWait(player);
        
        Bukkit.getScheduler().scheduleSyncDelayedTask(ImplodusPorts.getInstance(), () -> next(player), time);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou will be teleported in &d" + time/20 + "&a seconds."));
    }
    
    private static void next(Player player) {
        ArrayList<Port> playerJourney = journeys.get(player);
        if (playerJourney == null) return;
        
        Logger.debug("Player " + player.getName() + " departing from " + playerJourney.get(0).getDisplayName());
        
        player.teleport(playerJourney.get(1).getTeleportLocation(), TeleportCause.PLUGIN);
        
        playerJourney.remove(0);
        enroutePlayers.remove(player);
        if (playerJourney.size() > 1) {
            MessageHandler.sendJourneyNext(player);
            journeys.put(player, playerJourney);
        } else {
            journeys.put(player, null);
        }
        return;
    }
    
    private static ArrayList<Port> findPath(Player player, Port origin, Port destination) {
        ArrayList<Port> returnList = new ArrayList<Port>();
        
        returnList.add(origin);
        returnList.add(destination);
        return returnList;
    }
    
    private static Long getJourneyWait(Player player) {
        ArrayList<Port> playerJourney = journeys.get(player);
        
        Long cumulativeTime = 0L;
        for (int i = 1; i < playerJourney.size(); i++) {
            cumulativeTime += getWait(playerJourney.get(i-1), playerJourney.get(i));
        }
        return cumulativeTime;
    }
    
    public static double getJourneyCost(List<Port> playerJourney) {
        Double totalCost = 0.0;
        for (int i = 1; i < playerJourney.size(); i++) {
            totalCost += getTravelCost(playerJourney.get(i-1), playerJourney.get(i));
        }
        
        return totalCost;
    }
    
    public static Double getJourneyCost(Player player) {
        return getJourneyCost(journeys.get(player));
    }
    
    private static Long getWait(Port origin, Port destination) {
        Double distance = origin.distanceTo(destination);
        int size = Math.min(origin.getSize(), destination.getSize());
        Long waitTime = 100L;
        
        Double speed = (Double) Settings.getSizeMap(size).get("speed");
        waitTime += Math.round(distance/speed)*20L;
        
        return waitTime;
    }
    
    public static Double getTravelCost(Port origin, Port destination) {
        int size = Math.min(origin.getSize(), destination.getSize());
        Double cost = Settings.getBaseCost();
        
        Double costRate = (Double) Settings.getSizeMap(size).get("cost");
        cost += costRate * origin.distanceTo(destination) / 100;
        
        return cost;
    }
    
    public static long getTravelTime(Port origin, Port destination) {
        return getWait(origin, destination)/20L;
    }
    
    public static boolean canTravelTo(Port port, Player player) {
        return true;
    }

}
