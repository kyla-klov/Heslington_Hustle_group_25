package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;
import com.badlogic.gdx.InputProcessor;
import com.main.utils.ScreenType;

public class MainMenuScreen implements Screen, InputProcessor {

    Main game;

    Texture heslingtonHustleLable, playButton, controlsButton, settingsButton, exitButton;

    int heslingtonHustleLableHeight, playButtonHeight, controlsButtonHeight, settingsButtonHeight, exitButtonHeight;
    int heslingtonHustleLableWidth, playButtonWidth, controlsButtonWidth, settingsButtonWidth, exitButtonWidth;

    int x;
    float heslingtonHustleLableX;
    float heslingtonHustleLableY, playButtonY, controlsButtonY, settingsButtonY, exitButtonY;


    public MainMenuScreen (Main game) {
        this.game = game;

        heslingtonHustleLable = new Texture("menu_gui/heslington_hustle_lable.png");
        playButton = new Texture("menu_gui/play_button.png");
        controlsButton = new Texture("menu_gui/controls_button.png");
        settingsButton = new Texture("menu_gui/settings_button.png");
        exitButton = new Texture("menu_gui/exit_button.png");

        heslingtonHustleLableHeight = heslingtonHustleLable.getHeight() * 11;
        heslingtonHustleLableWidth = heslingtonHustleLable.getWidth() * 11;
        playButtonHeight = playButton.getHeight() * 11;
        playButtonWidth = playButton.getWidth() * 11;
        controlsButtonHeight = controlsButton.getHeight() * 11;
        controlsButtonWidth = controlsButton.getWidth() * 11;
        settingsButtonHeight = settingsButton.getHeight() * 11;
        settingsButtonWidth = settingsButton.getWidth() * 11;
        exitButtonHeight = exitButton.getHeight() * 11;
        exitButtonWidth = exitButton.getWidth() * 11;

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
        game.batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();

        game.batch.draw(heslingtonHustleLable, heslingtonHustleLableX, heslingtonHustleLableY, heslingtonHustleLableWidth, heslingtonHustleLableHeight);
        game.batch.draw(playButton, x, playButtonY, playButtonWidth, playButtonHeight);
        game.batch.draw(controlsButton, x, controlsButtonY, controlsButtonWidth, controlsButtonHeight);
        game.batch.draw(settingsButton, x, (float) settingsButtonY, settingsButtonWidth, settingsButtonHeight);
        game.batch.draw(exitButton, x, exitButtonY, exitButtonWidth, exitButtonHeight);
        game.batch.end();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float touchX = screenX * game.defWidth / (float) game.screenWidth;
        float touchY = (game.screenHeight - screenY) * game.defHeight / (float) game.screenHeight;

        if (touchX >= x && touchX <= x + playButtonWidth &&
                touchY >= playButtonY && touchY <= playButtonY + playButtonHeight) {
            game.screenManager.setScreen(ScreenType.GAME_SCREEN);
        }
        else if (touchX >= x && touchX <= x + controlsButtonWidth &&
                touchY >= controlsButtonY && touchY <= controlsButtonY + controlsButtonHeight) {
            game.screenManager.setScreen(ScreenType.CONTROLS);
        }
        else if (touchX >= x && touchX <= x + settingsButtonWidth &&
                touchY >= settingsButtonY && touchY <= settingsButtonY + settingsButtonHeight) {
            game.screenManager.setScreen(ScreenType.SETTINGS);
        }
        else if (touchX >= x && touchX <= x + exitButtonWidth &&
                touchY >= exitButtonY && touchY <= exitButtonY + exitButtonHeight) {
            Gdx.app.exit();
        }

        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
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
        heslingtonHustleLable.dispose();
    }
}
