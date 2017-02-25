package com.honker.game.entities.living;

import com.honker.game.entities.Entity;
import com.honker.game.entities.blocks.DroppedItem;
import com.honker.game.items.Item;
import com.honker.game.items.Potion;
import com.honker.game.items.Weapon;
import com.honker.game.main.Player;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import static com.honker.main.Main.game;
import static com.honker.main.Main.mainChannel;
import static com.honker.main.Main.sendMessage;
import static com.honker.main.Variables.ENTITY_SIZE;
import static com.honker.main.Variables.WINDOW_SIZE;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Hero extends NPC {
    
    public Hero(int x, int y, Location location, Map map, int hp, String name, int level) {
        super(x, y, game.img.HERO_SPRITE, location, map, hp, name, level, true);
        setWeapon(Weapon.BEGINNER_SWORD);
    }
    
    public boolean use(Item item) {
        Player player = game.findPlayer((Hero)this);
        if(item instanceof Potion) {
            Potion potion = (Potion)item;
            if(player.battle != null) {
                potion.inBattle(player);
            } else {
                potion.inGame(player);
            }
            inventory.remove(potion);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean attack(){
        return false;
    }
    
    @Override
    public void attack(Player player) {
        game.findPlayer(this).startBattle(player);
    }
    
    public void attack(NPC npc) {
        game.findPlayer(this).startBattle(npc);
    }
    
    @Override
    public void collide(int xvel, int yvel) {
        Rectangle playerRect = getBounds();
        playerRect.setLocation(x + xvel, y + yvel);
        
        ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();
        for(Entity entity : map.entities){
            if(entity != null && !entity.equals(this)){
                if(playerRect.intersects(entity)){
                    if(entity instanceof DroppedItem) {
                        if(inventory.size() > maxInventorySize) {
                            sendMessage(mainChannel, game.findPlayer(this).user.mention() + " , your inventory is full!");
                        } else {
                            DroppedItem item = (DroppedItem)entity;
                            addToInventory(item);
                            if(item.items.isEmpty()) {
                                entitiesToRemove.add(entity);
                                entitiesToRemove.add(item);
                            }
                        }
                    } else {
                        if(xvel != 0) this.xvel = 0;
                        if(yvel != 0) this.yvel = 0;
                    }
                }
            }
        }
        
        for(Player player : game.players) {
            if(player.player.location.equals(location) && player.player.map.equals(map) && player.loggedIn) {
                if(playerRect.intersects(player.player)) {
                    if(xvel != 0) this.xvel = 0;
                    if(yvel != 0) this.yvel = 0;
                }
            }
        }
        
        for(NPC entity : map.npcs) {
            if(playerRect.intersects(entity)) {
                if(xvel != 0) this.xvel = 0;
                if(yvel != 0) this.yvel = 0;
            }
        }
        
        if(map.exit != null) {
            if(playerRect.intersects(map.exit)) {
                x = map.exit.enter.x;
                y = map.exit.enter.y;
                location = map.exit.enter.location;
                map = map.exit.enter.map;
            }
        }
        
        if(map.enter != null) {
            if(playerRect.intersects(map.enter)) {
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
        
        for(Entity entity : entitiesToRemove) {
            entity.destroy();
        }
    }
    
    @Override
    public void destroy() {
        game.playersToRemove.add(game.findPlayer(this));
    }
    
    @Override
    public void draw(Graphics g) {
        draw(g, x - ENTITY_SIZE / 2, y - ENTITY_SIZE / 2);
    }
}
