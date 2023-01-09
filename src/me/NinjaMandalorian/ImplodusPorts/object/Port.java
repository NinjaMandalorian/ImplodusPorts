package me.NinjaMandalorian.ImplodusPorts.object;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Port object, handles all interactions with individual ports.
 * @author NinjaMandalorian
 *
 */
public class Port {

    private String id;
    private Location location;
    private int size;
    private String displayName;
    
    /**
     * Constructor for individual ports.
     * @param name - id 
     * @param location
     * @param size
     * @param displayName
     */
    public Port(String id, Location location, int size, String displayName) {
        this.id = id;
        this.location = location;
        this.size = size;
        this.displayName = displayName;
    }
    
    public Port(String id, Location location, int size) {
        this(id, location, size, id);
    }
    
    public String getId() {
        return this.id;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    /**
     * Gets all the icons for each type of port.
     * @return List of Materials
     */
    public static List<Material> getIcons() {
        return Arrays.asList(Material.GLASS, Material.BIRCH_BOAT, Material.OAK_BOAT, Material.SPRUCE_BOAT, Material.DARK_OAK_BOAT);
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
    
}
