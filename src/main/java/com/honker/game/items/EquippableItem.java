package com.honker.game.items;

import java.awt.Image;
import java.util.Random;

public abstract class EquippableItem extends Item {
    
    public static final String QUALITY_AWFUL = "Awful";
    public static final String QUALITY_SHODDY = "Shoddy";
    public static final String QUALITY_POOR = "Poor";
    public static final String QUALITY_NORMAL = "Normal";
    public static final String QUALITY_GOOD = "Good";
    public static final String QUALITY_SUPERIOR = "Superior";
    public static final String QUALITY_PARAGON = "Paragon";
    public static final String QUALITY_GODLY = "Godly";
    public static final String QUALITY_LEGENDARY = "Legendary";
    
    public String quality = QUALITY_NORMAL;
    public int STR, AG, DEF, SPD;
    
    public void removeRandomStat() {
        if(STR <= 0 && AG <= 0 && DEF <= 0 && SPD <= 0) {
            return;
        }
        
        while(true) {
            int stat = new Random().nextInt(4) + 1;
            if(stat == 1) {
                if(STR <= 0) {
                    continue;
                }
                STR--;
                break;
            } else if(stat == 2) {
                if(AG <= 0) {
                    continue;
                }
                AG--;
                break;
            } else if(stat == 3) {
                if(DEF <= 0) {
                    continue;
                }
                DEF--;
                break;
            } else if(stat == 4) {
                if(SPD <= 0) {
                    continue;
                }
                SPD--;
                break;
            }
        }
    }
    
    public void addRandomStat() {
        int stat = new Random().nextInt(4) + 1;
        if(stat == 1) {
            STR++;
        } else if(stat == 2) {
            AG++;
        } else if(stat == 3) {
            DEF++;
        } else if(stat == 4) {
            SPD++;
        }
    }
    
    public void removeStats(int points) {
        if(points < 1) {
            return;
        }
        
        for(int a = 0; a < points; a++) {
            removeRandomStat();
        }
    }
    
    public void addStats(int points) {
        if(points < 1) {
            return;
        }
        
        for(int a = 0; a < points; a++) {
            addRandomStat();
        }
    }
    
    public EquippableItem(Image sprite, String name, String description, int STR, int AG, int DEF, int SPD, int rarity, boolean affectedByQuality, String forcedQuality) {
        super(sprite, name, description, true, false, rarity);
        
        int chance = new Random().nextInt(50) + 1;
        if(chance >= 1 && chance <= 10)
            quality = QUALITY_AWFUL;
        else if(chance >= 10 && chance <= 20)
            quality = QUALITY_SHODDY;
        else if(chance >= 20 && chance <= 30)
            quality = QUALITY_POOR;
        else if(chance >= 30 && chance <= 35)
            quality = QUALITY_NORMAL;
        else if(chance >= 35 && chance <= 40)
            quality = QUALITY_GOOD;
        else if(chance >= 40 && chance <= 44)
            quality = QUALITY_SUPERIOR;
        else if(chance >= 44 && chance <= 47)
            quality = QUALITY_PARAGON;
        else if(chance >= 47 && chance <= 49)
            quality = QUALITY_GODLY;
        else if(chance >= 49 && chance <= 50)
            quality = QUALITY_LEGENDARY;
        else
            quality = QUALITY_NORMAL;
        
        if(!affectedByQuality) {
            quality = forcedQuality;
        }
    }
}
