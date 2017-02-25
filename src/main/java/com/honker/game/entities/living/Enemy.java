package com.honker.game.entities.living;

import com.honker.game.entities.Entity;
import com.honker.game.entities.blocks.DroppedItem;
import com.honker.game.items.Weapon;
import com.honker.game.main.Player;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import static com.honker.main.Main.game;
import static com.honker.main.Variables.ENTITY_SIZE;
import static com.honker.main.Variables.WINDOW_SIZE;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends NPC {

    public Enemy(NPCLayout layout, int x, int y, Location location, Map map) {
        super(x, y, layout.sprite, location, map, layout.hp, layout.name, layout.level, layout.friendly);
        setWeapon(layout.weapon);
        map.npcs.add(this);
    }
    
    public Enemy(int x, int y, Image image, Location location, Map map, int hp, Weapon weapon, String name, int level, boolean friendly) {
        super(x, y, image, location, map, hp, name, level, friendly);
        setWeapon(weapon);
        map.npcs.add(this);
    }
    
    public void getPath() {
        int xChance = new Random().nextInt(3) + 1;
        if(xChance == 1)
            left = true;
        else if(xChance == 2)
            right = true;
        else
            left = right = false;
        
        if(!left && !right) {
            int yChance = new Random().nextInt(3) + 1;
            if(yChance == 1)
                up = true;
            else if(yChance == 2)
                down = true;
            else
                up = down = false;
        }
    }
    
    @Override
    public void move() {
        getPath();
        super.move();
    }

    @Override
    public void collide(int xvel, int yvel) {
        Rectangle enemyRect = getBounds();
        enemyRect.setLocation(x + xvel, y + yvel);
        
        for(Entity entity : map.entities){
            if(entity != null && !entity.equals(this)){
                if(!(entity instanceof DroppedItem) && enemyRect.intersects(entity)){
                    if(xvel != 0) this.xvel = 0;
                    if(yvel != 0) this.yvel = 0;
                }
            }
        }
        
        for(Player player : game.players) {
            if(player.player.location.equals(location) && player.player.map.equals(map) && player.loggedIn) {
                if(enemyRect.intersects(player.player)) {
                    if(xvel != 0) this.xvel = 0;
                    if(yvel != 0) this.yvel = 0;
                }
            }
        }
        
        for(NPC entity : map.npcs) {
            if(enemyRect.intersects(entity)) {
                if(xvel != 0) this.xvel = 0;
                if(yvel != 0) this.yvel = 0;
            }
        }
        
        if(map.exit != null) {
            if(enemyRect.intersects(map.exit)) {
                x = map.exit.enter.x;
                y = map.exit.enter.y;
                location = map.exit.enter.location;
                map = map.exit.enter.map;
            }
        }
        
        if(map.enter != null) {
            if(enemyRect.intersects(map.enter)) {
                if(xvel != 0) this.xvel = 0;
                if(yvel != 0) this.yvel = 0;
            }
        }
        
        if(xvel != 0 || yvel != 0) {
            int currentLineIndex = 0;
            int currentRoomIndex = 0;
            for(int a = 0; a < location.rooms.length; a++) {
                boolean foundRoom = false;
                for(int b = 0; b < location.rooms[a].length; b++) {
                    if(location.rooms[a][b].equals(map)) {
                        currentLineIndex = a;
                        currentRoomIndex = b;
                        foundRoom = true;
                        break;
                    }
                }

                if(foundRoom) {
                    break;
                }
            }
            
            if(x + xvel > WINDOW_SIZE - ENTITY_SIZE) {
                map = location.rooms[currentLineIndex][currentRoomIndex + 1];
                x = 0;
                this.xvel = 0;
            } else if(x + xvel < 0) {
                map = location.rooms[currentLineIndex][currentRoomIndex - 1];
                x = WINDOW_SIZE - ENTITY_SIZE;
                this.xvel = 0;
            }

            if(y + yvel > WINDOW_SIZE - ENTITY_SIZE) {
                map = location.rooms[currentLineIndex + 1][currentRoomIndex];
                y = 0;
                this.yvel = 0;
            } else if(y + yvel < 0) {
                map = location.rooms[currentLineIndex - 1][currentRoomIndex];
                y = WINDOW_SIZE - ENTITY_SIZE;
                this.yvel = 0;
            }
        }
    }
}
