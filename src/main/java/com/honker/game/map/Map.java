package com.honker.game.map;

import com.honker.game.entities.Entity;
import com.honker.game.entities.blocks.DroppedItem;
import com.honker.game.entities.blocks.Floor;
import com.honker.game.entities.living.Enemy;
import com.honker.game.entities.living.Hero;
import com.honker.game.entities.living.NPC;
import com.honker.game.entities.misc.Enter;
import com.honker.game.entities.misc.Exit;
import com.honker.game.entities.misc.Spawn;
import com.honker.game.items.Item;
import java.util.ArrayList;
import java.util.Random;

public class Map {
    
    public static Map mapUpLeft = new Map(
       new String[] {
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    "
    }, new String[] {
        "####################",
        "#                  #",
        "# >                #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#        PP         ",
        "#        PP         ",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#########  #########"
    }, new String[] {
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    "
    });
    
    public static Map mapUpRight = new Map(
       new String[] {
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    "
    }, new String[] {
        "####################",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "                   #",
        "                   #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#########  #########"
    }, new String[] {
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    "
    });
    
    public static Map mapDownLeft = new Map(
       new String[] {
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    "
    }, new String[] {
        "#########  #########",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                   ",
        "#                   ",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "####################"
    }, new String[] {
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    "
    });
    
    public static Map mapDownRight = new Map(
       new String[] {
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    "
    }, new String[] {
        "#########  #########",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "                   #",
        "                   #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "#                  #",
        "####################"
    }, new String[] {
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    ",
        "                    "
    });
    
    public String[] layer1, layer2, layer3;
    
    public Enter enter = null;
    public Exit exit = null;

    public ArrayList<NPC> npcsToRemove = new ArrayList<NPC>();
    public ArrayList<NPC> npcs = new ArrayList<NPC>();
    public ArrayList<Floor> floors = new ArrayList<Floor>();
    public ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Spawn> spawners = new ArrayList<Spawn>();
    
    public Map(String[] layer1, String[] layer2, String[] layer3) {
        this.layer1 = layer1;
        this.layer2 = layer2;
        this.layer3 = layer3;
    }
    
    public void removeEntities() {
        for(Entity entity : entitiesToRemove) {
            entities.remove(entity);
        }
        entitiesToRemove.clear();
    }
    
    public void removeNPCs() {
        for(NPC npc : npcsToRemove) {
            npcs.remove(npc);
        }
        npcsToRemove.clear();
    }
    
    public static void dropLoot(Hero hero) {
        int dropX = hero.x;
        int dropY = hero.y;
        ArrayList<Item> droppedItems = new ArrayList<Item>();
        droppedItems.add(hero.weapon);
        for(Item item : hero.inventory) {
            droppedItems.add(item);
        }
        
        new DroppedItem(dropX, dropY, droppedItems, hero.location, hero.map);
        hero.destroy();
    }
    
    public static void dropLoot(Enemy enemy) {
        int dropX = enemy.x;
        int dropY = enemy.y;
        ArrayList<Item> droppedItems = new ArrayList<Item>();
        int itemsCount = new Random().nextInt(3) + 1;
        for(int a = 0; a < itemsCount; a++) {
            droppedItems.add(Item.getRandomItem());
        }
        
        new DroppedItem(dropX, dropY, droppedItems, enemy.location, enemy.map);
        enemy.destroy();
    }
    
    public Floor getRandomEmptyTile() {
        Floor floor;
        while(true) {
            floor = floors.get(new Random().nextInt(floors.size()));
            boolean generated = true;
            for(Entity entity : entities) {
                if(entity.intersects(floor)) {
                    generated = false;
                    break;
                }
            }
            for(NPC npc : npcs) {
                if(npc.intersects(floor)) {
                    generated = false;
                    break;
                }
            }
            if((exit != null && exit.intersects(floor)) || (enter != null && enter.intersects(floor))) {
                generated = false;
                break;
            }
            if(generated)
                break;
        }
        
        return floor;
    }
    
    public int[] getRandomPosition() {
        Floor floor = getRandomEmptyTile();
        return new int[] {floor.x, floor.y};
    }
    
    public int getRandomX() {
        return getRandomEmptyTile().x;
    }
    
    public int getRandomY() {
        return getRandomEmptyTile().y;
    }
}
