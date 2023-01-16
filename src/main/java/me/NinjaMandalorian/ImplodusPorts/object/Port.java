package me.NinjaMandalorian.ImplodusPorts.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.NinjaMandalorian.ImplodusPorts.Logger;
import me.NinjaMandalorian.ImplodusPorts.data.PortDataManager;
import me.NinjaMandalorian.ImplodusPorts.settings.Settings;

/**
 * Port object, handles all interactions with individual ports.
 * @author NinjaMandalorian
 *
 */
public class Port {

    private static HashMap<String, Port> activePorts = new HashMap<String, Port>();
    
    private String id;
    private Location signLocation;
    private Location teleportLocation;
    private int size;
    private String displayName;
    
    /**
     * Constructor for individual ports.
     * @param name - id 
     * @param location
     * @param size
     * @param displayName
     */
    public Port(String id, Location sLocation, Location tLocation, int size, String displayName) {
        this.id = id;
        this.signLocation = sLocation;
        this.teleportLocation = tLocation;
        this.size = size;
        this.displayName = displayName;
    }
    
    public Port(String id, Location sLocation, Location tLocation, int size) {
        this(id, sLocation, tLocation, size, id);
    }
    
    public String getId() {
        return this.id;
    }
    
    public Location getSignLocation() {
        return this.signLocation;
    }
    
    public Location getTeleportLocation() {
        return this.teleportLocation;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public List<Port> getNearby() {
        ArrayList<Port> returnList = new ArrayList<Port>();
        
        for (Port port : activePorts.values()) {
            if (port.equals(this)) continue;
            if (this.distanceTo(port) < (Integer) Settings.getSizeMap(size).get("distance")) {
                returnList.add(port);
            }
        }
        
        return returnList;
    }
    
    public Double distanceTo(Port port) {
        if (!this.signLocation.getWorld().equals(port.getSignLocation().getWorld())) return 0.0;
        return (this.getSignLocation().distance(port.getSignLocation()));
    }
    
    /**
     * Gets all the icons for each type of port.
     * @return List of Materials
     */
    public static List<Material> getIcons() {
        return Arrays.asList(Material.GLASS, Material.BIRCH_BOAT, Material.OAK_BOAT, Material.DARK_OAK_BOAT, Material.MANGROVE_BOAT);
    }

    /**
     * Gives icon for port size
     * @param size - Port size
     * @return Material
     */
    public static Material getIcon(int size) {
        if (size >= getIcons().size()) return Material.GLASS;
        return getIcons().get(size);
    }
    
    /**
     * Get all Ports
     * @return List of a Ports
     */
    public static HashMap<String,Port> getPorts() {
        return activePorts;
    }
    
    public static Port getPort(String id) {
        return activePorts.get(id);
    }
    
    public static Port getPort(Location location) {
        for (Port port : activePorts.values()) {
            if (port.getSignLocation().getBlock().equals(location.getBlock())) {
                return port;
            }
        }
        return null;
    }
    
    public static void initPorts() {
        activePorts = PortDataManager.loadPortData();
    }

    public static void portCreate(Player player, Port port) {
        player.sendMessage("CREATED PORT");
        Logger.log("Player " + player != null ? player.getName() : "CONSOLE" + " created port " + port.getId());
        activePorts.put(port.getId(), port);
        PortDataManager.savePort(port);
    }
    
    public static void portDestroy(Player player, Port port) {
        player.sendMessage("DESTROYED PORT");
        Logger.log("Player " + player != null ? player.getName() : "CONSOLE" + " destroyed port " + port.getId());
        activePorts.remove(port.getId());
        PortDataManager.deletePort(port);
    }
    
}
