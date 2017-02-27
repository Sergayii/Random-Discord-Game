package com.honker.game.main;

import com.honker.game.entities.Entity;
import com.honker.game.entities.blocks.Floor;
import com.honker.game.entities.living.Enemy;
import com.honker.game.entities.living.NPC;
import com.honker.game.entities.living.Hero;
import com.honker.game.entities.living.NPCLayout;
import com.honker.game.entities.misc.Spawn;
import com.honker.game.items.Armor;
import com.honker.game.items.Potion;
import com.honker.game.items.Weapon;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import com.honker.main.Images;
import static com.honker.main.Main.bot;
import static com.honker.main.Main.mainChannel;
import static com.honker.main.Main.sendMessage;
import static com.honker.main.Variables.WINDOW_SIZE;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;
import sx.blah.discord.handle.obj.IUser;
import static com.honker.main.Main.sendFile;

public class Game {
    
    public Images img;
    
    public ArrayList<Location> locations = new ArrayList<Location>();
    public ArrayList<Player> playersToRemove = new ArrayList<Player>();
    public ArrayList<Player> players = new ArrayList<Player>();
    
    public void updatePlayers() {
        List<IUser> users = bot.client.getUsers();
        for(Player player : players) {
            if(!users.contains(player.user))
                player.loggedIn = false;
        }
    }
    
    public Player createPlayer(IUser user) {
        int x = 0, y = 0;
        Spawn spawn = locations.get(0).getWithSpawners().spawners.get(new Random().nextInt(locations.get(0).getWithSpawners().spawners.size()));
        x = spawn.x;
        y = spawn.y;
        
        Player player = new Player(user, new Hero(x, y, locations.get(0), locations.get(0).getWithSpawners(), 100, user.getName(), 1));
        players.add(player);
        player.player.addToInventory(Weapon.DEBUG_SWORD);
        player.player.addToInventory(Potion.DEBUG_POTION);
        player.player.addToInventory(Potion.DEBUG_POTION);
        player.player.addToInventory(Potion.DEBUG_POTION);
//        player.player.addToInventory(Potion.DEBUG_POTION);
//        player.player.addToInventory(Potion.DEBUG_POTION);
        player.player.addToInventory(Armor.DEBUG_HEAD);
        player.player.addToInventory(Armor.DEBUG_BODY);
        player.player.addToInventory(Armor.DEBUG_ARMS);
        player.player.addToInventory(Armor.DEBUG_LEGS);
        return player;
    }
    
    public Player findPlayer(Hero hero) {
        for(Player player : players) {
            if(player != null && player.player.equals(hero))
                return player;
        }
        return null;
    }
    
    public Player findPlayer(IUser user) {
        for(Player player : players) {
            if(player != null && player.user.equals(user))
                return player;
        }
        return null;
    }
    
    public void removePlayers() {
        for(Player player : playersToRemove) {
            players.remove(player);
        }
        playersToRemove.clear();
    }
    
    public void removeEntities(Location location, Map map) {
        removePlayers();
        map.removeEntities();
        map.removeNPCs();
    }
    
    public void turn(Location location, Map map) {
        removeEntities(location, map);
        
        for(Player player : players) {
            player.player.move();
        }
        for(NPC npc : map.npcs) {
            boolean attacked = npc.attack();
            if(!attacked) {
                npc.move();
            }
        }
        for(Entity entity : map.entities) {
            entity.move();
        }
        
        removePlayers();
        map.removeEntities();
        map.removeNPCs();
        
        for(Location loc : locations) {
            for(Map[] row : loc.rooms) {
                for(Map col : row) {
                    boolean noPlayers = true;
                    for(Player player : players) {
                        if(player.player.location.equals(loc) && player.player.map.equals(col)) {
                            noPlayers = false;
                            break;
                        }
                    }
                    
                    if(noPlayers && col.npcs.isEmpty()) {
                        int count = new Random().nextInt(5) + 1;
                        for(int a = 0; a < count; a++) {
                            int[] randomPos = col.getRandomPosition();
                            new Enemy(NPCLayout.DEBUG_ENEMY, randomPos[0], randomPos[1], loc, col);
                        }
                    }
                }
            }
        }
    }
    
    public void sendMap(String response, Location location, Map map) {
        BufferedImage image = new BufferedImage(WINDOW_SIZE, WINDOW_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        
        g.clearRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
        
        for(Floor floor : map.floors) {
            floor.draw(g);
        }
        for(Entity entity : map.entities) {
            entity.draw(g);
        }
        for(NPC entity : map.npcs) {
            entity.draw(g);
        }
        for(Player player : players) {
            if(player.loggedIn && player.player.location.equals(location) && player.player.map.equals(map)) {
                player.player.draw(g);
            }
        }
        if(map.exit != null) map.exit.draw(g);
        if(map.enter != null) map.enter.draw(g);
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try {
            sendFile(mainChannel, response, new ByteArrayInputStream(output.toByteArray()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void start() {
        sendMessage(mainChannel, "Game started!\nSend \"!join\" if you want to play!");
    }
    
    public void stop() {
        players.clear();
        locations.clear();
    }
    
    public Game() {
        img = new Images();
    }
}
