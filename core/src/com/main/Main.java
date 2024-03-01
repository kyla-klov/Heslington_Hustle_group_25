package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.screens.MainGameScreen;

import static com.badlogic.gdx.Gdx.graphics;

public class Main extends Game {
	/* this is for movement speed so that it can use delta time to keep
	* the frame movement constant so matter the frames that are set
	*/
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
