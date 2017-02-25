package com.honker.game.entities.blocks;

import com.honker.game.entities.Entity;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import java.awt.Image;

public abstract class StaticEntity extends Entity{

    public StaticEntity(int x, int y, Image sprite, Location location, Map map) {
        super(x, y, sprite, location, map, Entity.INFINITE_HP);
    }

    @Override
    public void move() {}

    @Override
    public void collide(int xvel, int yvel) {} 
}
