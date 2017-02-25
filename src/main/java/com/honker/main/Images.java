package com.honker.main;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images {
    
    public Image HERO_SPRITE, BLOCK_SPRITE, FLOOR_SPRITE, EXIT_SPRITE, ENTER_SPRITE, ITEM_BAG_SPRITE, EMPTY_SPRITE,
                 ENEMY_SPRITE;
    
    public Image STONE_BACKGROUND;
    
    public Images() {
        try {
            HERO_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/hero.png"));
            BLOCK_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/wall.png"));
            FLOOR_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/floor.png"));
            EXIT_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/exit.png"));
            ENTER_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/enter.png"));
            ITEM_BAG_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/item_bag.png"));
            EMPTY_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/empty.png"));
            ENEMY_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/enemy.png"));
            
            STONE_BACKGROUND = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/backgrounds/stone_bg.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
