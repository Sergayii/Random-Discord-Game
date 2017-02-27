package com.honker.listeners;

import com.honker.game.main.Player;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import com.honker.game.menus.Battle;
import com.honker.game.menus.InventoryMenu;
import static com.honker.main.Main.COMMAND_SYMBOL;
import static com.honker.main.Main.TEXT_CHANNEL_ID;
import static com.honker.main.Main.game;
import static com.honker.main.Main.sendMessage;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class MessageListener {
    
    public void command(IChannel chan, IUser user, String msg) {
        if(msg.length() > 1){
            Player player = game.findPlayer(user);
            Location location;
            Map map;
            if(player != null){
                location = player.player.location;
                map = player.player.map;
            } else {
                location = null;
                map = null;
            }
            
            msg = msg.substring(1).toLowerCase();
            for(String str = " "; str.length() < 1000; str += " ")
                msg = msg.replace(str, " ");
            msg = msg.replace("  ", " ");
            msg = msg.trim();
            String[] cmd = msg.split(" ");
            
            if(cmd[0].equals("help")) {
                if(cmd.length == 1) {
                    sendMessage(chan,
                            "List of commands:\n```"
                            + "!help - shows help\n"
                            + "!move <direction> - moves your character into <direction>\n"
                            + "\t<direction> can be equal to left, right, up, and down\n"
                            + "!move <direction> <n> - moves your character into <direction> <n> times\n"
                            + "\t<direction> can be equal to left, right, up, and down\n"
                            + "\t<n> can vary from 1 to 10\n"
                            + "!skip - skips a turn\n"
                            + "!skip <n> - skips <n> turns\n"
                            + "\t<n> can vary from 1 to 10\n"
                            + "!battle <direction> - starts a battle with enemy standing in <direction>\n"
                            + "\t<direction> can be equal to left, right, up, and down\n"
                            + "!menu - shows current menu\n"
                            + "!menu <choice> - chooses point number <choice> from current menu\n"
                            + "!map - shows the map of current location\n"
                            + "!stats - shows your stats\n"
                            + "!inventory - opens up inventory screen\n"
                            + "!join - logs you into the game\n"
                            + "\tthis command can also create an account if you don't have one\n"
                            + "!leave - logs you out from the game\n"
                            + "```"
                    );
                }
            } else if(cmd[0].equals("move")) {
                if(cmd.length == 2) {
                    if(player == null) {
                        sendMessage(chan, "You aren't logged in, " + user.mention());
                    } else if(player != null && player.player.action) {
                        sendMessage(chan, user.mention() + " , you are already doing something!");
                    } else {
                        if(cmd[1].equals("left")) {
                            player.player.left = true;
                            game.turn(location, map);
                            
                            location = player.player.location;
                            map = player.player.map;
                            if(!player.player.action) {
                                game.sendMap("You are standing here:", location, map);
                            }
                        } else if(cmd[1].equals("right")) {
                            player.player.right = true;
                            game.turn(location, map);
                            
                            location = player.player.location;
                            map = player.player.map;
                            if(!player.player.action) {
                                game.sendMap("You are standing here:", location, map);
                            }
                        } else if(cmd[1].equals("up")) {
                            player.player.up = true;
                            game.turn(location, map);
                            
                            location = player.player.location;
                            map = player.player.map;
                            if(!player.player.action) {
                                game.sendMap("You are standing here:", location, map);
                            }
                        } else if(cmd[1].equals("down")) {
                            player.player.down = true;
                            game.turn(location, map);
                            
                            location = player.player.location;
                            map = player.player.map;
                            if(!player.player.action) {
                                game.sendMap("You are standing here:", location, map);
                            }
                        }
                    }
                } else if(cmd.length == 3) {
                    if(player == null) {
                        sendMessage(chan, "You aren't logged in, " + user.mention());
                    } else if(player != null && player.player.action) {
                        sendMessage(chan, user.mention() + " , you are already doing something!");
                    } else {
                        int turnCount = 1;
                        try {
                            turnCount = Integer.parseInt(cmd[2]);
                            if(turnCount < 1) {
                                sendMessage(chan, "The turn count is too small, " + user.mention() + " , i'll use 1 instead");
                                turnCount = 1;
                            } else if(turnCount > 10) {
                                sendMessage(chan, "The turn count is too bug, " + user.mention() + ", i'll use 1 instead");
                                turnCount = 1;
                            }
                        } catch(NumberFormatException e) {
                            sendMessage(chan, user.mention() + " , the turn count isn't an integer, so i'll use 1 instead");
                            turnCount = 1;
                        }
                        if(cmd[1].equals("left")) {
                            for(int a = 0; a < turnCount; a++) {
                                if(player.player.action)
                                    break;
                                player.player.left = true;
                                game.turn(location, map);
                            }
                            
                            location = player.player.location;
                            map = player.player.map;
                            if(!player.player.action) {
                                game.sendMap("You are standing here:", location, map);
                            }
                        } else if(cmd[1].equals("right")) {
                            for(int a = 0; a < turnCount; a++) {
                                if(player.player.action)
                                    break;
                                player.player.right = true;
                                game.turn(location, map);
                            }
                            
                            location = player.player.location;
                            map = player.player.map;
                            if(!player.player.action) {
                                game.sendMap("You are standing here:", location, map);
                            }
                        } else if(cmd[1].equals("up")) {
                            for(int a = 0; a < turnCount; a++) {
                                if(player.player.action)
                                    break;
                                player.player.up = true;
                                game.turn(location, map);
                            }
                            
                            location = player.player.location;
                            map = player.player.map;
                            if(!player.player.action) {
                                game.sendMap("You are standing here:", location, map);
                            }
                        } else if(cmd[1].equals("down")) {
                            for(int a = 0; a < turnCount; a++) {
                                if(player.player.action)
                                    break;
                                player.player.down = true;
                                game.turn(location, map);
                            }
                            
                            location = player.player.location;
                            map = player.player.map;
                            if(!player.player.action) {
                                game.sendMap("You are standing here:", location, map);
                            }
                        }
                    }
                }
            } else if(cmd[0].equals("skip")) {
                if(player == null) {
                    sendMessage(chan, "You aren't logged in, " + user.mention());
                } else if(player != null && player.player.action) {
                    sendMessage(chan, user.mention() + " , you are already doing something!");
                } else {
                    if(cmd.length == 1) {
                        if(!player.player.action) {
                            game.turn(location, map);
                        }

                        location = player.player.location;
                        map = player.player.map;
                        if(!player.player.action)
                            game.sendMap("You are standing here:", location, map);
                    } else if(cmd.length == 2) {
                        int turnCount = 1;
                        try {
                            turnCount = Integer.parseInt(cmd[1]);
                            if(turnCount < 1) {
                                sendMessage(chan, "The turn count is too small, " + user.mention() + " , i'll use 1 instead");
                                turnCount = 1;
                            } else if(turnCount > 10) {
                                sendMessage(chan, "The turn count is too bug, " + user.mention() + ", i'll use 1 instead");
                                turnCount = 1;
                            }
                        } catch(NumberFormatException e) {
                            sendMessage(chan, user.mention() + " , the turn count isn't an integer, so i'll use 1 instead");
                            turnCount = 1;
                        }

                        for(int a = 0; a < turnCount; a++) {
                            if(player.player.action)
                                break;
                            game.turn(location, map);
                        }

                        location = player.player.location;
                        map = player.player.map;
                        if(!player.player.action) {
                            game.sendMap("You are standing here:", location, map);
                        }
                    }
                }
            } else if(cmd[0].equals("battle")) {
                if(player == null) {
                    sendMessage(chan, "You aren't logged in, " + user.mention());
                } else if(player != null && player.player.action) {
                    sendMessage(chan, user.mention() + " , you are already doing something!");
                } else {
                    if(cmd.length == 2) {
                        if(cmd[1].equals("left")) {
                            player.startBattle(Battle.DIRECTION_LEFT);
                        } else if(cmd[1].equals("right")) {
                            player.startBattle(Battle.DIRECTION_RIGHT);
                        } else if(cmd[1].equals("up")) {
                            player.startBattle(Battle.DIRECTION_UP);
                        } else if(cmd[1].equals("down")) {
                            player.startBattle(Battle.DIRECTION_DOWN);
                        }
                    }
                }
            } else if(cmd[0].equals("menu")) {
                if(player == null) {
                    sendMessage(chan, "You aren't logged in, " + user.mention());
                } else if(player != null && player.battle != null) {
                    if(cmd.length == 1) {
                        player.battle.sendMenu(player.player);
                    } else if(cmd.length == 2) {
                        int choice;
                        try {
                            choice = Integer.parseInt(cmd[1]);
                            if(choice < 1) {
                                sendMessage(chan, "Your choice is too small, " + user.mention());
                                return;
                            }
                        } catch(NumberFormatException e) {
                            sendMessage(chan, user.mention() + " , your choice isn't an integer!");
                            return;
                        }
                        
                        player.battle.choose(choice, player.player);
                    }
                } else if(player != null && player.menu != null) {
                    if(cmd.length == 1) {
                        player.menu.sendMenu();
                    } else if(cmd.length == 2) {
                        int choice;
                        try {
                            choice = Integer.parseInt(cmd[1]);
                            if(choice < 1) {
                                sendMessage(chan, "Your choice is too small, " + user.mention());
                                return;
                            }
                        } catch(NumberFormatException e) {
                            sendMessage(chan, user.mention() + " , your choice isn't an integer!");
                            return;
                        }
                        
                        player.menu.choose(choice);
                    }
                } else {
                    sendMessage(chan, user.mention() + " , you aren't in any menu!");
                }
            } else if(cmd[0].equals("map")) {
                if(cmd.length == 1) {
                    if(player != null && !player.player.action)
                        game.sendMap("Current location: " + location.name, location, map);
                    else if(player != null && player.player.action)
                        sendMessage(chan, user.mention() + " , you are already doing something!");
                    else
                        sendMessage(chan, "You aren't logged in, " + user.mention());
                }
            } else if(cmd[0].equals("stats")) {
                if(player == null) {
                    sendMessage(chan, "You aren't logged in, " + user.mention());
                } else {
                    if(cmd.length == 1) {
                        sendMessage(chan, user.mention() + " 's stats:\n\n" + player.player.getStatsAsString());
                    }
                }
            } else if(cmd[0].equals("inventory")) {
                if(player == null) {
                    sendMessage(chan, "You aren't logged in, " + user.mention());
                } else if(player.player.action) {
                    sendMessage(chan, "You are already doing something, " + user.mention());
                } else {
                    if(cmd.length == 1) {
                       player.setMenu(new InventoryMenu(player));
                       player.menu.sendMenu();
                    }
                }
            } else if(cmd[0].equals("join")) {
                if(cmd.length == 1) {
                    Player login = game.findPlayer(user);
                    if(login == null){
                        sendMessage(chan, "No account found, making one!");
                        login = game.createPlayer(user);
                        sendMessage(chan, "Your account is succesfully created, " + user.mention());
                    } else {
                        if(login.loggedIn)
                            sendMessage(chan, "You already are logged in, " + user.mention());
                        else {
                            login.join();
                            sendMessage(chan, "You are logged in succesfully, " + user.mention());
                        }
                    }
                    location = login.player.location;
                    map = login.player.map;
                    
                    game.sendMap("Current location: " + location.name, location, map);
                }
            } else if(cmd[0].equals("leave")) {
                if(cmd.length == 1) {
                    if(player != null && player.battle != null)
                        sendMessage(chan, user.mention() + " , you are in a battle!");
                    else if(player != null) {
                        if(player.loggedIn){
                            player.leave();
                            sendMessage(chan, "Goodbye, " + user.mention());
                        } else {
                            sendMessage(chan, "You aren't logged in!");
                        }
                    } else {
                        sendMessage(chan, "You aren't logged in!");
                    }
                }
            }
        }
    }
    
    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        IChannel chan = event.getMessage().getChannel();
        IUser user = event.getMessage().getAuthor();
        String message = event.getMessage().getContent();
        
        if(message.startsWith(COMMAND_SYMBOL) && chan.getID().equals(TEXT_CHANNEL_ID)){
            command(chan, user, message);
        }
    }
}
