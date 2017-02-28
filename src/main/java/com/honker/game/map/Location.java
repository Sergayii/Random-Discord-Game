package com.honker.game.map;

import com.honker.game.entities.blocks.Block;
import com.honker.game.entities.blocks.Floor;
import com.honker.game.entities.living.Boss;
import com.honker.game.entities.living.Enemy;
import com.honker.game.entities.living.NPCLayout;
import com.honker.game.entities.misc.BossSpawn;
import com.honker.game.entities.misc.Enter;
import com.honker.game.entities.misc.Exit;
import com.honker.game.entities.misc.PlayerSpawn;
import static com.honker.main.Main.game;
import static com.honker.main.Variables.ENTITY_SIZE;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class Location {
    
    public Map[][] rooms;
    
    public String name;
    
    public Map getWithSpawners() {
        ArrayList<Map> availableMaps = new ArrayList<Map>();
        for(Map[] line : rooms) {
            for(Map map : line) {
                if(!map.spawners.isEmpty()) {
                    availableMaps.add(map);
                }
            }
        }
        if(availableMaps.isEmpty())
            return null;
        return availableMaps.get(new Random().nextInt(availableMaps.size()));
    }
    
    public void generateMisc() {
        new Exit(32 * 2, 32 * 3, this, rooms[0][0], rooms[0][0].enter);
    }
    
    public void generate() {
        for(Map[] line : rooms) {
            for(Map map : line) {
                int x = 0, y = 0;
                for(String row : map.layer1){
                    for(int a = 0; a < row.length(); a++){
                        char col = row.charAt(a);
                        switch(col){
                            case '#':
                                new Floor(x, y, game.img.STONE_FLOOR_SPRITE, this, map);
                                break;
                            case 'D':
                                new Floor(x, y, game.img.DIRT_SPRITE, this, map);
                                break;
                            case 'G':
                                new Floor(x, y, game.img.GRASS_SPRITE, this, map);
                                break;
                            case ' ':
                                new Floor(x, y, game.img.EMPTY_SPRITE, this, map);
                                break;
                        }
                        x += ENTITY_SIZE;
                    }
                    y += ENTITY_SIZE;
                    x = 0;
                }

                x = y = 0;
                for(String row : map.layer2){
                    for(int a = 0; a < row.length(); a++){
                        char col = row.charAt(a);
                        switch(col){
                            case '#':
                                new Block(x, y, game.img.STONE_BLOCK_SPRITE, this, map);
                                break;
                            case 'T':
                                int tree = new Random().nextInt(3) + 1;
                                Image img = tree == 1 ? game.img.TREE1 : tree == 2 ? game.img.TREE2 : tree == 3 ? game.img.TREE3 : null;
                                new Block(x, y, img, this, map);
                                break;
                            case 'P':
                                new PlayerSpawn(x, y, this, map);
                                break;
                            case '>':
                                new Enter(x, y, this, map);
                                break;
                        }
                        x += ENTITY_SIZE;
                    }
                    y += ENTITY_SIZE;
                    x = 0;
                }

                x = y = 0;
                for(String row : map.layer3){
                    for(int a = 0; a < row.length(); a++){
                        char col = row.charAt(a);
                        switch(col){
                            case '*':
                                new Enemy(NPCLayout.DEBUG_ENEMY, x, y, this, map);
                                break;
                            case 'B':
                                new BossSpawn(x, y, this, map, NPCLayout.BOSS1);
                                break;
                        }
                        x += ENTITY_SIZE;
                    }
                    y += ENTITY_SIZE;
                    x = 0;
                }
            }
        }
        
        generateMisc();
    }
    
    public Location(Map[][] rooms, String name) {
        this.rooms = rooms;
        this.name = name;
        generate();
    }
    
    public Location() {
        rooms = new Map[][] {
            new Map[] {Map.mapUpLeft, Map.mapUpRight},
            new Map[] {Map.mapDownLeft, Map.mapDownRight}
        };
        name = "Debug land";
        generate();
    }
}
