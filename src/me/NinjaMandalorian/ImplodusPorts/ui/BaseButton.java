package me.NinjaMandalorian.ImplodusPorts.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinjaMandalorian.ImplodusPorts.ui.tasks.BaseTask;
import me.NinjaMandalorian.ImplodusPorts.ui.tasks.MessageTask;
import net.md_5.bungee.api.ChatColor;

/**
 * Represents a button in the Inventory Menu
 * Contains the ItemStack, BaseTask & Any other metadata.
 * @author NinjaMandalorian
 */
public class BaseButton {
    
    private ItemStack itemStack;
    private BaseTask task;
    private HashMap<String,String> metadata;
    
    private BaseButton() {};
    
    public BaseTask getTask() {
        return this.task;
    }
    
    public HashMap<String, String> getMetadata() {
        return this.metadata;
    }
    
    public void setMetadata(HashMap<String, String> map) {
        this.metadata = map;
    }
    
    public ItemStack getItemStack() { 
        return this.itemStack;
    }
    
    // Constructors for building
    
    public static BaseButton create() {
        BaseButton button = new BaseButton();
        button.itemStack = new ItemStack(Material.GLASS);
        button = button.name(ChatColor.GRAY + "Empty");
        button.task = new MessageTask("Task-Unassigned");
        return button;
    }
    
    public static BaseButton create(Material material) {
        BaseButton button = create();
        button.itemStack = new ItemStack(material);
        return button;
    }
    
    public BaseButton glow() {
        this.itemStack.addUnsafeEnchantment(Enchantment.LUCK, 1);
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        this.itemStack.setItemMeta(meta);
        return this;
    }
    
    public BaseButton itemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }
    
    public BaseButton task(BaseTask task) {
        this.task = task;
        return this;
    }
    
    public BaseButton quantity(int num) {
        this.itemStack.setAmount(num);
        return this;
    }
    
    public BaseButton name(String name) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        this.itemStack.setItemMeta(meta);
        return this;
    }
    
    public BaseButton lore(List<String> lore) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setLore(lore);
        this.itemStack.setItemMeta(meta);
        return this;
    }
    
    public BaseButton lore(String lore) {
        return lore(Arrays.asList(lore.split("\n")));
    }
    
}
