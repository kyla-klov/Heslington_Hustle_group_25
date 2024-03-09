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

    private final Texture backButtonTexture;
    private final float backButtonX;
    private final float backButtonY;
    private final float backButtonWidth = 100;
    private final float backButtonHeight = 50;

    public MainSettingsScreen(Main game) {
        this.game = game;
        backButtonTexture = new Texture("assets/menu_buttons/back_button.png");
        backButtonX = 10; // Adjust the position as needed
        backButtonY = game.screenHeight - backButtonHeight - 10;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 1, 1);
        game.batch.begin();
        game.batch.draw(backButtonTexture, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
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
        backButtonTexture.dispose();
    }
}
