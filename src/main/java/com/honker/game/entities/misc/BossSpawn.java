package com.honker.game.entities.misc;

import com.honker.game.entities.blocks.StaticEntity;
import com.honker.game.entities.living.Boss;
import com.honker.game.entities.living.NPCLayout;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import static com.honker.main.Main.game;

public class BossSpawn extends StaticEntity {
    
    public NPCLayout bossLayout;
    
    public void spawn() {
        new Boss(bossLayout, x, y, location, map);
    }
    
    public BossSpawn(int x, int y, Location location, Map map, NPCLayout bossLayout) {
        super(x, y, game.img.EMPTY_SPRITE, location, map);
        this.bossLayout = bossLayout;
        map.bossSpawners.add(this);
    }
}
