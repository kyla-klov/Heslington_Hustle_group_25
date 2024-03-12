package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.main.Main;

import static com.badlogic.gdx.Gdx.input;

public class MainControlScreen implements Screen, InputProcessor {

    Main game;

    private final Texture backButtonTexture;
    private final float backButtonX, backButtonY, backButtonWidth = 100, backButtonHeight = 50;
    private final Stage stage; // LibGdx container for labels with Peaberry font

    private final Texture controlLabel;

    private final float controlLabelX;
    private final float controlLabelY;
    private final float controlLabelWidth = 500;
    private final float controlLabelHeight = 130;

    private final Texture wLabel;
    private final float wLabelX;
    private final float wLabelY;
    private final float wLabelWidth = 150;
    private final float wLabelHeight = 150;


    public MainControlScreen(Main game) {
        this.game = game;
        backButtonTexture = new Texture("assets/menu_buttons/back_button.png");

        controlLabel = new Texture("assets/controls_gui/controls_label.png");
        wLabel = new Texture("assets/controls_gui/w_button.png");



        backButtonX = 10; // Adjust the position as needed
        backButtonY = game.screenHeight - backButtonHeight - 10;
        controlLabelX = (game.screenWidth - controlLabelWidth) / 2;
        controlLabelY = game.screenHeight - (controlLabelHeight * 2);
        wLabelX = (game.screenWidth - wLabelWidth) / 2 - 200;
        wLabelY = game.screenHeight - wLabelHeight - 350;



        stage = new Stage();
        Label.LabelStyle labelStyle = new Label.LabelStyle(game.skin.getFont("default-font"), null);
        // Label for displaying text
        Label testLabel = new Label("test", labelStyle);
        testLabel.setPosition(game.screenWidth / 2f - testLabel.getWidth() / 2, game.screenHeight / 2f - testLabel.getHeight() / 2);
        stage.addActor(testLabel);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();
        game.batch.draw(backButtonTexture, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        game.batch.draw(controlLabel, controlLabelX, controlLabelY, controlLabelWidth, controlLabelHeight);
        game.batch.draw(wLabel, wLabelX, wLabelY, wLabelWidth, wLabelHeight);
        game.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
    public boolean scrolled(float amountX, float amountY) {
        // Implement scrolling behavior if needed
        return false; // Return false if the event was not handled
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
        stage.dispose();
    }

}
