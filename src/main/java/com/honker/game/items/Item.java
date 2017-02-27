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
    public static final int RARITY_ANCIENT = 7;
    
    public static ArrayList<Item> devItems = new ArrayList<Item>();
    public static ArrayList<Item> commonItems = new ArrayList<Item>();
    public static ArrayList<Item> uncommonItems = new ArrayList<Item>();
    public static ArrayList<Item> rareItems = new ArrayList<Item>();
    public static ArrayList<Item> supremeItems = new ArrayList<Item>();
    public static ArrayList<Item> epicItems = new ArrayList<Item>();
    public static ArrayList<Item> legendaryItems = new ArrayList<Item>();
    public static ArrayList<Item> ancientItems = new ArrayList<Item>();
    
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
            case RARITY_ANCIENT:
                if(!ancientItems.contains(item))
                    ancientItems.add(item);
                break;
        }
    }
    
    public static String getInfoAboutItem(Item item) {
        if(item instanceof Weapon) {
            Weapon weapon = (Weapon)item;
            
            return  "Item name: " + weapon.name +  "\nQuality: " + weapon.quality + "\nDescription:\n" + weapon.description + "\n\nMinimum damage: " + 
                    weapon.minDamage + "\nMaximum damage: " + weapon.maxDamage + "\n\nAdditional stats:\n" + "Strength: " + weapon.STR + "\nAgility: " + 
                    weapon.AG + "\nDefense: " + weapon.DEF + "\nSpeed: " + weapon.SPD;
        } else if(item instanceof Armor) {
            Armor armor = (Armor)item;
            
            return  "Item name: " + armor.name +  "\nQuality: " + armor.quality + "\nDescription:\n" + armor.description + "\n\nSlot: " + 
                    armor.slot + "\n\nAdditional stats:\n" + "Strength: " + armor.STR + "\nAgility: " + armor.AG + "\nDefense: " + 
                    armor.DEF + "\nSpeed: " + armor.SPD;
        }
        return "Item name: " + item.name+  "\n\nDescription:\n" + item.description;
    }
    
    public static Item getRandomItemWithRarity() {
        ArrayList<Item> items = new ArrayList<Item>();
        int chance = new Random().nextInt(36) + 1;
        if(chance >= 1 && chance <= 10)
            items.addAll(Item.commonItems);
        else if(chance >= 10 && chance <= 18)
            items.addAll(Item.uncommonItems);
        else if(chance >= 18 && chance <= 25)
            items.addAll(Item.rareItems);
        else if(chance >= 25 && chance <= 30)
            items.addAll(Item.supremeItems);
        else if(chance >= 30 && chance <= 33)
            items.addAll(Item.epicItems);
        else if(chance >= 33 && chance <= 35)
            items.addAll(Item.legendaryItems);
        else if(chance >= 35 && chance <= 36)
            items.addAll(Item.ancientItems);
        else
            items.addAll(Item.commonItems);
        return getRandomItemFromList(items);
    }
    
    public static Item getRandomItemFromList(ArrayList<Item> items) {
        if(items.isEmpty()) {
            return getRandomItemFromList(commonItems);
        }
        return items.get(new Random().nextInt(items.size()));
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
                list.addAll(ancientItems);
            
            if(!list.isEmpty())
                break;
        }
        return list.get(new Random().nextInt(list.size()));
    }
}
