package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import static com.badlogic.gdx.Gdx.graphics;
import com.main.utils.GameData;
import com.main.utils.ScreenManager;
import com.main.utils.ScreenType;
import com.main.sound.GameMusic;

public class Main extends Game {
	public SpriteBatch batch;
	public GameData gameData;
	public ScreenManager screenManager;
	public int screenWidth, screenHeight;
	public int defWidth, defHeight;
	public Skin skin;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameData = new GameData();
		screenWidth = graphics.getWidth();
		screenHeight = graphics.getHeight();
		defWidth = graphics.getWidth();
		defHeight = graphics.getHeight();
		gameData.setPlayerPosY(screenWidth /2 - screenHeight /2);

		// Fonts for writing in game
		skin = new Skin();
		BitmapFont font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
		skin.add("default-font", font, BitmapFont.class);
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = font;
		skin.add("Peaberry", labelStyle, Label.LabelStyle.class);

		screenManager = new ScreenManager(this);
		screenManager.keepInMemory(ScreenType.GAME_SCREEN);
		screenManager.setScreen(ScreenType.MAIN_MENU);
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
