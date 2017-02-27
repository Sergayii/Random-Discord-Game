package com.honker.game.items;

import com.honker.game.entities.living.NPC;
import static com.honker.main.Main.game;
import java.awt.Image;
import java.util.Random;

public class Weapon extends Item {
    
    public static final String QUALITY_AWFUL = "Awful";
    public static final String QUALITY_SHODDY = "Shoddy";
    public static final String QUALITY_POOR = "Poor";
    public static final String QUALITY_NORMAL = "Normal";
    public static final String QUALITY_GOOD = "Good";
    public static final String QUALITY_SUPERIOR = "Superior";
    public static final String QUALITY_PARAGON = "Paragon";
    public static final String QUALITY_GODLY = "Godly";
    public static final String QUALITY_LEGENDARY = "Legendary";
    
    public static final Weapon DEBUG_SWORD = new Weapon(game.img.BEGINNER_SWORD_SPRITE, "Debug sword", "Sword used for debug", 9999, 10000, 100, 100, 100, 100, Item.RARITY_DEV, false, Weapon.QUALITY_NORMAL);
    public static final Weapon DEFAULT = new Weapon(game.img.EMPTY_SPRITE, "Fists", "Regular fists", 5, 10, 0, 0, 0, 0, Item.RARITY_DEV, false, Weapon.QUALITY_NORMAL);
    
    public static final Weapon BOSS_WEAPON1 = new Weapon(game.img.EMPTY_SPRITE, "Boss weapon", "You shouldn't see that...", 10, 20, 0, 0, 0, 0, Item.RARITY_DEV, false, Weapon.QUALITY_NORMAL);
    
    public static final Weapon BEGINNER_SWORD = new Weapon(game.img.BEGINNER_SWORD_SPRITE, "Beginner's sword", "Very weak sword", 10, 15, 0, 0, 0, 0, Item.RARITY_COMMON, true, Weapon.QUALITY_NORMAL);
    public static final Weapon WOODEN_BOW = new Weapon(game.img.WOODEN_BOW_SPRITE, "Wooden bow", "Regular bow", 15, 20, 0, 0, 0, 0, Item.RARITY_COMMON, true, Weapon.QUALITY_NORMAL);
    public static final Weapon STEEL_SWORD = new Weapon(game.img.STEEL_SWORD_SPRITE, "Steel sword", "Weak sword", 20, 30, 0, 0, 0, 0, Item.RARITY_COMMON, true, Weapon.QUALITY_NORMAL);
    
    public NPC owner = null;
    public String quality;
    public int minDamage, maxDamage;
    public int STR, AG, DEF, SPD;
    
    public int getDamage() {
        return minDamage + new Random().nextInt(maxDamage - minDamage) + 1;
    }
    
    public void setOwner(NPC npc) {
        npc.weapon = this;
        owner = npc;
    }
    
    public Weapon(Image sprite, String name, String description, int minDamage, int maxDamage, int STR, int AG, int DEF, int SPD, int rarity, boolean affectedByQuality, String forcedQuality) {
        super(sprite, name, description, true, false, rarity);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        
        int chance = new Random().nextInt(50) + 1;
        if(chance >= 1 && chance <= 10)
            quality = QUALITY_AWFUL;
        else if(chance >= 10 && chance <= 20)
            quality = QUALITY_SHODDY;
        else if(chance >= 20 && chance <= 30)
            quality = QUALITY_POOR;
        else if(chance >= 30 && chance <= 35)
            quality = QUALITY_NORMAL;
        else if(chance >= 35 && chance <= 40)
            quality = QUALITY_GOOD;
        else if(chance >= 40 && chance <= 44)
            quality = QUALITY_SUPERIOR;
        else if(chance >= 44 && chance <= 47)
            quality = QUALITY_PARAGON;
        else if(chance >= 47 && chance <= 49)
            quality = QUALITY_GODLY;
        else if(chance >= 49 && chance <= 50)
            quality = QUALITY_LEGENDARY;
        else
            quality = QUALITY_NORMAL;
        
        if(!affectedByQuality) {
            quality = forcedQuality;
        }
        
        this.STR = STR;
        this.AG = AG;
        this.DEF = DEF;
        this.SPD = SPD;
        
        switch(quality) {
            case QUALITY_AWFUL:
                this.minDamage = (int)(this.minDamage / 2);
                this.maxDamage = (int)(this.maxDamage / 2);
                break;
            case QUALITY_SHODDY:
                this.minDamage = (int)(this.minDamage / 1.5);
                this.maxDamage = (int)(this.maxDamage / 1.5);
                break;
            case QUALITY_POOR:
                this.minDamage = (int)(this.minDamage / 1.25);
                this.maxDamage = (int)(this.maxDamage / 1.25);
                break;
            case QUALITY_GOOD:
                this.minDamage = (int)(this.minDamage * 1.25);
                this.maxDamage = (int)(this.maxDamage * 1.25);
                break;
            case QUALITY_SUPERIOR:
                this.minDamage = (int)(this.minDamage * 1.5);
                this.maxDamage = (int)(this.maxDamage * 1.5);
                break;
            case QUALITY_PARAGON:
                this.minDamage = (int)(this.minDamage * 2);
                this.maxDamage = (int)(this.maxDamage * 2);
                break;
            case QUALITY_GODLY:
                this.minDamage = (int)(this.minDamage * 3);
                this.maxDamage = (int)(this.maxDamage * 3);
                break;
            case QUALITY_LEGENDARY:
                this.minDamage = (int)(this.minDamage * 4);
                this.maxDamage = (int)(this.maxDamage * 4);
                break;
        }
        
        Item.addToItemsList(this);
    }
}
