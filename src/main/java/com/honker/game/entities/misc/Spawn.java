package com.honker.game.entities.misc;

import com.honker.game.entities.blocks.StaticEntity;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import java.awt.Graphics;

public class Spawn extends StaticEntity {
    
    public Spawn(int x, int y, Location location, Map map) {
        super(x, y, null, location, map);
        map.spawners.add(this);
    }
    
    @Override
    public void draw(Graphics g) {}
    
    @Override
    public void draw(Graphics g, int x, int y){}
}
