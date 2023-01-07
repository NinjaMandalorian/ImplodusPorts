package me.NinjaMandalorian.ImplodusPorts.ui;

import java.util.ArrayList;
import java.util.HashMap;

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
    
    private BaseMenu(Builder builder) {
        this.inventory = Bukkit.createInventory(this, builder.menuSize, builder.menuTitle);
        
    }
    
    public void open(Player player) {
        player.openInventory(inventory);
    }
    
    @Override
    public Inventory getInventory() {
        return this.inventory;
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
        
        public Builder setButton(int slot, BaseButton button) {
            menuButtons.put(null, button);
            return this;
        }
        
        public BaseMenu build() {
            
            return new BaseMenu(this);
        }
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
