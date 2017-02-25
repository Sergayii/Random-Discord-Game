package com.honker.game.entities.blocks;

import com.honker.game.map.Location;
import com.honker.game.map.Map;
import java.awt.Image;

public class Block extends StaticEntity {

    public Block(int x, int y, Image image, Location location, Map map) {
        super(x, y, image, location, map);
        map.entities.add(this);
    }
}
