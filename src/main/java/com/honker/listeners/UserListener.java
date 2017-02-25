package com.honker.listeners;

import static com.honker.main.Main.game;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.UserJoinEvent;
import sx.blah.discord.handle.impl.events.UserLeaveEvent;
import sx.blah.discord.handle.impl.events.UserUpdateEvent;

public class UserListener {
    
    @EventSubscriber
    public void onUserJoinEvent(UserJoinEvent event) {
        game.updatePlayers();
    }
    
    @EventSubscriber
    public void onUserLeaveEvent(UserLeaveEvent event) {
        game.updatePlayers();
    }
    
    @EventSubscriber
    public void onUserUpdateEvent(UserUpdateEvent event) {
        game.updatePlayers();
    }
}
