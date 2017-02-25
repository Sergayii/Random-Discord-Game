package com.honker.game.entities.living;

import com.honker.game.entities.Entity;
import com.honker.game.entities.blocks.DroppedItem;
import com.honker.game.items.Item;
import com.honker.game.items.Weapon;
import com.honker.game.main.Player;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import com.honker.game.menus.PVEBattle;
import static com.honker.main.Main.game;
import static com.honker.main.Main.mainChannel;
import static com.honker.main.Main.sendMessage;
import static com.honker.main.Variables.ENTITY_SIZE;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public abstract class NPC extends Entity {
    
    public boolean action = false, friendly;
    public int STR = 0, AG = 0, DEF = 0, SPD = 0;
    public int statsSTR = 0, statsAG = 0, statsDEF = 0, statsSPD = 0;
    public int equipSTR = 0, equipAG = 0, equipDEF = 0, equipSPD = 0;
    public int exp = 0, expUntilNextLevel = 100, level;
    public int maxInventorySize = 20;
    
    public Weapon weapon;
    public ArrayList<Item> inventory = new ArrayList<Item>();
    
    public String name;
    
    public int getDamage() {
        return weapon.getDamage() + STR;
    }
    
    public int addHP(int count) {
        int oldHP = hp;
        hp += count;
        hpCheck();
        if(hp - oldHP <= count)
            return hp - oldHP;
        else
            return count;
    }
    
    public int damage(int damage) {
        int dodge = new Random().nextInt(100) + 1;
        if(dodge <= AG) {
            damage = -1;
        } else {
            damage -= DEF;
            if(damage < 0)
                damage = 0;
            hp -= damage;
            hpCheck();
        }
        
        return damage;
    }
    
    public boolean drop(int index) {
        if(index < 0 || index >= inventory.size()) {
            return false;
        }
        ArrayList<Item> droppedItems = new ArrayList<Item>();
        droppedItems.add(inventory.get(index));
        new DroppedItem(x, y, droppedItems, location, map);
        inventory.remove(index);
        return true;
    }
    
    public void addToInventory(DroppedItem droppedItem) {
        ArrayList<Item> itemsToRemove = new ArrayList<Item>();
        if(droppedItem.items != null) {
            for(Item item : droppedItem.items) {
                addToInventory(item);
                itemsToRemove.add(item);
            }
        }
        droppedItem.items.removeAll(itemsToRemove);
    }
    
    public void addToInventory(Item item) {
        if(item != null && !item.equals(Weapon.DEFAULT)) {
            inventory.add(item);
        }
    }
    
    public String getInventoryAsString() {
        String inventoryString = "";
        
        if(weapon == null) {
            inventoryString += "Current weapon: None\n\n";
        } else {
            inventoryString += "Current weapon: " + weapon.name + "\n\n";
        }
        
        inventoryString += "Inventory:\n";
        
        int index = 1;
        for(Item item : inventory) {
            if(item != null) {
                check:
                if(inventoryString.contains(index + ". ")) {
                    index++;
                    break check;
                }
                inventoryString += index + ". " + item.name + "\n";
                index++;
            }
        }
        
        if(inventoryString.endsWith("Inventory:\n")) {
            inventoryString += "(Empty)";
        }
        
        return inventoryString;
    }
    
    public String getStatsAsString() {
        return  "HP: " + hp + "\nLevel: " + level + "\n\nEXP: " + exp + "\nEXP until next level: " + 
                (expUntilNextLevel - exp) + "\n\nWeapon attack: " + weapon.maxDamage + "\n\nStrength: " + STR + "\nAgility: " + AG + "\nDefense: " + DEF +
                "\nSpeed: " + SPD;
    }
    
    public int unequip(Item item) {
        if(inventory.size() > maxInventorySize - 1) {
            return -1;
        }
        
        Item itemUnequipped;
        if(item.equals(weapon)) {
            if(item.equals(Weapon.DEFAULT)) {
                return 0;
            }
            equipSTR -= weapon.STR;
            equipAG -= weapon.AG;
            equipDEF -= weapon.DEF;
            equipSPD -= weapon.SPD;
            itemUnequipped = weapon;
            weapon = Weapon.DEFAULT;
            addToInventory(itemUnequipped);
            statsCheck();
            return 1;
        }
        
        return 0;
    }
    
    public boolean equip(Item item) {
        if(item instanceof Weapon) {
            Weapon equippedWeapon = (Weapon)item;
            Weapon oldWeapon = weapon;
            setWeapon(equippedWeapon);
            inventory.remove(inventory.indexOf(item));
            equipSTR += weapon.STR;
            equipAG += weapon.AG;
            equipDEF += weapon.DEF;
            equipSPD += weapon.SPD;
            statsCheck();
            addToInventory(oldWeapon);
            return true;
        }
        
        return false;
    }
    
    public void setWeapon(Weapon weapon) {
        weapon.setOwner(this);
    }
    
    @Override
    public void destroy() {
        map.npcsToRemove.add(this);
    }
    
    public int getExpGained() {
        int expScale = 10;
        return expScale * (level - 1) + new Random().nextInt(expScale * level - expScale * (level - 1)) + 1;
    }
    
    public void addExp(int count) {
        for(int a = 0; a < count; a++) {
            exp++;
            expCheck();
        }
    }
    
    public void expCheck() {
        if(exp > expUntilNextLevel) {
            exp = 0;
            level++;
            
            int statUpgraded = new Random().nextInt(4) + 1;
            if(statUpgraded == 1) {
                STR++;
            } else if(statUpgraded == 2) {
                AG++;
            } else if(statUpgraded == 3) {
                DEF++;
            } else if(statUpgraded == 4) {
                SPD++;
            }
            
            statsCheck();
        }
        
        expUntilNextLevel = 100 * level;
    }
    
    public void statsCheck() {
        if(statsSTR > 100) {
            statsSTR = 100;
        }
        if(statsAG > 50) {
            statsAG = 50;
        }
        if(statsDEF > 100) {
            statsDEF = 100;
        }
        if(statsSPD > 100) {
            statsSPD = 100;
        }
        
        STR = statsSTR + equipSTR;
        AG = statsAG + equipAG;
        DEF = statsDEF + equipDEF;
        SPD = statsSPD + equipSPD;
        
        if(STR > 200) {
            STR = 200;
        }
        if(AG > 90) {
            AG = 90;
        }
        if(DEF > 200) {
            DEF = 200;
        }
        if(SPD > 100) {
            SPD = 100;
        }
    }
    
    private Player findPlayer(Rectangle rect) {
        for(Player player : game.players) {
            if(player.player.location.equals(location) && player.player.map.equals(map)) {
                if(player.player.intersects(rect)) {
                    return player;
                }
            }
        }
        return null;
    }
    
    public boolean attack() {
        if(friendly || action)
            return false;
        
        Rectangle rect = getBounds();
        Player player = null;
        
        rect.setLocation(x + ENTITY_SIZE, y);
        player = findPlayer(rect);
        if(player != null) {
            attack(player);
            return true;
        }
        
        rect.setLocation(x - ENTITY_SIZE, y);
        player = findPlayer(rect);
        if(player != null) {
            attack(player);
            return true;
        }
        
        rect.setLocation(x, y - ENTITY_SIZE);
        player = findPlayer(rect);
        if(player != null) {
            attack(player);
            return true;
        }
        
        rect.setLocation(x, y + ENTITY_SIZE);
        player = findPlayer(rect);
        if(player != null) {
            attack(player);
            return true;
        }
        
        return false;
    }
    
    public void attack(Player player) {
        if(action)
            return;
        
        Image bg = null;
        if(location.equals(game.locations.get(0))) {
            bg = game.img.STONE_BACKGROUND;
        }
        
        sendMessage(mainChannel, name + " started battle with " + player.user.mention());
        PVEBattle battle = new PVEBattle(bg, player.player, this);
        battle.sendMenu();
    }
    
    @Override
    public void move() {
        if(action) {
            return;
        }
        
        if(left) xvel = -ENTITY_SIZE;
        else if(right) xvel = ENTITY_SIZE;
        else if((!left && !right) || (left && right)) xvel = 0;
        
        collide(xvel, 0);
        setLocation(x + xvel, y);
        
        if(up) yvel = -ENTITY_SIZE;
        else if(down) yvel = ENTITY_SIZE;
        else if((!up && !down) || (up && down)) yvel = 0;
        
        collide(0, yvel);
        setLocation(x, y + yvel);
        
        left = right = up = down = false;
    }
    
    public NPC(int x, int y, Image image, Location location, Map map, int hp, String name, int level, boolean friendly) {
        super(x, y, image, location, map, hp);
        this.level = level;
        this.name = name;
        this.friendly = friendly;
        expCheck();
        hpCheck();
    }
}
