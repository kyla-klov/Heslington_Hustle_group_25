package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.main.Main;

public class MainMenuScreen implements Screen {

    Main game;

    Texture playButton;
    Texture controlsButton;
    Texture settingsButton;
    Texture exitButton;
    //Texture menuBackground;

    public MainMenuScreen (Main game){
        this.game = game;
        playButton = new Texture("menu_buttons/play_button.png");
        controlsButton = new Texture("menu_buttons/controls_button.png");
        settingsButton = new Texture("menu_buttons/settings_button.png");
        exitButton = new Texture("menu_buttons/exit_button.png");
        //menuBackground = new Texture(Gdx.files.internal("campus_background.png"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();

        //game.batch.draw(menuBackground, 0, 0, game.screenWidth, game.screenHeight);

        int x = (game.screenWidth - playButton.getWidth()) / 2; //this is to make sure the buttons are centered
        float playButtonY = (game.screenHeight) - (float) playButton.getWidth() /2;
        float controlsButtonY = (game.screenHeight) - controlsButton.getWidth();
        double settingsButtonY = (game.screenHeight) - settingsButton.getWidth()*1.5;
        float exitButtonY = (game.screenHeight) - (exitButton.getWidth()*2);

        game.batch.draw(playButton, x, playButtonY);
        game.batch.draw(controlsButton, x, controlsButtonY);
        game.batch.draw(settingsButton, x, (float) settingsButtonY);
        game.batch.draw(exitButton, x, exitButtonY);

        if (Gdx.input.justTouched()) {
            int touchX = Gdx.input.getX();
            int touchY = game.screenHeight - Gdx.input.getY();

            if (touchX >= x && touchX <= x + playButton.getWidth() &&
                    touchY >= playButtonY && touchY <= playButtonY + playButton.getHeight()) {
                game.setScreen(new MainGameScreen(game));
            }
            else if (touchX >= x && touchX <= x + exitButton.getWidth() &&
                    touchY >= exitButtonY && touchY <= exitButtonY + exitButton.getHeight()) {
                Gdx.app.exit();
            }
        }

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
