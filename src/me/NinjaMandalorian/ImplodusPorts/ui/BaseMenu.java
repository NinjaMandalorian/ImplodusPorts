package me.NinjaMandalorian.ImplodusPorts.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BaseMenu implements InventoryHolder {
    
    private Inventory inventory;
    private HashMap<Integer,BaseButton> menuButtons;
    
    public BaseMenu(Inventory inventory, String menuTitle) {
        // Re-creates inventory with BaseMenu as holder
        this.inventory = Bukkit.createInventory(this, inventory.getSize(), menuTitle);
        this.inventory.setContents(inventory.getContents());
    }
    
    private BaseMenu(Builder builder) {
        this.inventory = Bukkit.createInventory(this, builder.menuSize, builder.menuTitle);
        
        // Creates all items in inventory
        for (Entry<Integer, BaseButton> buttonEntry : builder.menuButtons.entrySet()) {
            inventory.setItem(buttonEntry.getKey(), buttonEntry.getValue().getItemStack() );
        }
        
        this.menuButtons = builder.menuButtons;
        
    }
    
    public void open(Player player) {
        player.openInventory(inventory);
    }
    
    @Override
    public Inventory getInventory() {
        return this.inventory;
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
    
    public static Builder createBuilder() {
        return new Builder();
    }
    
    /**
     * Menu Builder
     */
    public static class Builder {
        private String menuTitle = "ip-title";
        private int menuSize = 54;
        private HashMap<Integer,BaseButton> menuButtons = new HashMap<Integer,BaseButton>();
        
        Builder() {}
        
        public Builder title(String title) {
            this.menuTitle = title;
            return this;
        }
        
        public Builder rows(int num) {
            this.menuSize = 9 * Math.min(num, 6);
            return this;
        }
        
        public Builder setButton(int slot, BaseButton button) {
            this.menuButtons.put(null, button);
            return this;
        }
        
        public BaseMenu build() {
            return new BaseMenu(this);
        }
    }
    
}
