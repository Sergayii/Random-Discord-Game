package com.honker.game.items;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public abstract class Item {
    
    public static final int RARITY_DEV = 0;
    public static final int RARITY_COMMON = 1;
    public static final int RARITY_UNCOMMON = 2;
    public static final int RARITY_RARE = 3;
    public static final int RARITY_SUPREME = 4;
    public static final int RARITY_EPIC = 5;
    public static final int RARITY_LEGENDARY = 6;
    public static final int RARITY_TRANSCENDENT = 7;
    
    public static ArrayList<Item> devItems = new ArrayList<Item>();
    public static ArrayList<Item> commonItems = new ArrayList<Item>();
    public static ArrayList<Item> uncommonItems = new ArrayList<Item>();
    public static ArrayList<Item> rareItems = new ArrayList<Item>();
    public static ArrayList<Item> supremeItems = new ArrayList<Item>();
    public static ArrayList<Item> epicItems = new ArrayList<Item>();
    public static ArrayList<Item> legendaryItems = new ArrayList<Item>();
    public static ArrayList<Item> transcendentItems = new ArrayList<Item>();
    
    public boolean equippable, usable;
    public String name, description;
    public int rarity;
    
    public Image sprite;
    
    public Item(Image sprite, String name, String description, boolean equippable, boolean usable, int rarity) {
        this.sprite = sprite;
        this.name = name;
        this.description = description;
        this.equippable = equippable;
        this.usable = usable;
        this.rarity = rarity;
    }
    
    public String getInfo() {
        return Item.getInfoAboutItem(this);
    }
    
    public static void addToItemsList(Item item) {
        switch(item.rarity) {
            case RARITY_DEV:
                if(!devItems.contains(item))
                    devItems.add(item);
                break;
            case RARITY_COMMON:
                if(!commonItems.contains(item))
                    commonItems.add(item);
                break;
            case RARITY_UNCOMMON:
                if(!uncommonItems.contains(item))
                    uncommonItems.add(item);
                break;
            case RARITY_RARE:
                if(!rareItems.contains(item))
                    rareItems.add(item);
                break;
            case RARITY_SUPREME:
                if(!supremeItems.contains(item))
                    supremeItems.add(item);
                break;
            case RARITY_EPIC:
                if(!epicItems.contains(item))
                    epicItems.add(item);
                break;
            case RARITY_LEGENDARY:
                if(!legendaryItems.contains(item))
                    legendaryItems.add(item);
                break;
            case RARITY_TRANSCENDENT:
                if(!transcendentItems.contains(item))
                    transcendentItems.add(item);
                break;
        }
    }
    
    public static String getInfoAboutItem(Item item) {
        if(item instanceof Weapon) {
            Weapon weapon = (Weapon)item;
            
            return  "Item name: " + weapon.name +  "\nQuality: " + weapon.quality + "\nDescription:\n" + weapon.description + "\n\nMinimum damage: " + 
                    weapon.minDamage + "\nMaximum damage: " +  weapon.maxDamage;
        }
        return "Item name: " + item.name+  "\n\nDescription:\n" + item.description;
    }
    
    public static Item getRandomItem() {
        ArrayList<Item> list = new ArrayList<Item>();
        while(true) {
            int rand = new Random().nextInt(7) + 1;
            if(rand == 1)
                list.addAll(commonItems);
            else if(rand == 2)
                list.addAll(uncommonItems);
            else if(rand == 3)
                list.addAll(rareItems);
            else if(rand == 4)
                list.addAll(supremeItems);
            else if(rand == 5)
                list.addAll(epicItems);
            else if(rand == 6)
                list.addAll(legendaryItems);
            else if(rand == 7)
                list.addAll(transcendentItems);
            
            if(!list.isEmpty())
                break;
        }
        return list.get(new Random().nextInt(list.size()));
    }
}
