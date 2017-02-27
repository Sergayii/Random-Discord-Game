package com.honker.game.menus;

import com.honker.game.entities.living.Hero;
import com.honker.game.entities.living.NPC;
import com.honker.game.items.Item;
import com.honker.game.main.Player;
import static com.honker.main.Main.game;
import static com.honker.main.Main.mainChannel;
import static com.honker.main.Main.sendMessage;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public abstract class Battle extends Menu {
    
    public static int DIRECTION_LEFT = 1, DIRECTION_RIGHT = 2, DIRECTION_UP = 3, DIRECTION_DOWN = 4;
    
    public boolean turn1, turn2;
    
    public NPC fighter1;
    public NPC fighter2;
    
    public void turn() {
        if(!(fighter1 instanceof Hero)) {
            if(menu == 1) {
                int choice = 1;
                if(fighter1.hp < (int)(fighter1.hp / 20)) {
                    choice = 2;
                }
                choose(choice, fighter1);
            }
        } else if(!(fighter2 instanceof Hero)) {
            if(menu == 1) {
                int choice = 1;
                if(fighter2.hp < (int)(fighter2.hp / 20)) {
                    choice = 2;
                }
                choose(choice, fighter2);
            }
        }
        game.turn(fighter1.location, fighter1.map);
    }
    
    public void choose(int choice, NPC npc) {
        if(menu == 1) {
            if(npc.equals(fighter1) && turn1) {
                sendMessage(mainChannel, fighter1.name + ", you already turned!");
                return;
            } else if(npc.equals(fighter2) && turn2) {
                sendMessage(mainChannel, fighter2.name + ", you already turned!");
                return;
            }
            
            if(choice == 1) {
                if(npc.equals(fighter1)) {
                    int damageDealt = fighter2.damage(fighter1.getDamage());
                    if(damageDealt == 0) {
                        sendMessage(mainChannel, fighter1.name + " dealt no damage!");
                    } else if(damageDealt == -1) {
                        sendMessage(mainChannel, fighter2.name + " avoided hit!");
                    } else {
                        sendMessage(mainChannel, fighter1.name + " dealt " + damageDealt + " damage!");
                    }
                    
                    if(fighter2.hp <= 0) {
                        stop(fighter1.name + " won!");
                        fighter1.addExp(fighter2.getExpGained());
                        game.removeEntities(fighter1.location, fighter1.map);
                        game.sendMap("Let's look at the battlefield now!", fighter1.location, fighter1.map);
                        return;
                    }
                    
                    turn1 = true;
                    turn2 = false;
                    
                    if(this instanceof PVPBattle) {
                        sendMenu(fighter2);
                    }
                } else if(npc.equals(fighter2)) {
                    int damageDealt = fighter1.damage(fighter2.getDamage());
                    if(damageDealt == 0) {
                        sendMessage(mainChannel, fighter2.name + " dealt no damage!");
                    } else if(damageDealt == -1) {
                        sendMessage(mainChannel, fighter1.name + " avoided hit!");
                    } else {
                        sendMessage(mainChannel, fighter2.name + " dealt " + damageDealt + " damage!");
                    }
                    
                    if(fighter1.hp <= 0) {
                        stop(fighter2.name + " won!");
                        fighter2.addExp(fighter1.getExpGained());
                        game.removeEntities(fighter1.location, fighter1.map);
                        game.sendMap("Let's look at the battlefield now!", fighter1.location, fighter1.map);
                        return;
                    }
                    
                    turn1 = false;
                    turn2 = true;
                    
                    sendMenu(fighter1);
                }
            } else if(choice == 2) {
                menu = 2;
                if(npc.equals(fighter1))
                    sendMenu(fighter1);
                else
                    sendMenu(fighter2);
            } else if(choice == 3) {
                if(npc.equals(fighter1)) {
                    if(npc instanceof Hero) {
                        Player player = game.findPlayer((Hero)npc);
                        int chance = new Random().nextInt(5) + 1;
                        if(chance == 1) {
                            stop(player.user.mention() + " escaped!");
                            game.turn(fighter1.location, fighter1.map);
                            game.sendMap("Let's look at the battlefield now!", fighter1.location, fighter1.map);
                            return;
                        } else {
                            sendMessage(mainChannel, player.user.mention() + " failed to escape!");
                            if(this instanceof PVPBattle) {
                                sendMenu(fighter2);
                            }
                        }
                    } else {
                        int chance = new Random().nextInt(5) + 1;
                        if(chance == 1) {
                            stop(fighter1.name + " escaped!");
                            game.turn(fighter1.location, fighter1.map);
                            game.sendMap("Let's look at the battlefield now!", fighter1.location, fighter1.map);
                            return;
                        } else {
                            sendMessage(mainChannel, fighter1.name + " failed to escape!");
                            if(this instanceof PVPBattle) {
                                sendMenu(fighter2);
                            }
                        }
                    }
                    
                    turn1 = true;
                    turn2 = false;
                } else if(npc.equals(fighter2)) {
                    if(npc instanceof Hero) {
                        Player player = game.findPlayer((Hero)npc);
                        int chance = new Random().nextInt(5) + 1;
                        if(chance == 1) {
                            stop(player.user.mention() + " escaped!");
                            game.turn(fighter1.location, fighter1.map);
                            game.sendMap("Let's look at the battlefield now!", fighter1.location, fighter1.map);
                            return;
                        } else {
                            sendMessage(mainChannel, player.user.mention() + " failed to escape!");
                        }
                        
                        sendMenu(fighter1);
                    } else {
                        int chance = new Random().nextInt(5) + 1;
                        if(chance == 1) {
                            stop(fighter2.name + " escaped!");
                            game.turn(fighter1.location, fighter1.map);
                            game.sendMap("Let's look at the battlefield now!", fighter1.location, fighter1.map);
                            return;
                        } else {
                            sendMessage(mainChannel, fighter2.name + " failed to escape!");
                        }
                        
                        sendMenu(fighter1);
                    }
                    
                    turn1 = false;
                    turn2 = true;
                }
            }
        } else if(menu == 2) {
            Player player;
            if(npc.equals(fighter1) && fighter1 instanceof Hero) {
                player = game.findPlayer((Hero)fighter1);
            } else if(npc.equals(fighter2) && fighter2 instanceof Hero) {
                player = game.findPlayer((Hero)fighter2);
            } else {
                return;
            }
            
            ArrayList<Item> usableItems = new ArrayList<Item>();
            for(Item item : player.player.inventory) {
                if(item.usable) {
                    usableItems.add(item);
                }
            }
            if(choice > 0 && choice <= usableItems.size()) {
                boolean used = player.player.use(usableItems.get(choice - 1));
                if(used) {
                    if(player.player.equals(fighter1)) {
                        turn1 = true;
                        turn2 = false;
                    } else {
                        turn1 = false;
                        turn2 = true;
                    }
                    menu = 1;
                    sendMenu(player.player);
                } else {
                    sendMessage(mainChannel, player.player.name + ", it doesn't seem like you can use that!");
                    sendMenu(player.player);
                }
            } else if(choice == usableItems.size() + 1) {
                menu = 1;
                sendMenu(player.player);
            }
        }
        
        if(this instanceof PVEBattle && !turn2) {
            turn();
        }
    }
    
    public void stop() {
        stop("Battle stopped!");
    }
    
    public void stop(String response) {
        if(fighter1 instanceof Hero) {
            Player player1 = game.findPlayer((Hero)fighter1);
            player1.stopBattle();
        }
        fighter1.action = false;
        
        if(fighter2 instanceof Hero) {
            Player player2 = game.findPlayer((Hero)fighter2);
            player2.stopBattle();
        }
        fighter2.action = false;
        
        sendMessage(mainChannel, response);
    }
    
    public Battle(Image bg) {
        super(bg);
        turn1 = false;
        turn2 = false;
    }
}
