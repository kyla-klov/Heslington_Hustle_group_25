package com.main.utils;

import com.badlogic.gdx.Screen;
import com.main.Main;

import java.util.Map;
import java.util.HashMap;

public class ScreenManager {
    private Main game;
    private Map<ScreenType, Screen> screens;

    public ScreenManager(Main game){
        this.game = game;
        screens = new HashMap<>();
    }

    public void addScreen(ScreenType type, Screen screen){
        if (screen != null){
            screens.put(type, screen);
        }
    }

    public void setScreen(ScreenType type) {
        Screen screen = screens.get(type);
        if (screen != null) {
            game.setScreen(screen);
        }
    }

    public Screen getScreen(ScreenType type){
        return screens.get(type);
    }
}
