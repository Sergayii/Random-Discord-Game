package com.honker.game.entities.living;

import com.honker.game.map.Location;
import com.honker.game.map.Map;

public class Boss extends Enemy {
    
    @Override
    public int getExpGained() {
        return super.getExpGained() * 10;
    }
    
    public Boss(NPCLayout layout, int x, int y, Location location, Map map) {
        super(layout, x, y, location, map);
    }
}
