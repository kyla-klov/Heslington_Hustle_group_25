package com.main.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;

public class MainMenuScreen implements Screen {

//    private static final int exitButtonWidth = 300;
//    private static final int exitButtonHeight = 150;
//    private static final int platButtonWidth = 300;
//    private static final int playButtonHeight= 150;

    Main game;

    Texture playButton;
    Texture controlsButton;
    Texture settingsButton;
    Texture exitButton;

    public MainMenuScreen (Main game){
        this.game = game;
        playButton = new Texture("menu_buttons/play_button.png");
        controlsButton = new Texture("menu_buttons/controls_button.png");
        settingsButton = new Texture("menu_buttons/settings_button.png");
        exitButton = new Texture("menu_buttons/exit_button.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();

        

        int x = (game.screenWidth - playButton.getWidth()) / 2; //this is to make sure the buttons are centered
        // int y = (game.screenHeight - playButton.getWidth()) / 2;
        game.batch.draw(playButton, x, (game.screenHeight - (float) playButton.getWidth() / 2));

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
