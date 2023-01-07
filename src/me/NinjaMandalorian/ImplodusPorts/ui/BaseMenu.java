package me.NinjaMandalorian.ImplodusPorts.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BaseMenu implements InventoryHolder {
    
    private Inventory inventory;
    private ArrayList<BaseButton> menuButtons;
    
    public BaseMenu(Inventory inventory, String menuTitle) {
        // Re-creates inventory with BaseMenu as holder
        this.inventory = Bukkit.createInventory(this, inventory.getSize(), menuTitle);
        this.inventory.setContents(inventory.getContents());
    }
    
    public void open(Player player) {
        player.openInventory(inventory);
    }
    
    @Override
    public Inventory getInventory() {
        return null;
    }
    
    /**
     * Gets button, indexed from 0:(size-1)
     * @param slotNum - Slot number
     * @return Button of slot or null if empty.
     */
    public BaseButton getButton(int slotNum) {
        if (slotNum > this.inventory.getSize() - 1) return null;
        return this.menuButtons.get(slotNum);
    }
    
}
