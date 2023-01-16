package me.NinjaMandalorian.ImplodusPorts.command;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.NinjaMandalorian.ImplodusPorts.ImplodusPorts;
import me.NinjaMandalorian.ImplodusPorts.helper.StringHelper;
import me.NinjaMandalorian.ImplodusPorts.object.Port;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;

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
        if (sender instanceof Player player) {
            if (args.length == 0) {
                return false;
            }
            
            switch(args[0].toLowerCase()) {
            case "travel":
                travelCommand(player, StringHelper.remFirst(args));
                return true;
            case "debug":
                ImplodusPorts.getInstance().getAdventure().all().sendMessage(Component.text("TEST-COMPONENT").hoverEvent(HoverEvent.showText(Component.text("HOVER"))));
                Port.initPorts();
                return true;
            default:
                
            }
        }
        return false;
    }
    
    private void travelCommand(Player player, String[] args) {
        
    }

}
