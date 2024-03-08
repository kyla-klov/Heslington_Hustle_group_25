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
    int x;
    float playButtonY;
    float controlsButtonY;
    double settingsButtonY;
    float exitButtonY;

    public MainMenuScreen (Main game){
        this.game = game;
        playButton = new Texture("menu_buttons/play_button.png");
        controlsButton = new Texture("menu_buttons/controls_button.png");
        settingsButton = new Texture("menu_buttons/settings_button.png");
        exitButton = new Texture("menu_buttons/exit_button.png");
        //menuBackground = new Texture(Gdx.files.internal("campus_background.png"));

        x = (game.screenWidth - playButton.getWidth()) / 2; //this is to make sure the buttons are centered
        playButtonY = (game.screenHeight) - (float) playButton.getHeight() * 1.25f;
        controlsButtonY = (game.screenHeight) - controlsButton.getHeight() * 2.5f;
        settingsButtonY = (game.screenHeight) - settingsButton.getHeight() * 3.75f;
        exitButtonY = (game.screenHeight) - (exitButton.getHeight() * 5f);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();

        //game.batch.draw(menuBackground, 0, 0, game.screenWidth, game.screenHeight);

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
            else if (touchX >= x && touchX <= x + controlsButton.getWidth() &&
                    touchY >= controlsButtonY && touchY <= controlsButtonY + controlsButton.getHeight()) {
                game.setScreen(new MainControlScreen(game));
            }
            else if (touchX >= x && touchX <= x + settingsButton.getWidth() &&
                    touchY >= settingsButtonY && touchY <= settingsButtonY + settingsButton.getHeight()) {
                //game.setScreen(new MainSettingsScreen(game));
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
