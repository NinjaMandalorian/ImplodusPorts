package me.NinjaMandalorian.ImplodusPorts.ui;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

import me.NinjaMandalorian.ImplodusPorts.ui.tasks.BaseTask;

/**
 * Represents a button in the Inventory Menu
 * Contains the ItemStack, BaseTask & Any other metadata.
 * @author NinjaMandalorian
 */
public class BaseButton {
    
    private ItemStack itemStack;
    private BaseTask task;
    private HashMap<String,String> metadata;
    
    public BaseButton(ItemStack itemStack, BaseTask task, HashMap<String, String> metadata) {
        this.itemStack = itemStack;
        this.task = task;
        this.metadata = metadata;
    }

    
    public BaseButton(ItemStack itemStack, BaseTask task) {
        this(itemStack, task, new HashMap<String,String>());
    }
    
    public ItemStack getItemStack() { 
        return this.itemStack;
    }
    
    public BaseTask getTask() {
        return this.task;
    }
    
    public HashMap<String, String> getMetadata() {
        return this.metadata;
    }
    
    public void setMetadata(HashMap<String, String> map) {
        this.metadata = map;
    }
    
}
