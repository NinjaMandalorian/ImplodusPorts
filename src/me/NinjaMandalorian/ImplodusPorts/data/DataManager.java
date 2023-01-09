package me.NinjaMandalorian.ImplodusPorts.data;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;

public class DataManager {
    
    private static ImplodusPorts plugin;
    
    private static String dataFolder = "plugins" + File.separator + "ImplodusNuclei" + File.separator + "data";
    
    public static void init(){
        plugin = ImplodusPorts.getInstance();
        
        createFolders();
    }

    private static void createFolders() {
        List<String> paths = Arrays.asList(
                "plugins" + File.separator + "ImplodusPorts" + File.separator + "data"
                );
        
        for (String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
    
}
