package com.honker.game.items;

import com.honker.game.entities.living.NPC;
import static com.honker.main.Main.game;
import java.awt.Image;
import java.util.Random;

public class Weapon extends Item {
    
    public static final Weapon DEBUG_SWORD = new Weapon(game.img.ENEMY_SPRITE, "Debug sword", "Sword used for debug", 9999, 10000, 100, 100, 100, 100, false);
    public static final Weapon DEFAULT = new Weapon(null, "Fists", "Regular fists", 5, 10, 0, 0, 0, 0, false);
    public static final Weapon BEGINNER_SWORD = new Weapon(game.img.ITEM_BAG_SPRITE, "Beginner's sword", "Very weak sword", 10, 15, 0, 0, 0, 0, true);
    
    public NPC owner = null;
    public int minDamage, maxDamage;
    public int STR, AG, DEF, SPD;
    
    public int getDamage() {
        return minDamage + new Random().nextInt(maxDamage - minDamage) + 1;
    }
    
    public void setOwner(NPC npc) {
        npc.weapon = this;
        owner = npc;
    }
    
    public Weapon(Image sprite, String name, String description, int minDamage, int maxDamage, int STR, int AG, int DEF, int SPD, boolean addToItems) {
        super(sprite, name, description, true, false);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        
        this.STR = STR;
        this.AG = AG;
        this.DEF = DEF;
        this.SPD = SPD;
        
        if(addToItems && !Item.items.contains(this)) {
            Item.items.add(this);
        }
    }
}
