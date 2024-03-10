package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;

public class MainMenuScreen implements Screen {

    Main game;

    Texture heslingtonHustleLable;
    Texture playButton;
    Texture controlsButton;
    Texture settingsButton;
    Texture exitButton;

    int heslingtonHustleLableHeight;
    int heslingtonHustleLableWidth;
    int playButtonHeight;
    int playButtonWidth;
    int controlsButtonHeight;
    int controlsButtonWidth;
    int settingsButtonHeight;
    int settingsButtonWidth;
    int exitButtonHeight;
    int exitButtonWidth;

    //Texture menuBackground;
    int x;
    float heslingtonHustleLableX;
    float heslingtonHustleLableY;
    float playButtonY;
    float controlsButtonY;
    double settingsButtonY;
    float exitButtonY;

    public MainMenuScreen (Main game) {

        this.game = game;

        heslingtonHustleLable = new Texture("menu_gui/heslington_hustle_lable.png");
        playButton = new Texture("menu_gui/play_button.png");
        controlsButton = new Texture("menu_gui/controls_button.png");
        settingsButton = new Texture("menu_gui/settings_button.png");
        exitButton = new Texture("menu_gui/exit_button.png");
        //menuBackground = new Texture(Gdx.files.internal("campus_background.png"));

        heslingtonHustleLableHeight = heslingtonHustleLable.getHeight() * 12;
        heslingtonHustleLableWidth = heslingtonHustleLable.getWidth() * 12;
        playButtonHeight = playButton.getHeight() * 12;
        playButtonWidth = playButton.getWidth() * 12;
        controlsButtonHeight = controlsButton.getHeight() * 12;
        controlsButtonWidth = controlsButton.getWidth() * 12;
        settingsButtonHeight = settingsButton.getHeight() * 12;
        settingsButtonWidth = settingsButton.getWidth() * 12;
        exitButtonHeight = exitButton.getHeight() * 12;
        exitButtonWidth = exitButton.getWidth() * 12;

        heslingtonHustleLableX = (float) (game.screenWidth - heslingtonHustleLableWidth) / 2;
        x = (game.screenWidth - playButtonWidth) / 2; //this is to make sure the buttons are centered
        heslingtonHustleLableY = (game.screenHeight) - (float) heslingtonHustleLableHeight * 1.25f;
        playButtonY = (game.screenHeight) - (float) playButtonHeight * 2.5f;
        controlsButtonY = (game.screenHeight) - controlsButtonHeight * 3.75f;
        settingsButtonY = (game.screenHeight) - settingsButtonHeight * 5f;
        exitButtonY = (game.screenHeight) - (exitButtonHeight * 6.25f);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();

        //game.batch.draw(menuBackground, 0, 0, game.screenWidth, game.screenHeight);

        game.batch.draw(heslingtonHustleLable, heslingtonHustleLableX, heslingtonHustleLableY, heslingtonHustleLableWidth, heslingtonHustleLableHeight);
        game.batch.draw(playButton, x, playButtonY, playButtonWidth, playButtonHeight);
        game.batch.draw(controlsButton, x, controlsButtonY, controlsButtonWidth, controlsButtonHeight);
        game.batch.draw(settingsButton, x, (float) settingsButtonY, settingsButtonWidth, settingsButtonHeight);
        game.batch.draw(exitButton, x, exitButtonY, exitButtonWidth, exitButtonHeight);

        if (Gdx.input.justTouched()) {
            int touchX = Gdx.input.getX();
            int touchY = game.screenHeight - Gdx.input.getY();

            if (touchX >= x && touchX <= x + playButtonWidth &&
                    touchY >= playButtonY && touchY <= playButtonY + playButtonHeight) {
                game.setScreen(new MainGameScreen(game));
            }
            else if (touchX >= x && touchX <= x + controlsButtonWidth &&
                    touchY >= controlsButtonY && touchY <= controlsButtonY + controlsButtonHeight) {
                game.setScreen(new MainControlScreen(game));
            }
            else if (touchX >= x && touchX <= x + settingsButtonWidth &&
                    touchY >= settingsButtonY && touchY <= settingsButtonY + settingsButtonHeight) {
                game.setScreen(new MainSettingsScreen(game));
            }
            else if (touchX >= x && touchX <= x + exitButtonWidth &&
                    touchY >= exitButtonY && touchY <= exitButtonY + exitButtonHeight) {
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
        playButton.dispose();
        controlsButton.dispose();
        settingsButton.dispose();
        exitButton.dispose();
    }
}
