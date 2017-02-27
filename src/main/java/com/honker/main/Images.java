package com.honker.main;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images {
    
    public Image HERO_SPRITE, STONE_BLOCK_SPRITE, STONE_FLOOR_SPRITE, EXIT_SPRITE, ENTER_SPRITE, ITEM_BAG_SPRITE, EMPTY_SPRITE,
                 ENEMY_SPRITE, TREE1, TREE2, TREE3, DIRT_SPRITE, GRASS_SPRITE, BEGINNER_SWORD_SPRITE, WOODEN_BOW_SPRITE,
                 STEEL_SWORD_SPRITE, BOSS1, STEEL_HELMET_SPRITE, STEEL_ARMOR_SPRITE, STEEL_GLOVES_SPRITE, STEEL_GREAVES_SPRITE;
    
    public Image STONE_BACKGROUND, FOREST_BACKGROUND;
    
    public Images() {
        try {
            HERO_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/hero.png"));
            STONE_BLOCK_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/stone_wall.png"));
            STONE_FLOOR_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/stone_floor.png"));
            EXIT_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/exit.png"));
            ENTER_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/enter.png"));
            ITEM_BAG_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/item_bag.png"));
            EMPTY_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/empty.png"));
            ENEMY_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/enemy1.png"));
            TREE1 = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/tree1.png"));
            TREE2 = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/tree2.png"));
            TREE3 = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/tree3.png"));
            DIRT_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/dirt.png"));
            GRASS_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/grass.png"));
            BEGINNER_SWORD_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/beginner_sword.png"));
            WOODEN_BOW_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/wooden_bow.png"));
            STEEL_SWORD_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/steel_sword.png"));
            BOSS1 = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/boss1.png"));
            STEEL_HELMET_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/steel_helmet.png"));
            STEEL_ARMOR_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/steel_armor.png"));
            STEEL_GLOVES_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/steel_gloves.png"));
            STEEL_GREAVES_SPRITE = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/steel_greaves.png"));
            
            STONE_BACKGROUND = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/backgrounds/stone_bg.png"));
            FOREST_BACKGROUND = ImageIO.read(getClass().getResourceAsStream("/com/honker/game/images/backgrounds/forest_bg.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
