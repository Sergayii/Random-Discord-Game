package com.honker.main;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images {
    
    public Image HERO_SPRITE, STONE_BLOCK_SPRITE, STONE_FLOOR_SPRITE, EXIT_SPRITE, ENTER_SPRITE, ITEM_BAG_SPRITE, EMPTY_SPRITE,
                 ENEMY_SPRITE, TREE1, TREE2, TREE3, DIRT_SPRITE, GRASS_SPRITE;
    
    public Image STONE_BACKGROUND;
    
    public Images() {
        try {
            HERO_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/hero.png"));
            STONE_BLOCK_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/stone_wall.png"));
            STONE_FLOOR_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/stone_floor.png"));
            EXIT_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/exit.png"));
            ENTER_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/enter.png"));
            ITEM_BAG_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/item_bag.png"));
            EMPTY_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/empty.png"));
            ENEMY_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/enemy.png"));
            TREE1 = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/tree1.png"));
            TREE2 = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/tree2.png"));
            TREE3 = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/tree3.png"));
            DIRT_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/dirt.png"));
            GRASS_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/grass.png"));
            
            STONE_BACKGROUND = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/backgrounds/stone_bg.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
