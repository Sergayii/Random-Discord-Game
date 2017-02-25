package com.honker.game.entities.misc;

import com.honker.game.entities.blocks.StaticEntity;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import static com.honker.main.Main.game;

public class Exit extends StaticEntity {

    public Enter enter;
    
    public Exit(int x, int y, Location location, Map map, Enter enter) {
        super(x, y, game.img.EXIT_SPRITE, location, map);
        this.enter = enter;
        map.exit = this;
    }
}
