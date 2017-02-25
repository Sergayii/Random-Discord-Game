package com.honker.game.entities.blocks;

import com.honker.game.items.Item;
import com.honker.game.map.Location;
import com.honker.game.map.Map;
import static com.honker.main.Main.game;
import java.util.ArrayList;

public class DroppedItem extends StaticEntity {
    
    public ArrayList<Item> items;
    
    public DroppedItem(int x, int y, ArrayList<Item> items, Location location, Map map) {
        super(x, y, game.img.ITEM_BAG_SPRITE, location, map);
        
        if(!items.isEmpty()) {
            this.items = items;
            map.entities.add(this);
        }
    }
}
