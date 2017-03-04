package com.honker.game.items;

import com.honker.game.entities.living.NPC;
import static com.honker.main.Main.game;
import java.awt.Image;

public class Armor extends EquippableItem {
    
    public static final String SLOT_HEAD = "Head";
    public static final String SLOT_BODY = "Body";
    public static final String SLOT_ARMS = "Arms";
    public static final String SLOT_LEGS = "Legs";
    
    public static final Armor DEBUG_HEAD = new Armor(game.img.STEEL_HELMET_SPRITE, "Debug helmet", "Armor for debug", SLOT_HEAD, 50, 50, 50, 50, Item.RARITY_DEV, false, QUALITY_NORMAL);
    public static final Armor DEBUG_BODY = new Armor(game.img.STEEL_ARMOR_SPRITE, "Debug body armor", "Armor for debug", SLOT_BODY, 50, 50, 50, 50, Item.RARITY_DEV, false, QUALITY_NORMAL);
    public static final Armor DEBUG_ARMS = new Armor(game.img.STEEL_GLOVES_SPRITE, "Debug gloves", "Armor for debug", SLOT_ARMS, 50, 50, 50, 50, Item.RARITY_DEV, false, QUALITY_NORMAL);
    public static final Armor DEBUG_LEGS = new Armor(game.img.STEEL_GREAVES_SPRITE, "Debug greaves", "Armor for debug", SLOT_LEGS, 50, 50, 50, 50, Item.RARITY_DEV, false, QUALITY_NORMAL);
    public static final Armor DEFAULT_HEAD = new Armor(game.img.EMPTY_SPRITE, "None", "No armor", SLOT_HEAD, 0, 0, 0, 0, Item.RARITY_DEV, false, QUALITY_NORMAL);
    public static final Armor DEFAULT_BODY = new Armor(game.img.EMPTY_SPRITE, "None", "No armor", SLOT_BODY, 0, 0, 0, 0, Item.RARITY_DEV, false, QUALITY_NORMAL);
    public static final Armor DEFAULT_ARMS = new Armor(game.img.EMPTY_SPRITE, "None", "No armor", SLOT_ARMS, 0, 0, 0, 0, Item.RARITY_DEV, false, QUALITY_NORMAL);
    public static final Armor DEFAULT_LEGS = new Armor(game.img.EMPTY_SPRITE, "None", "No armor", SLOT_LEGS, 0, 0, 0, 0, Item.RARITY_DEV, false, QUALITY_NORMAL);
    
    public static final Armor STEEL_HELMET = new Armor(game.img.STEEL_HELMET_SPRITE, "Steel helmet", "Helmet made out of steel", SLOT_HEAD, 5, 0, 5, 0, Item.RARITY_UNCOMMON, true, QUALITY_NORMAL);
    public static final Armor STEEL_BODY_ARMOR = new Armor(game.img.STEEL_ARMOR_SPRITE, "Steel body armor", "Armor made out of steel", SLOT_BODY, 10, 5, 10, 0, Item.RARITY_UNCOMMON, true, QUALITY_NORMAL);
    public static final Armor STEEL_GLOVES = new Armor(game.img.STEEL_GLOVES_SPRITE, "Steel gloves", "Gloves made out of steel", SLOT_ARMS, 5, 5, 2, 0, Item.RARITY_UNCOMMON, true, QUALITY_NORMAL);
    public static final Armor STEEL_GREAVES = new Armor(game.img.STEEL_GREAVES_SPRITE, "Steel greaves", "Greaves made out of steel", SLOT_LEGS, 5, 5, 3, 5, Item.RARITY_UNCOMMON, true, QUALITY_NORMAL);
    
    public NPC owner = null;
    public String slot;
    
    public static Armor getArmorBySlot(NPC npc, String slot) {
        for(Armor armor : npc.armors) {
            if(armor.slot.equals(slot)) {
                return armor;
            }
        }
        return null;
    }
    
    public static void setDefault(NPC npc, String slot) {
        int index = 0;
        if(slot.equals(SLOT_HEAD)) {
            npc.armors.set(0, DEFAULT_HEAD);
            index = 0;
        } else if(slot.equals(SLOT_BODY)) {
            npc.armors.set(1, DEFAULT_BODY);
            index = 1;
        } else if(slot.equals(SLOT_ARMS)) {
            npc.armors.set(2, DEFAULT_ARMS);
            index = 2;
        } else if(slot.equals(SLOT_LEGS)) {
            npc.armors.set(3, DEFAULT_LEGS);
            index = 3;
        }
        npc.armors.get(index).setOwner(npc);
    }
    
    public void setOwner(NPC npc) {
        if(slot.equals(SLOT_HEAD))
            npc.armors.set(0, this);
        else if(slot.equals(SLOT_BODY))
            npc.armors.set(1, this);
        else if(slot.equals(SLOT_ARMS))
            npc.armors.set(2, this);
        else if(slot.equals(SLOT_LEGS))
            npc.armors.set(3, this);
        owner = npc;
    }
    
    public Armor(Image sprite, String name, String description, String slot, int STR, int AG, int DEF, int SPD, int rarity, boolean affectedByQuality, String forcedQuality) {
        super(sprite, name, description, STR, AG, DEF, SPD, rarity, affectedByQuality, forcedQuality);
        this.slot = slot;
        
        switch(quality) {
            case QUALITY_AWFUL:
                this.STR = (int)(this.STR / 2);
                this.AG = (int)(this.AG / 2);
                this.DEF = (int)(this.DEF / 2);
                this.SPD = (int)(this.SPD / 2);
                removeStats(10);
                break;
            case QUALITY_SHODDY:
                this.STR = (int)(this.STR / 1.5);
                this.AG = (int)(this.AG / 1.5);
                this.DEF = (int)(this.DEF / 1.5);
                this.SPD = (int)(this.SPD / 1.5);
                removeStats(7);
                break;
            case QUALITY_POOR:
                this.STR = (int)(this.STR / 1.25);
                this.AG = (int)(this.AG / 1.25);
                this.DEF = (int)(this.DEF / 1.25);
                this.SPD = (int)(this.SPD / 1.25);
                removeStats(5);
                break;
            case QUALITY_GOOD:
                this.STR = (int)(this.STR * 1.25);
                this.AG = (int)(this.AG * 1.25);
                this.DEF = (int)(this.DEF * 1.25);
                this.SPD = (int)(this.SPD * 1.25);
                addStats(5);
                break;
            case QUALITY_SUPERIOR:
                this.STR = (int)(this.STR * 1.5);
                this.AG = (int)(this.AG * 1.5);
                this.DEF = (int)(this.DEF * 1.5);
                this.SPD = (int)(this.SPD * 1.5);
                addStats(10);
                break;
            case QUALITY_PARAGON:
                this.STR = (int)(this.STR * 2);
                this.AG = (int)(this.AG * 2);
                this.DEF = (int)(this.DEF * 2);
                this.SPD = (int)(this.SPD * 2);
                addStats(25);
                break;
            case QUALITY_GODLY:
                this.STR = (int)(this.STR * 3);
                this.AG = (int)(this.AG * 3);
                this.DEF = (int)(this.DEF * 3);
                this.SPD = (int)(this.SPD * 3);
                addStats(50);
                break;
            case QUALITY_LEGENDARY:
                this.STR = (int)(this.STR * 4);
                this.AG = (int)(this.AG * 4);
                this.DEF = (int)(this.DEF * 4);
                this.SPD = (int)(this.SPD * 4);
                addStats(100);
                break;
        }
    }
}
