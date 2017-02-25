package com.honker.game.items;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public abstract class Item {
    
    public static ArrayList<Item> items = new ArrayList<Item>();
    
    public boolean equippable, usable;
    public String name, description;
    
    public Image sprite;
    
    public Item(Image sprite, String name, String description, boolean equippable, boolean usable) {
        this.sprite = sprite;
        this.name = name;
        this.description = description;
        this.equippable = equippable;
        this.usable = usable;
    }
    
    public String getInfo() {
        return Item.getInfoAboutItem(this);
    }
    
    public static String getInfoAboutItem(Item item) {
        if(item instanceof Weapon) {
            Weapon weapon = (Weapon)item;
            
            return  "Item name: " + weapon.name +  "\n\nDescription:\n" + ((Weapon) item).description + "\n\nMinimum damage: " + weapon.minDamage + "\nMaximum damage: " +
                    weapon.maxDamage;
        }
        return "Item name: " + item.name+  "\n\nDescription:\n" + item.description;
    }
    
    public static Item getRandomItem() {
        return items.get(new Random().nextInt(items.size()));
    }
}
