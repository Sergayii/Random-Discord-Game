package com.honker.game.items;

import com.honker.game.entities.living.NPC;
import static com.honker.main.Main.game;
import java.awt.Image;
import java.util.Random;

public class Weapon extends EquippableItem {
    
    public static final Weapon DEBUG_SWORD = new Weapon(game.img.BEGINNER_SWORD_SPRITE, "Debug sword", "Sword used for debug", 9999, 10000, 100, 100, 100, 100, Item.RARITY_DEV, false, Weapon.QUALITY_NORMAL);
    public static final Weapon DEFAULT = new Weapon(game.img.EMPTY_SPRITE, "Fists", "Regular fists", 5, 10, 0, 0, 0, 0, Item.RARITY_DEV, false, Weapon.QUALITY_NORMAL);
    
    public static final Weapon BOSS_WEAPON1 = new Weapon(game.img.EMPTY_SPRITE, "Boss weapon", "You shouldn't see that...", 10, 20, 0, 0, 0, 0, Item.RARITY_DEV, false, Weapon.QUALITY_NORMAL);
    
    public static final Weapon BEGINNER_SWORD = new Weapon(game.img.BEGINNER_SWORD_SPRITE, "Beginner's sword", "Very weak sword", 10, 15, 0, 0, 0, 0, Item.RARITY_COMMON, true, Weapon.QUALITY_NORMAL);
    public static final Weapon WOODEN_BOW = new Weapon(game.img.WOODEN_BOW_SPRITE, "Wooden bow", "Regular bow", 15, 20, 0, 0, 0, 0, Item.RARITY_COMMON, true, Weapon.QUALITY_NORMAL);
    public static final Weapon STEEL_SWORD = new Weapon(game.img.STEEL_SWORD_SPRITE, "Steel sword", "Weak sword", 20, 30, 0, 0, 0, 0, Item.RARITY_COMMON, true, Weapon.QUALITY_NORMAL);
    
    public NPC owner = null;
    public int minDamage, maxDamage;
    
    public int getDamage() {
        return minDamage + new Random().nextInt(maxDamage - minDamage) + 1;
    }
    
    public void setOwner(NPC npc) {
        npc.weapon = this;
        owner = npc;
    }
    
    public Weapon(Image sprite, String name, String description, int minDamage, int maxDamage, int STR, int AG, int DEF, int SPD, int rarity, boolean affectedByQuality, String forcedQuality) {
        super(sprite, name, description, STR, AG, DEF, SPD, rarity, affectedByQuality, forcedQuality);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        
        switch(quality) {
            case QUALITY_AWFUL:
                this.minDamage = (int)(this.minDamage / 2);
                this.maxDamage = (int)(this.maxDamage / 2);
                removeStats(10);
                break;
            case QUALITY_SHODDY:
                this.minDamage = (int)(this.minDamage / 1.5);
                this.maxDamage = (int)(this.maxDamage / 1.5);
                removeStats(5);
                break;
            case QUALITY_POOR:
                this.minDamage = (int)(this.minDamage / 1.25);
                this.maxDamage = (int)(this.maxDamage / 1.25);
                removeStats(3);
                break;
            case QUALITY_GOOD:
                this.minDamage = (int)(this.minDamage * 1.25);
                this.maxDamage = (int)(this.maxDamage * 1.25);
                addStats(3);
                break;
            case QUALITY_SUPERIOR:
                this.minDamage = (int)(this.minDamage * 1.5);
                this.maxDamage = (int)(this.maxDamage * 1.5);
                addStats(5);
                break;
            case QUALITY_PARAGON:
                this.minDamage = (int)(this.minDamage * 2);
                this.maxDamage = (int)(this.maxDamage * 2);
                addStats(10);
                break;
            case QUALITY_GODLY:
                this.minDamage = (int)(this.minDamage * 3);
                this.maxDamage = (int)(this.maxDamage * 3);
                addStats(20);
                break;
            case QUALITY_LEGENDARY:
                this.minDamage = (int)(this.minDamage * 4);
                this.maxDamage = (int)(this.maxDamage * 4);
                addStats(30);
                break;
        }
        
        Item.addToItemsList(this);
    }
}
