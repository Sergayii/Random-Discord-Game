package com.honker.game.menus;

import com.honker.game.items.Item;
import com.honker.game.main.Player;
import static com.honker.main.Main.game;
import static com.honker.main.Main.mainChannel;
import static com.honker.main.Main.sendMessage;
import java.util.ArrayList;

public class InventoryMenu extends Menu {
    
    public Player player;
    
    @Override
    public void choose(int choice) {
        if(menu == 1) {
            if(choice == 1) {
                menu = 2;
                sendMenu();
            } else if(choice == 2) {
                menu = 3;
                sendMenu();
            } else if(choice == 3) {
                menu = 4;
                sendMenu();
            } else if(choice == 4) {
                menu = 5;
                sendMenu();
            } else if(choice == 5) {
                menu = 6;
                sendMenu();
            } else if(choice == 6) {
                sendMessage(mainChannel, player.user.mention() + " 's inventory:\n" + player.player.getInventoryAsString());
                sendMenu();
            } else if(choice == 7) {
                player.removeMenu();
                sendMessage(mainChannel, player.user.mention() + " , you left the inventory screen");
                game.sendMap("Let's look at the map!", player.player.location, player.player.map);
            }
        } else if(menu == 2) {
            ArrayList<Item> equippableItems = new ArrayList<Item>();
            for(Item item : player.player.inventory) {
                if(item.equippable) {
                    equippableItems.add(item);
                }
            }
            if(choice > 0 && choice <= equippableItems.size()) {
                boolean equipped = player.player.equip(equippableItems.get(choice - 1));
                if(equipped) {
                    sendMessage(mainChannel, player.user.mention() + " , item equipped sucessfully!");
                } else {
                    sendMessage(mainChannel, player.user.mention() + " , it doesn't seem like you can equip that!");
                }
                sendMenu();
            } else if(choice == equippableItems.size() + 1) {
                menu = 1;
                sendMenu();
            }
        } else if(menu == 3) {
            if(choice == 1) {
                int unequipped = player.player.unequip(player.player.weapon);
                if(unequipped == 1) {
                    sendMessage(mainChannel, player.user.mention() + " , item unequipped sucessfully!");
                } else if(unequipped == 0) {
                    sendMessage(mainChannel, player.user.mention() + " , you can't unequip that!");
                } else if(unequipped == -1) {
                    sendMessage(mainChannel, player.user.mention() + " , your inventory is full!");
                }
                sendMenu();
            } else if(choice == 2) {
                menu = 1;
                sendMenu();
            }
        } else if(menu == 4) {
            ArrayList<Item> usableItems = new ArrayList<Item>();
            for(Item item : player.player.inventory) {
                if(item.usable) {
                    usableItems.add(item);
                }
            }
            if(choice > 0 && choice <= usableItems.size()) {
                Item item = usableItems.get(choice - 1);
                boolean used = player.player.use(item);
                if(used) {
                    sendMessage(mainChannel, player.user.mention() + " , item used successfully!");
                } else {
                    sendMessage(mainChannel, player.user.mention() + " , seems like you can't use this item!");
                }
                sendMenu();
            } else if(choice == usableItems.size() + 1) {
                menu = 1;
                sendMenu();
            }
        } else if(menu == 5) {
            if(choice > 0 && choice <= player.player.inventory.size()) {
                boolean dropped = player.player.drop(choice - 1);
                if(dropped) {
                    sendMessage(mainChannel, player.user.mention() + " , item dropped sucessfully!");
                } else {
                    sendMessage(mainChannel, player.user.mention() + " , failed to drop the item!");
                }
                sendMenu();
            } else if(choice == player.player.inventory.size() + 1) {
                menu = 1;
                sendMenu();
            }
        } else if(menu == 6) {
            if(choice > 0 && choice <= player.player.inventory.size()) {
                Item item = player.player.inventory.get(choice - 1);
                sendMessage(mainChannel, item.getInfo());
                sendMenu();
            } else if(choice == player.player.inventory.size() + 1) {
                menu = 1;
                sendMenu();
            }
        }
    }
    
    @Override
    public void sendMenu() {
        if(menu == 1) {
            sendMessage(mainChannel, "Variants:\n1. Equip\n2. Unequip\n3. Use\n4. Drop\n5. Info\n6. Items\n7. Back");
        } else if(menu == 2) {
            String inventoryString = new String();
        
            int index = 1;
            for(Item item : player.player.inventory) {
                if(item != null && item.equippable) {
                    check:
                    if(inventoryString.contains(index + ". ")) {
                        index++;
                        break check;
                    }
                    inventoryString += index + ". " + item.name + "\n";
                    index++;
                }
            }

            if(inventoryString.equals("")) {
                inventoryString += "1. Back";
            } else {
                inventoryString += (inventoryString.split("\n").length + 1) + ". Back";
            }
            sendMessage(mainChannel, "Variants:\n" + inventoryString);
        } else if(menu == 3) {
            sendMessage(mainChannel, "Variants:\n1. Weapon\n2. Back");
        } else if(menu == 4) {
            String inventoryString = new String();
        
            int index = 1;
            for(Item item : player.player.inventory) {
                if(item != null && item.usable) {
                    check:
                    if(inventoryString.contains(index + ". ")) {
                        index++;
                        break check;
                    }
                    inventoryString += index + ". " + item.name + "\n";
                    index++;
                }
            }

            if(inventoryString.equals("")) {
                inventoryString += "1. Back";
            } else {
                inventoryString += (inventoryString.split("\n").length + 1) + ". Back";
            }
            sendMessage(mainChannel, "Variants:\n" + inventoryString);
        } else if(menu == 5) {
            String inventoryString = new String();
        
            for(Item item : player.player.inventory) {
                if(item != null) {
                    int index = player.player.inventory.indexOf(item) + 1;
                    check:
                    if(inventoryString.contains(index + ". ")) {
                        index++;
                        break check;
                    }
                    inventoryString += index + ". " + item.name + "\n";
                }
            }

            if(inventoryString.equals("")) {
                inventoryString += "1. Back";
            } else {
                inventoryString += (inventoryString.split("\n").length + 1) + ". Back";
            }
            sendMessage(mainChannel, "Variants:\n" + inventoryString);
        } else if(menu == 6) {
            String inventoryString = new String();
        
            for(Item item : player.player.inventory) {
                if(item != null) {
                    int index = player.player.inventory.indexOf(item) + 1;
                    check:
                    if(inventoryString.contains(index + ". ")) {
                        index++;
                        break check;
                    }
                    inventoryString += index + ". " + item.name + "\n";
                }
            }

            if(inventoryString.equals("")) {
                inventoryString += "1. Back";
            } else {
                inventoryString += (inventoryString.split("\n").length + 1) + ". Back";
            }
            sendMessage(mainChannel, "Variants:\n" + inventoryString);
        } else if(menu == 7) {
            String inventoryString = new String();
        
            int index = 1;
            for(Item item : player.player.inventory) {
                if(item != null && item.usable) {
                    check:
                    if(inventoryString.contains(index + ". ")) {
                        index++;
                        break check;
                    }
                    inventoryString += index + ". " + item.name + "\n";
                    index++;
                }
            }

            if(inventoryString.equals("")) {
                inventoryString += "1. Back";
            } else {
                inventoryString += (inventoryString.split("\n").length + 1) + ". Back";
            }
            sendMessage(mainChannel, "Variants:\n" + inventoryString);
        }
    }
    
    public InventoryMenu(Player player) {
        super(null);
        this.player = player;
    }
}
