package me.NinjaMandalorian.ImplodusPorts.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;

public class ImplodusPortsCommands implements CommandExecutor, TabCompleter {

    public ImplodusPortsCommands() {
        ImplodusPorts plugin = ImplodusPorts.getInstance();
        plugin.getCommand("implodusports").setExecutor(this);
        plugin.getCommand("implodusports").setTabCompleter(this);
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("HAHA");
        return false;
    }

}