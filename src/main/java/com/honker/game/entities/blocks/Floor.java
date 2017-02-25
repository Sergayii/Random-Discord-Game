package com.honker.game.entities.blocks;

import com.honker.game.map.Location;
import com.honker.game.map.Map;
import java.awt.Image;

public class Floor extends StaticEntity {
    
    public Floor(int x, int y, Image sprite, Location location, Map map) {
        super(x, y, sprite, location, map);
        map.floors.add(this);
    }
}
