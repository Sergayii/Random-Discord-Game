package com.honker.game.items;

import com.honker.game.main.Player;
import com.honker.main.Main;
import static com.honker.main.Main.game;
import static com.honker.main.Main.mainChannel;
import java.awt.Image;

public class Potion extends Item {
    
    public static final Potion HEALTH_POTION = new Potion(game.img.ITEM_BAG_SPRITE, "Health potion", "A potion that restores 30 health", Item.RARITY_COMMON) {
        @Override
        public int inBattle(Player player) {
            int value = super.inBattle(player);
            if(value == 1) {
                int restored = player.player.addHP(30);
                Main.sendMessage(mainChannel, player.user.mention() + " restored " + restored + " HP!");
                return 1;
            }
            return 0;
        }
        
        @Override
        public int inGame(Player player) {
            int restored = player.player.addHP(30);
            Main.sendMessage(mainChannel, player.user.mention() + " restored " + restored + " HP!");
            return 1;
        }
    };
    
    public static final Potion DEBUG_POTION = new Potion(game.img.ITEM_BAG_SPRITE, "Debug potion", "A potion used for debugging", Item.RARITY_DEV) {
        @Override
        public int inBattle(Player player) {
            int value = super.inBattle(player);
            if(value == 1) {
                int restored = player.player.addHP(50);
                Main.sendMessage(mainChannel, player.user.mention() + " restored " + restored + " HP!");
                return 1;
            }
            return 0;
        }
        
        @Override
        public int inGame(Player player) {
            int restored = player.player.addHP(50);
            Main.sendMessage(mainChannel, player.user.mention() + " restored " + restored + " HP!");
            return 1;
        }
    };
    
    public int inBattle(Player player) {
        if(player.battle == null) {
            return 0;
        }
        return 1;
    }
    
    public int inGame(Player player) {
        return 0;
    };
    
    public Potion(Image sprite, String name, String description, int rarity) {
        super(sprite, name, description, false, true, rarity);
        
        Item.addToItemsList(this);
    }
}
