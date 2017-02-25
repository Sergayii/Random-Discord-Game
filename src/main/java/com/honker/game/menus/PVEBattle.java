package com.honker.game.menus;

import com.honker.game.entities.living.Hero;
import com.honker.game.entities.living.NPC;
import com.honker.game.items.Item;
import com.honker.game.main.Player;
import static com.honker.main.Main.game;
import static com.honker.main.Main.mainChannel;
import static com.honker.main.Main.sendFile;
import static com.honker.main.Main.sendMessage;
import static com.honker.main.Variables.WINDOW_SIZE;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class PVEBattle extends Battle {
    
    public PVEBattle(Image bg, Hero fighter1, NPC fighter2) {
        super(bg);
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        
        if(fighter1.SPD > fighter2.SPD) {
            turn1 = false;
            turn2 = true;
        } else if(fighter2.SPD > fighter1.SPD) {
            turn1 = true;
            turn2 = false;
        } else {
            int chance = new Random().nextInt(2) + 1;
            
            if(chance == 1) {
                turn1 = false;
                turn2 = true;
            } else if(chance == 2) {
                turn1 = true;
                turn2 = false;
            }
        }
        
        Player player = game.findPlayer(fighter1);
        player.battle = this;
        fighter1.action = true;
        fighter2.action = true;
        
        if(turn1) {
            turn();
        } else if(turn2) {
            sendMenu(fighter1);
        }
    }
    
    @Override
    public void sendMenu(String response, NPC npc) {
        if(menu == 1) {
            sendFightersStats();

            BufferedImage image = new BufferedImage(WINDOW_SIZE, WINDOW_SIZE, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();

            drawBg(g);

            if(npc.equals(fighter1)) {
                Image sprite = fighter2.sprite;
                Image scaledSprite = sprite.getScaledInstance(sprite.getWidth(null) * 10, sprite.getHeight(null) * 10, Image.SCALE_DEFAULT);
                g.drawImage(scaledSprite, WINDOW_SIZE / 2 - scaledSprite.getWidth(null) / 2, WINDOW_SIZE / 2 - scaledSprite.getHeight(null) / 2 + 100, null);
            } else if(npc.equals(fighter2)) {
                Image sprite = fighter1.sprite;
                Image scaledSprite = sprite.getScaledInstance(sprite.getWidth(null) * 10, sprite.getHeight(null) * 10, Image.SCALE_DEFAULT);
                g.drawImage(scaledSprite, WINDOW_SIZE / 2 - scaledSprite.getWidth(null) / 2, WINDOW_SIZE / 2 - scaledSprite.getHeight(null) / 2 + 100, null);
            }

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
            
            sendMessage(mainChannel,
                      "Variants:\n"
                    + "1. Attack\n"
                    + "2. Use item\n"
                    + "3. Escape"
            );
        } else if(menu == 2) {
            Player player = game.findPlayer((Hero)fighter1);
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
}
