package me.NinjaMandalorian.ImplodusPorts.data;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.NinjaMandalorian.ImplodusPorts.object.Port;

public class PortDataManager {
    
    private static String filePath = "ports.csv";
    private static String categories = ""
            + "id,"
            + "signLocation,"
            + "teleportLocation,"
            + "size,"
            + "displayName";    
    
    /**
     * Loads in port data from file, returns as a map
     * @return Map of ports
     */
    public static HashMap<String, Port> loadPortData() {
        HashMap<String, Port> returnData = new HashMap<String, Port>();
        
        String rawData = DataManager.getRawData(filePath);
        
        // Splits with newline then remove categories
        String[] portsData = rawData.split("\n");
        
        for (String portData : portsData) {
            Bukkit.getLogger().info("Converting to port: " + portData);
            String[] splitData = splitWithQuotes(portData); 
            if (splitData.length < 5 || splitData[0].equalsIgnoreCase("id")) continue;
            
            Port port = new Port(
                    splitData[0],
                    stringToLocation(splitData[1]),
                    stringToLocation(splitData[2]),
                    Integer.parseInt(splitData[3]),
                    splitData[4]
                    );
            
            returnData.put(splitData[0], port);
        }
        
        return returnData;
    }
    
    /**
     * Saves a port to file
     * @param port - Port to save
     */
    public static void savePort(Port port) {
        HashMap<String, Port> data = loadPortData();
        data.put(port.getId(), port);
        
        savePortData(data);
    }
    
    /**
     * Saves port data to file
     * @param data - Port data
     */
    public static void savePortData(HashMap<String, Port> data) {
        String rawData = "";
        rawData += categories + "\n";
        
        for (Port port : data.values()) {
            rawData += portToString(port);
            rawData += "\n";
        }
        
        Bukkit.getLogger().info("Saving: " + rawData);
        DataManager.saveRawData(filePath, rawData);
    }

    /**
     * Converts port to string for saving
     * @param port - Port to convert
     * @return Port string
     */
    private static String portToString(Port port) {
        return ""
                + port.getId() + ","
                + locationToString(port.getSignLocation()) + ","
                + locationToString(port.getTeleportLocation()) + ","
                + port.getSize() + ","
                + port.getDisplayName()
                ;
    }
    
    /**
     * Splits string for .csv format, ignoring commas within brackets.
     * @param string - Input string
     * @return Split array excluding quoted
     */
    private static String[] splitWithQuotes(String string) {
        String[] split = string.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (string.contains("\"")) {
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].replace("\"", "");
            }
        }
        return split;
    }
    
    /**
     * Converts a saved string to a Location object.
     * @param string - Saved string
     * @return Bukkit location or null
     */
    private static Location stringToLocation(String string) {
        String[] array = string.split(",");
        if (array.length == 4) {
            return new Location(
                    Bukkit.getWorld(array[0]), 
                    Double.parseDouble(array[1]), 
                    Double.parseDouble(array[2]), 
                    Double.parseDouble(array[3])
                );
        } else if (array.length == 6) {
            return new Location(
                    Bukkit.getWorld(array[0]), 
                    Double.parseDouble(array[1]), 
                    Double.parseDouble(array[2]), 
                    Double.parseDouble(array[3]),
                    Float.parseFloat(array[4]),
                    Float.parseFloat(array[5])
                );
        }
        return null;
    }
    
    /**
     * Converts a location object to a string.
     * @param location - Location
     * @return String
     */
    private static String locationToString(Location location) {
        String returnString = "\"";
        
        returnString += location.getWorld().getName() + ",";
        returnString += location.getX() + ",";
        returnString += location.getY() + ",";
        returnString += location.getZ() + ",";
        returnString += location.getYaw() + ",";
        returnString += location.getPitch() + ",";
        
        returnString += "\"";
        return returnString;
    }
    
}
