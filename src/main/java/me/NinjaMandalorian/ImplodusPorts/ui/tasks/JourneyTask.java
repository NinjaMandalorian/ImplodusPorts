package me.NinjaMandalorian.ImplodusPorts.ui.tasks;

import me.NinjaMandalorian.ImplodusPorts.handler.TravelHandler;
import me.NinjaMandalorian.ImplodusPorts.object.Port;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.List;

public class JourneyTask implements BaseTask {

	private List<Port> ports;

	public JourneyTask(Port origin, Port destination) {
		ports = Arrays.asList(origin, destination);
	}

	@Override
	public void run(InventoryClickEvent event) {
		TravelHandler.startJourney((Player) event.getWhoClicked(), ports.get(0), ports.get(1));
		event.getWhoClicked().closeInventory();
	}

}
