package me.NinjaMandalorian.ImplodusPorts.ui.tasks;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CommandTask implements BaseTask {

    private String commandString;
    
    public CommandTask(String command) {
        this.commandString = command;
    }
    
    @Override
    public void run(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        player.chat(commandString);
    }

}
