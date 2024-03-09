package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.screens.MainControlScreen;
import com.main.screens.MainMenuScreen;
import com.main.screens.MainSettingsScreen;

import static com.badlogic.gdx.Gdx.graphics;

public class Main extends Game {
	/* this is for movement speed so that it can use delta time to keep
	* the frame movement constant so matter the frames that are set
	*/
	public SpriteBatch batch;
	public int screenWidth, screenHeight;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screenWidth = graphics.getWidth();
		screenHeight = graphics.getHeight();
		this.setScreen(new MainSettingsScreen(this));
		this.setScreen(new MainControlScreen(this));
		this.setScreen(new MainMenuScreen(this));
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
