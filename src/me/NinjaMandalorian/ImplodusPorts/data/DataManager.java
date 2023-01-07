package me.NinjaMandalorian.ImplodusPorts.data;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;

public class DataManager {
    
    private static ImplodusPorts plugin;
    
    public static void init(){
        plugin = ImplodusPorts.getInstance();
        
        plugin.getConfig();
    }
    
}
