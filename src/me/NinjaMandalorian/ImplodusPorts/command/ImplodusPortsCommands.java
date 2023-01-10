package me.NinjaMandalorian.ImplodusPorts.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;
import me.NinjaMandalorian.ImplodusPorts.ui.PortMenu;

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
        if (sender instanceof Player player) {
            PortMenu.createPortMenu(player, null).open(player);
            if (args.length == 0) {
                return false;
            }
            
            switch(args[0].toLowerCase()) {
            case "travel":
                travelCommand(player, StringHelper.remFirst(args));
                return true;
            case "debug":
                args = StringHelper.remFirst(args);
                Port fakePort = new Port("bingus_town", new Location(Bukkit.getWorld("World"), 0.0, 0.0, 0.0), args.length > 0 ? Integer.parseInt(args[0]) : 1, "Bingus Town");
                PortMenu.createPortMenu(player, fakePort).open(player);
                return true;
            default:
                
            }
        }
        return false;
    }

}
