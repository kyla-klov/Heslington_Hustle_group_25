package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.screens.MainControlScreen;
import com.main.screens.MainGameScreen;
import com.main.screens.MainMenuScreen;
import com.main.screens.MainSettingsScreen;

import static com.badlogic.gdx.Gdx.graphics;

import com.main.utils.GameData;
import com.main.utils.ScreenManager;
import com.main.utils.ScreenType;

public class Main extends Game {
	/* this is for movement speed so that it can use delta time to keep
	* the frame movement constant so matter the frames that are set
	*/
	public SpriteBatch batch;
	public GameData gameData;
	public ScreenManager screenManager;
	public int screenWidth, screenHeight;
	public int defWidth, defHeight;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screenWidth = graphics.getWidth();
		screenHeight = graphics.getHeight();
		defWidth = graphics.getWidth();
		defHeight = graphics.getHeight();
		screenManager = new ScreenManager(this);
		screenManager.addScreen(ScreenType.MAIN_MENU, new MainMenuScreen(this));
		screenManager.addScreen(ScreenType.GAME_SCREEN, new MainGameScreen(this));
		screenManager.addScreen(ScreenType.SETTINGS, new MainSettingsScreen(this));
		screenManager.addScreen(ScreenType.CONTROLS, new MainControlScreen(this));
		screenManager.setScreen(ScreenType.MAIN_MENU);
		gameData = new GameData();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		screenWidth = width;
		screenHeight = height;
	}
	
	@Override
	public void dispose () {
	}
}
