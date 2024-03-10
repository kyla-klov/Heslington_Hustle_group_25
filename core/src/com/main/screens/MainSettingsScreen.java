package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;


import static com.badlogic.gdx.Gdx.input;

public class MainSettingsScreen implements Screen, InputProcessor {

    Main game;
    private final Texture backButton;
    private final float backButtonX;
    private final float backButtonY;
    private final float backButtonWidth = 200;
    private final float backButtonHeight = 100;

    private final Texture settingsLabel;

    private final float settingsLabelX;
    private final float settingsLabelY;

    private final float settingsLabelWidth = 500;
    private final float settingsLabelHeight = 130;

    private final Texture musicUpButton;

    private final float musicUpButtonX;
    private final float musicUpButtonY;

    private final float musicUpButtonWidth = 75;

    private final float musicUpButtonHeight = 75;


    private final Texture musicDownButton;

    private final float musicDownButtonX;
    private final float musicDownButtonY;

    private final float musicDownButtonWidth = 75;
    private final float musicDownButtonHeight = 75;

    private final Texture musicLabel;

    private final float musicLabelX;
    private final float musicLabelY;

    private final float musicLabelWidth = 200;
    private final float musicLabelHeight = 50;

    private final Texture musicBar;

    private final float musicBarX;
    private final float musicBarY;
    private final float musicBarWidth = 250;
    private final float musicBarHeight = 50;

    private final Texture soundUpButton;

    private final float soundUpButtonX;
    private final float soundUpButtonY;

    private final float soundUpButtonWidth = 75;

    private final float soundUpButtonHeight = 75;


    private final Texture soundLabel;

    private final float soundLabelX;
    private final float soundLabelY;

    private final float soundLabelWidth = 200;

    private final float soundLabelHeight = 50;

    /*
    private final Texture soundDownButtonTexture;

    private final float soundDownButtonLabelX;
    private final float soundDownButtonY;
    private final Texture pickBoyButtonTexture;

    private final float pickBoyButtonX;
    private final float pickBoyButtonY;
    private final Texture pickGirlButtonTexture;

    private final float pickGirlButtonX;
    private final float pickGirlButtonY;
    */


    public MainSettingsScreen(Main game) {
        this.game = game;
        /*
        group them maybe but up to you.
         */
        backButton = new Texture("assets/settings_gui/back_button.png");
        settingsLabel = new Texture("assets/settings_gui/settings_label.png");
        musicUpButton = new Texture("assets/settings_gui/arrow_right_button.png");
        musicDownButton = new Texture("assets/settings_gui/arrow_left_button.png");
        musicLabel = new Texture("assets/settings_gui/music_label.png");
        musicBar = new Texture("assets/settings_gui/bar_75.png");
        soundUpButton = new Texture("assets/settings_gui/arrow_right_button.png");
        soundLabel = new Texture("assets/settings_gui/sound_label.png");

        backButtonX = (game.screenWidth - backButtonWidth) /2;
        backButtonY = (float) game.screenHeight / 6;
        settingsLabelX = (game.screenWidth - settingsLabelWidth) / 2;
        settingsLabelY =  game.screenHeight - (settingsLabelHeight * 2);
        musicUpButtonX = (game.screenWidth - musicUpButtonWidth) / 2 + 200;
        musicUpButtonY = game.screenHeight - musicUpButtonHeight - 350;
        musicDownButtonX = (game.screenWidth - musicUpButtonWidth) / 2 - 200;
        musicDownButtonY = game.screenHeight - musicUpButtonHeight - 350;
        musicLabelX = (game.screenWidth - musicLabelWidth) / 2;
        musicLabelY = game.screenHeight - musicLabelHeight - 290;
        musicBarX = (game.screenWidth - musicBarWidth) / 2;
        musicBarY = game.screenHeight - musicBarHeight - 375;
        soundUpButtonX = (game.screenWidth - soundUpButtonWidth) / 2 + 200;
        soundUpButtonY = game.screenHeight - soundUpButtonHeight - 500;
        soundLabelX = (game.screenWidth - soundLabelWidth) / 2 + 175;
        soundLabelY = game.screenHeight - soundLabelHeight - 350;

        /*
        musicDownButtonTexture = new Texture("assets/settings_gui/arrow_left_button.png");
        backButtonX = 10;
        backButtonY = game.screenHeight - backButtonHeight - 10;

        soundUpButtonTexture = new Texture("assets/settings_gui/arrow_right_button.png");
        backButtonX = 10;
        backButtonY = game.screenHeight - backButtonHeight - 10;

        soundDownButtonTexture = new Texture("assets/settings_gui/arrow_left_button.png");
        backButtonX = 10;
        backButtonY = game.screenHeight - backButtonHeight - 10;

        pickBoyButtonTexture = new Texture("assets/settings_gui/boy_button.png");
        backButtonX = 10;
        backButtonY = game.screenHeight - backButtonHeight - 10;

        pickGirlButtonTexture = new Texture("assets/settings_gui/girl_button.png");
        backButtonX = 10;
        backButtonY = game.screenHeight - backButtonHeight - 10;

        musicLabelTexture = new Texture("assets/settings_gui/sound_label.png");
        backButtonX = 10;
        backButtonY = game.screenHeight - backButtonHeight - 10;

        soundLabelTexture = new Texture("assets/settings_gui/sound_label.png");
        backButtonX = 10;
        backButtonY = game.screenHeight - backButtonHeight - 10;
        */

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 1, 1);
        game.batch.begin();
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        game.batch.draw(settingsLabel, settingsLabelX, settingsLabelY, settingsLabelWidth, settingsLabelHeight);
        game.batch.draw(musicUpButton, musicUpButtonX, musicUpButtonY, musicUpButtonWidth, musicUpButtonHeight);
        game.batch.draw(musicDownButton, musicDownButtonX, musicDownButtonY, musicDownButtonWidth, musicDownButtonHeight);
        game.batch.draw(musicLabel, musicLabelX, musicLabelY, musicLabelWidth, musicLabelHeight);
        game.batch.draw(musicBar, musicBarX, musicBarY, musicBarWidth, musicBarHeight);
        game.batch.draw(soundUpButton, soundUpButtonX, soundUpButtonY, soundUpButtonWidth, soundUpButtonHeight);
        game.batch.draw(soundLabel, soundLabelX, soundLabelY, soundLabelWidth, soundLabelHeight);
        game.batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float worldX = screenX * game.screenWidth / (float) Gdx.graphics.getWidth();
        float worldY = game.screenHeight - screenY * game.screenHeight / (float) Gdx.graphics.getHeight();

        if (worldX >= backButtonX && worldX <= backButtonX + backButtonWidth &&
                worldY >= backButtonY && worldY <= backButtonY + backButtonHeight) {
            game.setScreen(new MainMenuScreen(game));
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
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
        backButton.dispose();
    }
}
