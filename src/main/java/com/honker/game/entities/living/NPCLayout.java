package com.honker.game.entities.living;

import com.honker.game.items.Weapon;
import static com.honker.main.Main.game;
import java.awt.Image;

public class NPCLayout {

    public static final NPCLayout BOSS1 = new NPCLayout(game.img.BOSS1, 100, Weapon.BOSS_WEAPON1, "Debug boss", 20, false);
    
    public static final NPCLayout DEBUG_ENEMY = new NPCLayout(game.img.ENEMY_SPRITE, 20, Weapon.BEGINNER_SWORD, "Debug enemy", 1, false);
    
    public Image sprite;
    public boolean friendly;
    //public int STR = 0, AG = 0, DEF = 0, SPD = 0;
    public int level;
    public int hp;
    public Weapon weapon;
    public String name;
    
    public NPCLayout(Image sprite, int hp, Weapon weapon, String name, int level, boolean friendly) {
        this.sprite = sprite;
        this.hp = hp;
        this.weapon = weapon;
        this.name = name;
        this.level = level;
        this.friendly = friendly;
    }
}
