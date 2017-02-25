package com.honker.game.entities.misc;

import com.honker.game.entities.blocks.StaticEntity;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import static com.honker.main.Main.game;

public class Enter extends StaticEntity {
    
    public Enter(int x, int y, Location location, Map map) {
        super(x, y, game.img.ENTER_SPRITE, location, map);
        map.enter = this;
    }
}
