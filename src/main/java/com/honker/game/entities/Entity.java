package com.honker.game.entities;

import com.honker.game.entities.living.Enemy;
import com.honker.game.entities.living.Hero;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import static com.honker.main.Variables.ENTITY_SIZE;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entity extends Rectangle{
    
    public static int INFINITE_HP = 10000000;
    
    public Location location;
    public Map map;
    
    public Image sprite;
    
    public boolean left = false, right = false, up = false, down = false;
    public int xvel, yvel, hp, maxHp;
    
    public void hpCheck() {
        if(hp > maxHp) {
            hp = maxHp;
        } else if(hp <= 0) {
            hp = 0;
            destroy();
            
            if(this instanceof Enemy) {
                Map.dropLoot((Enemy)this);
            } else if(this instanceof Hero) {
                Map.dropLoot((Hero)this);
            }
        }
    }
    
    public void draw(Graphics g, int x, int y) {
        g.drawImage(sprite, x, y, null);
    }
    
    public void draw(Graphics g) {
        draw(g, x, y);
    }
    
    public void destroy() {
        map.entitiesToRemove.add(this);
    }
    
    public abstract void move();
    public abstract void collide(int xvel, int yvel);
    
    public Entity(int x, int y, Image sprite, Location location, Map map, int hp) {
        this.x = x;
        this.y = y;
        width = ENTITY_SIZE;
        height = ENTITY_SIZE;
        this.sprite = sprite;
        this.location = location;
        this.map = map;
        maxHp = hp;
        this.hp = hp;
    }
}
