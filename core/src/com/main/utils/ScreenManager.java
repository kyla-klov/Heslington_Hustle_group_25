package com.main.utils;

import com.badlogic.gdx.Screen;
import com.main.Main;
import com.main.screens.MainControlScreen;
import com.main.screens.MainGameScreen;
import com.main.screens.MainMenuScreen;
import com.main.screens.MainSettingsScreen;

import java.util.Map;
import java.util.HashMap;

public class ScreenManager {
    private final Main game;
    private final Map<ScreenType, Screen> screensInMemory;
    private Screen curScreen;
    private ScreenType curScreenType;

    public ScreenManager(Main game){
        this.game = game;
        this.screensInMemory = new HashMap<>();
    }

    public void keepInMemory(ScreenType screenType){
        if (screenType.equals(curScreenType) && curScreen != null){
            screensInMemory.put(screenType, curScreen);
        }
        else{
            screensInMemory.put(screenType, createScreen(screenType));
        }
    }

    public void setScreen(ScreenType screenType){
        if (curScreen != null && !screensInMemory.containsKey(curScreenType)){
            curScreen.dispose();
        }
        if (screensInMemory.containsKey(screenType)){
            curScreen = screensInMemory.get(screenType);
        }
        else {
            curScreen = createScreen(screenType);
        }
        curScreenType = screenType;
        game.setScreen(curScreen);
    }

    public void resize(int width, int height){
        curScreen.resize(width, height);
    }

    private Screen createScreen(ScreenType type) {
        switch (type){
            case MAIN_MENU:
                return new MainMenuScreen(game);
            case GAME_SCREEN:
                return new MainGameScreen(game);
            case SETTINGS:
                return new MainSettingsScreen(game);
            case CONTROLS:
                return new MainControlScreen(game);
        }
        return null;
    }
}
