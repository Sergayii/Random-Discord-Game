package com.honker.game.entities.misc;

import com.honker.game.entities.blocks.StaticEntity;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import static com.honker.main.Main.game;
import java.awt.Graphics;

public class PlayerSpawn extends StaticEntity {
    
    public PlayerSpawn(int x, int y, Location location, Map map) {
        super(x, y, game.img.EMPTY_SPRITE, location, map);
        map.spawners.add(this);
    }
    
    @Override
    public void draw(Graphics g) {}
    
    @Override
    public void draw(Graphics g, int x, int y){}
}
