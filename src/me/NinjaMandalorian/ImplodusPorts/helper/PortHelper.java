package me.NinjaMandalorian.ImplodusPorts.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import me.NinjaMandalorian.ImplodusPorts.object.Port;

public class PortHelper {
    
    /**
     * Orders ports for display. <br>
     * Megaports, then rest. Closest to farthest.
     * @param currentPort - Port ordering from
     * @param toSort - List of ports to sort
     * @return Sorted Port List.
     */
    public static List<Port> orderPorts(Port currentPort, List<Port> toSort) {
        List<Port> returnList = new ArrayList<Port>();
        TreeMap<Double,Port> sortedMap = new TreeMap<Double,Port>();
        
        // Removes self
        toSort.remove(currentPort);
        
        // Adds all megaports to sortedMap & removes from toSort
        for (Port port : toSort) {
            if (port.getSize() == 4) {
                sortedMap.put(currentPort.distanceTo(port), port);
                toSort.remove(port);
            }
        }
        
        // Adds all megaports to final list & clears sortedMap
        returnList.addAll(sortedMap.values());
        sortedMap.clear();
        
        // Re-uses sortedMap for rest of ports
        for (Port port : toSort) {
            sortedMap.put(currentPort.distanceTo(port), port);
        }
        // Adds rest and returns.
        returnList.addAll(sortedMap.values());
        
        return returnList;
    }
    
}
