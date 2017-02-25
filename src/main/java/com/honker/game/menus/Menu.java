package com.honker.game.menus;

import com.honker.game.entities.living.NPC;
import static com.honker.main.Variables.WINDOW_SIZE;
import java.awt.Graphics;
import java.awt.Image;

public abstract class Menu {
    
    public int menu = 1;
    
    public Image bg;
    
    public void sendMenu(String response, NPC npc){}
    
    public void sendMenu(String response){}
    
    public void sendMenu(NPC npc) {
        sendMenu("", npc);
    }
    
    public void sendMenu() {
        sendMenu("");
    }
    
    public void choose(int choice){};
    
    public void drawBg(Graphics g) {
        g.clearRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
        g.drawImage(bg, 0, 0, null);
    }
    
    public Menu(Image bg) {
        this.bg = bg;
    }
}
