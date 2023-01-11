package me.NinjaMandalorian.ImplodusPorts;

import org.bukkit.Bukkit;

public class Logger {
    public static void log(String string) {
        Bukkit.getLogger().severe(string);
    }
}
