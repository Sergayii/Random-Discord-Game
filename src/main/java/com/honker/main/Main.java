package com.honker.main;

import com.honker.game.main.Game;
import com.honker.game.map.Location;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.RateLimitException;

public class Main implements Runnable {
    
    public static final String COMMAND_SYMBOL = "!";
    public static String BOT_TOKEN, TEXT_CHANNEL_ID, GUILD_ID, FILES_PATH;
    public static boolean ready = false;
    
    public static Game game;
    public static Bot bot;
    
    public static IChannel mainChannel;
    
    public static String message = "";
    
    @Override
    public void run() {
        while(true) {
            flushMessages();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void flushMessages() {
        if(message.equals("")) {
            return;
        }
        
        try {
            mainChannel.sendMessage(message);
            message = "";
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void sendFile(IChannel chan, String message, File file){
        flushMessages();
        try {
            chan.sendFile(message, file);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void sendFile(IChannel chan, File file){
        sendFile(chan, "", file);
    }
    
    public static void sendFile(IChannel chan, String message, InputStream stream){
        flushMessages();
        try {
            chan.sendFile(message, false, stream, "Image.png");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void sendMessage(IChannel chan, String msg){
        message += msg + "\n\n";
    }
    
    public static void restartLater() {
        sendMessage(mainChannel, "Restarting the game, please wait!");
        game.stop();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        game = new Game();
        game.start();
    }
    
    @EventSubscriber
    public static void onReadyEvent(ReadyEvent e) {
        if(!ready) {
            game = new Game();
            game.locations.add(new Location());
            
            mainChannel = bot.client.getChannelByID(TEXT_CHANNEL_ID);

            game.updatePlayers();
            game.start();

            new Thread(new Main()).start();
            
            ready = true;
        }
    }
    
    public static void shutdown() {
        exit("I'm leaving to apply an update, goodbye!");
        System.exit(0);
    }

    public static void restart() {
        exit("Restarting");
        join();
    }

    public static void exit() {
        exit("");
    }

    public static void exit(String exitMessage) {
        List<IVoiceChannel> voiceChannels = bot.client.getConnectedVoiceChannels();
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                for(IVoiceChannel channel : voiceChannels){
                    if(channel.isConnected())
                        channel.leave();
                }
            }
        }).start();

        try {
            if(exitMessage != null && !exitMessage.equals(""))
                mainChannel.sendMessage(exitMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            bot.client.logout();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ready = false;
    }

    public static void join() {
        bot = new Bot();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner settingsReader = new Scanner(new File("./settings.txt"));
        StringBuilder string = new StringBuilder();
        while(settingsReader.hasNext()) {
            String line = settingsReader.nextLine();
            string.append(line);
            string.append(System.lineSeparator());
        }

        String[] settingsList = string.toString().split(System.lineSeparator());
        ArrayList<String> settings = new ArrayList<String>();
        settings.addAll(Arrays.asList(settingsList));

        for(String setting : settings) {
            if(setting.startsWith("BOT_TOKEN = "))
                BOT_TOKEN = setting.replaceFirst("BOT_TOKEN = ", "");
            else if(setting.startsWith("TEXT_CHANNEL_ID = "))
                TEXT_CHANNEL_ID = setting.replaceFirst("TEXT_CHANNEL_ID = ", "");
            else if(setting.startsWith("GUILD_ID = "))
                GUILD_ID = setting.replaceFirst("GUILD_ID = ", "");
            else if(setting.startsWith("FILES_PATH = "))
                FILES_PATH = setting.replaceFirst("FILES_PATH = ", "");
        }
        
        join();
    }
}
