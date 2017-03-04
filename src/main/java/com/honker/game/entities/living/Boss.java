package com.honker.game.entities.living;

import com.honker.game.map.Location;
import com.honker.game.map.Map;

public class Boss extends Enemy {
    
    @Override
    public int getExpGained() {
        int expScale = 10;
        return super.getExpGained() * expScale;
    }
    
    public Boss(NPCLayout layout, int x, int y, Location location, Map map) {
        super(layout, x, y, location, map);
    }
}
