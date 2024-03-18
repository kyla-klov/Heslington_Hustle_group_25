package com.main.utils;

import com.badlogic.gdx.Screen;
import com.main.Main;
import com.main.screens.MainControlScreen;
import com.main.screens.MainGameScreen;
import com.main.screens.MainMenuScreen;
import com.main.screens.MainSettingsScreen;

import java.util.Map;
import java.util.HashMap;

/**
 * The ScreenManager class manages the game screens, including creation, switching,
 * and memory management of screens.
 */
public class ScreenManager {
    private Main game;
    private Map<ScreenType, Screen> screensInMemory;
    private Screen curScreen;
    private ScreenType curScreenType;

    /**
     * Initializes the ScreenManager with a reference to the main game class.
     *
     * @param game The main game class instance.
     */
    public ScreenManager(Main game){
        this.game = game;
        this.screensInMemory = new HashMap<>();
    }

    /**
     * Keeps a screen in memory for quick access without having to recreate it.
     *
     * @param screenType The type of the screen to keep in memory.
     */
    public void keepInMemory(ScreenType screenType){
        if (screenType.equals(curScreenType) && curScreen != null){
            screensInMemory.put(screenType, curScreen);
        }
        else{
            screensInMemory.put(screenType, createScreen(screenType));
        }
    }

    /**
     * Sets the current screen of the game. If the screen is stored in memory, it uses it; otherwise, it creates a new screen.
     *
     * @param screenType The type of the screen to display.
     */
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

    /**
     * Creates a screen based on the given screen type. This method defines how each screen type is instantiated.
     *
     * @param type The type of the screen to create.
     * @return The created screen, or null if the type is unknown.
     */
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
