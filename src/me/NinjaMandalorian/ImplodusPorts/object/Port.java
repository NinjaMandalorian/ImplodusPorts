package me.NinjaMandalorian.ImplodusPorts.object;

import org.bukkit.Location;

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
    
}
