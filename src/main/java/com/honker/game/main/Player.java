package com.honker.game.main;

import com.honker.game.entities.living.Hero;
import com.honker.game.entities.living.NPC;
import com.honker.game.map.Map;
import com.honker.game.menus.Battle;
import com.honker.game.menus.Menu;
import com.honker.game.menus.PVEBattle;
import static com.honker.game.menus.PVEBattle.DIRECTION_DOWN;
import static com.honker.game.menus.PVEBattle.DIRECTION_LEFT;
import static com.honker.game.menus.PVEBattle.DIRECTION_RIGHT;
import static com.honker.game.menus.PVEBattle.DIRECTION_UP;
import com.honker.game.menus.PVPBattle;
import static com.honker.main.Main.game;
import static com.honker.main.Main.mainChannel;
import static com.honker.main.Main.sendMessage;
import static com.honker.main.Variables.ENTITY_SIZE;
import java.awt.Image;
import java.awt.Rectangle;
import sx.blah.discord.handle.obj.IUser;

public class Player {
    
    public String name;
    
    public Menu menu;
    public Battle battle;
    public Hero player;
    
    public IUser user;
    
    public boolean loggedIn;
    
    public void leave() {
        loggedIn = false;
    }
    
    public void join() {
        loggedIn = true;
    }
    
    public void removeMenu() {
        menu = null;
        player.action = false;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
        player.action = true;
    }
    
    public void startBattle(int direction) {
        Rectangle hitbox = player.getBounds();
        
        if(direction == DIRECTION_LEFT) {
            hitbox.setLocation(player.x - ENTITY_SIZE, player.y);
        } else if(direction == DIRECTION_RIGHT) {
            hitbox.setLocation(player.x + ENTITY_SIZE, player.y);
        } else if(direction == DIRECTION_UP) {
            hitbox.setLocation(player.x, player.y - ENTITY_SIZE);
        } else if(direction == DIRECTION_DOWN) {
            hitbox.setLocation(player.x, player.y + ENTITY_SIZE);
        }
        
        for(NPC npc : player.map.npcs) {
            if(npc.intersects(hitbox)) {
                if(!npc.action)
                    startBattle(npc);
                else
                    sendMessage(mainChannel, user.mention() + " , this npc is doing something");
                return;
            }
        }
        
        for(Player player : game.players) {
            if(player.loggedIn && player.player.location.equals(this.player.location) && player.player.map.equals(this.player.map) && player.player.intersects(hitbox)) {
                if(!player.player.action)
                    startBattle(player);
                else
                    sendMessage(mainChannel, user.mention() + " , this player is doing something");
                return;
            }
        }
        
        sendMessage(mainChannel, user.mention() + " , i found no enemy here!");
    }
    
    public void startBattle(Player player2) {
        Hero hero = player2.player;

        if(hero.action) {
            sendMessage(mainChannel, user.mention() + " , the player is already doing something!");
            return;
        }
        
        Image bg = null;
        if(player.location.equals(game.locations.get(0))) {
            bg = game.img.STONE_BACKGROUND;
        }
        
        sendMessage(mainChannel, user.mention() + " started battle with " + hero.name);
        battle = new PVPBattle(bg, this, player2);
        player2.battle = battle;
        player.action = true;
        hero.action = true;
        battle.sendMenu("");
    }
    
    public void startBattle(NPC entity) {
        if(entity.friendly) {
            sendMessage(mainChannel, user.mention() + " , you can't start battle with this NPC!");
            return;
        } else if(entity.action) {
            sendMessage(mainChannel, user.mention() + " , this NPC is already doing someething!");
            return;
        }
        
        Image bg = null;
        if(player.map.type == Map.TYPE_STONE) {
            bg = game.img.STONE_BACKGROUND;
        } else if(player.map.type == Map.TYPE_FOREST) {
            bg = game.img.FOREST_BACKGROUND;
        }
        
        sendMessage(mainChannel, user.mention() + " started battle with " + entity.name);
        battle = new PVEBattle(bg, player, entity);
        player.action = true;
        entity.action = true;
        battle.sendMenu("");
    }
    
    public void stopBattle() {
        battle = null;
        menu = null;
        player.action = false;
    }
    
    public Player(IUser user, Hero hero) {
        this.user = user;
        name = user.getName();
        player = hero;
        join();
    }
}
