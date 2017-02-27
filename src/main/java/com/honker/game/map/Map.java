package com.honker.game.map;

import com.honker.game.entities.Entity;
import com.honker.game.entities.blocks.DroppedItem;
import com.honker.game.entities.blocks.Floor;
import com.honker.game.entities.living.Boss;
import com.honker.game.entities.living.Enemy;
import com.honker.game.entities.living.Hero;
import com.honker.game.entities.living.NPC;
import com.honker.game.entities.misc.Enter;
import com.honker.game.entities.misc.Exit;
import com.honker.game.entities.misc.Spawn;
import com.honker.game.items.Item;
import static com.honker.game.items.Weapon.QUALITY_AWFUL;
import static com.honker.game.items.Weapon.QUALITY_GODLY;
import static com.honker.game.items.Weapon.QUALITY_GOOD;
import static com.honker.game.items.Weapon.QUALITY_LEGENDARY;
import static com.honker.game.items.Weapon.QUALITY_NORMAL;
import static com.honker.game.items.Weapon.QUALITY_PARAGON;
import static com.honker.game.items.Weapon.QUALITY_POOR;
import static com.honker.game.items.Weapon.QUALITY_SHODDY;
import static com.honker.game.items.Weapon.QUALITY_SUPERIOR;
import static com.honker.main.Variables.WINDOW_SIZE;
import java.util.ArrayList;
import java.util.Random;

public class Map {
    
    public static int TYPE_STONE = 1;
    public static int TYPE_FOREST = 2;
    
    public static Map mapUpLeft = new Map(
       new String[] {
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGDDGGGGGGGGGG",
        "GGGGGGGGDDDDGGGGGGGG",
        "GGGGGGGDDDDDDGGGGGGG",
        "GGGGGGGDDDDDDGGGGGGG",
        "GGGGGGGGDDDGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG",
        "GGGGGGGGGGGGGGGGGGGG"
    }, new String[] {
        "TTTTTTTTTTTTTTTTTTTT",
        "T                  T",
        "T >                T",
        "T                  T",
        "T                  T",
        "T                  T",
        "T                  T",
        "T                  T",
        "T                  T",
        "T        PP         ",
        "T        PP         ",
        "T                  T",
        "T                  T",
        "T                  T",
        "T                  T",
        "T                  T",
        "T                  T",
        "T                  T",
        "T                  T",
        "TTTTTTTTT  TTTTTTTTT"
    }, new String[] {
        "                    ",
        "                    ",
        "         B          ",
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
    }, TYPE_FOREST);
    
    public static Map mapUpRight = new Map(
       new String[] {
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################"
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
    }, TYPE_STONE);
    
    public static Map mapDownLeft = new Map(
       new String[] {
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################"
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
    }, TYPE_STONE);
    
    public static Map mapDownRight = new Map(
       new String[] {
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################",
        "####################"
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
    }, TYPE_STONE);
    
    public int type;
    public String[] layer1, layer2, layer3;
    
    public Enter enter = null;
    public Exit exit = null;

    public ArrayList<NPC> npcsToRemove = new ArrayList<NPC>();
    public ArrayList<NPC> npcs = new ArrayList<NPC>();
    public ArrayList<Floor> floors = new ArrayList<Floor>();
    public ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Spawn> spawners = new ArrayList<Spawn>();
    
    public Map(String[] layer1, String[] layer2, String[] layer3, int type) {
        this.layer1 = layer1;
        this.layer2 = layer2;
        this.layer3 = layer3;
        this.type = type;
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
            droppedItems.add(Item.getRandomItemWithRarity());
        }
        
        new DroppedItem(dropX, dropY, droppedItems, enemy.location, enemy.map);
        enemy.destroy();
    }
    
    public static void dropLoot(Boss boss) {
        int dropX = boss.x;
        int dropY = boss.y;
        ArrayList<Item> droppedItems = new ArrayList<Item>();
        ArrayList<Item> items = new ArrayList<Item>();
        if(boss.level >= 1 && boss.level <= 10) {
            items.addAll(Item.commonItems);
        } else if(boss.level >= 10 && boss.level <= 20) {
            items.addAll(Item.uncommonItems);
        } else if(boss.level >= 20 && boss.level <= 30) {
            items.addAll(Item.rareItems);
        } else if(boss.level >= 30 && boss.level <= 40) {
            items.addAll(Item.supremeItems);
        } else if(boss.level >= 40 && boss.level <= 50) {
            items.addAll(Item.epicItems);
        } else if(boss.level >= 50 && boss.level <= 60) {
            items.addAll(Item.legendaryItems);
        } else if(boss.level >= 60) {
            items.addAll(Item.ancientItems);
        } else {
            items.addAll(Item.commonItems);
        }
        droppedItems.add(Item.getRandomItemFromList(items));
        int itemsCount = new Random().nextInt(2) + 3;
        for(int a = 0; a < itemsCount; a++) {
            droppedItems.add(Item.getRandomItemWithRarity());
        }
        
        new DroppedItem(dropX, dropY, droppedItems, boss.location, boss.map);
        boss.destroy();
    }
    
    public Floor getRandomEmptyTile() {
        if(floors.isEmpty()) {
            return null;
        }
        
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
        if(floor == null) {
            return new int[] {WINDOW_SIZE / 2, WINDOW_SIZE / 2};
        }
        return new int[] {floor.x, floor.y};
    }
    
    public int getRandomX() {
        return getRandomEmptyTile().x;
    }
    
    public int getRandomY() {
        return getRandomEmptyTile().y;
    }
}
