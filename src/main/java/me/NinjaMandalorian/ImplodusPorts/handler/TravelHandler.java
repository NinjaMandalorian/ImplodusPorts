package me.NinjaMandalorian.ImplodusPorts.handler;

import java.util.ArrayList;
import java.util.Arrays;
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
 * 
 * @author NinjaMandalorian
 *
 */
public class TravelHandler {

	private static HashMap<Player, List<Port>> journeys = new HashMap<Player, List<Port>>();
	private static ArrayList<Player> enroutePlayers = new ArrayList<Player>();

	/**
	 * Starts a port journey for a player.
	 * 
	 * @param player      - Player doing journey
	 * @param origin      - Start port
	 * @param destination - Destination port
	 * @param args        - Extra arguments
	 */
	public static void startJourney(Player player, Port origin, Port destination, String... args) {
		Logger.debug(player.getName() + " RUN PORT;" + origin.getId());
		ArrayList<Port> playerJourney = findPath(player, origin, destination);

		journeys.put(player, playerJourney);
		scheduleNext(player);
	}

	/**
	 * Cancels port journey for a player.
	 * 
	 * @param player - Player to cancel journey of.
	 */
	public static void cancelJourney(Player player) {
		journeys.remove(player);
		enroutePlayers.remove(player);
		player.sendMessage(ChatColor.RED + "Journey Cancelled.");
	}

	/**
	 * Schedules next port travel.
	 * 
	 * @param player - Player to schedule
	 */

	public static void scheduleNext(Player player) {
		if (enroutePlayers.contains(player))
			return;
		enroutePlayers.add(player);

		Long time = getJourneyWait(player);

		Bukkit.getScheduler().scheduleSyncDelayedTask(ImplodusPorts.getInstance(), () -> next(player), time);
		player.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&aYou will be teleported in &d" + time / 20 + "&a seconds."));
	}

	/**
	 * Runs next port.
	 * 
	 * @param player - Player to run next port.
	 */
	private static void next(Player player) {
		ArrayList<Port> playerJourney = (ArrayList<Port>) journeys.get(player);
		if (playerJourney == null)
			return;

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

	/**
	 * Finds path between two ports for a player.
	 * 
	 * @param player      - Player to find path for.
	 * @param origin      - Start port.
	 * @param destination - End port.
	 * @return List of ports to go through.
	 */
	public static ArrayList<Port> findPath(Player player, Port origin, Port destination) {
		if (origin.getNearby().contains(destination))
			return (ArrayList<Port>) Arrays.asList(origin, destination);
		return AStarAlgorithm.findShortestPath((List<Port>) Port.getPorts().values(), origin, destination);
	}

	/**
	 * Gets the journey wait (in ticks) for a player.
	 * 
	 * @param player - Player to check for
	 * @return Wait in ticks
	 */

	private static Long getJourneyWait(Player player) {
		return getJourneyWait(journeys.get(player));
	}

	/**
	 * Gets the journey wait (in ticks) for a port list.
	 * 
	 * @param journey - Port list
	 * @return Wait in ticks
	 */
	public static Long getJourneyWait(List<Port> journey) {
		Long cumulativeTime = 0L;
		for (int i = 1; i < journey.size(); i++) {
			cumulativeTime += getWait(journey.get(i - 1), journey.get(i));
		}
		return cumulativeTime;
	}

	/**
	 * Gets the cost for a player's journey
	 * 
	 * @param playerJourney - Journey list
	 * @return Double of cost
	 */
	public static double getJourneyCost(List<Port> playerJourney) {
		Double totalCost = 0.0;
		for (int i = 1; i < playerJourney.size(); i++) {
			totalCost += getTravelCost(playerJourney.get(i - 1), playerJourney.get(i));
		}

		return totalCost;
	}

	/**
	 * Gets journey cost of player
	 * 
	 * @param player - Journey player
	 * @return Double of cost
	 */

	public static Double getJourneyCost(Player player) {
		return getJourneyCost(journeys.get(player));
	}

	/**
	 * Gets the wait for travel between two ports.
	 * 
	 * @param origin      - Start port
	 * @param destination - End port
	 * @return Long of time (ticks)
	 */

	private static Long getWait(Port origin, Port destination) {
		Double distance = origin.distanceTo(destination);
		int size = Math.min(origin.getSize(), destination.getSize());
		Long waitTime = 100L;
		Double speed = 0.0;
		switch (size) {
		case 1:
			speed = Settings.smallSpeed;
			break;
		case 2:
			speed = Settings.mediumSpeed;
			break;
		case 3:
			speed = Settings.largeSpeed;
			break;
		case 4:
			speed = Settings.megaSpeed;
			break;
		}

		waitTime += Math.round(distance / speed) * 20L;

		return waitTime;
	}

	/**
	 * Gets travel cost between two ports
	 * 
	 * @param origin      - Start port
	 * @param destination - Emd port
	 * @return Double of cost
	 */
	private static Double getTravelCost(Port origin, Port destination) {
		int size = Math.min(origin.getSize(), destination.getSize());
		Double cost = Settings.getBaseCost();

		Double costRate = 0.0;
		switch (size) {
		case 1:
			costRate = Settings.smallCost;
			break;
		case 2:
			costRate = Settings.mediumCost;
			break;
		case 3:
			costRate = Settings.largeCost;
			break;
		case 4:
			costRate = Settings.megaCost;
			break;
		}
		cost += costRate * origin.distanceTo(destination) / 100;

		return cost;
	}

	/**
	 * Gets if player can travel to port
	 * 
	 * @param port   - Port to travel to
	 * @param player - Player travelling
	 * @return Boolean of if available
	 */
	public static boolean canTravelTo(Port port, Player player) {
		return true;
	}

	/**
	 * Gets a player's current port
	 * 
	 * @param player - Player to check
	 * @return Port of player
	 */
	public static Port getCurrentPort(Player player) {
		if (journeys.get(player) == null)
			return null;
		return journeys.get(player).get(0);
	}

}
