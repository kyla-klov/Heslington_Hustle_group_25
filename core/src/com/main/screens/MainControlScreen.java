package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.main.Main;
import com.main.utils.ScreenType;

import static com.badlogic.gdx.Gdx.input;

public class MainControlScreen implements Screen, InputProcessor {
    Main game;
    private final Texture backButton, controlLabel, wLabel, aLabel, sLabel, dLabel, arrowUpLabel, arrowLeftLabel, arrowDownLabel, arrowRightLabel;
    private final float backButtonX, backButtonY, backButtonWidth = 200, backButtonHeight = 100;
    // X coordinates for each button and label
    private final float controlLabelX, wLabelX, aLabelX, sLabelX, dLabelX, arrowUpLabelX, arrowLeftLabelX, arrowDownLabelX, arrowRightLabelX;
    // Y coordinates for each button and label
    private final float controlLabelY, wLabelY, aLabelY, sLabelY, dLabelY, arrowUpLabelY, arrowLeftLabelY, arrowDownLabelY, arrowRightLabelY;
    // Button and label dimensions
    private final float controlLabelWidth = 500, wLabelWidth = 100, aLabelWidth = 100, sLabelWidth = 100, dLabelWidth = 100, arrowUpWidth = 100,
            arrowLeftLabelWidth = 100, arrowDownLabelWidth = 100, arrowRightLabelWidth = 100;
    private final float controlLabelHeight = 130, wLabelHeight = 100, aLabelHeight = 100, sLabelHeight = 100, dLabelHeight = 100, arrowUpHeight = 100,
            arrowLeftLabelHeight = 100, arrowDownLabelHeight = 100, arrowRightLabelHeight = 100;

    public MainControlScreen(Main game) {
        this.game = game;

        Gdx.input.setInputProcessor(this);
        backButton = new Texture("assets/settings_gui/back_button.png");
        controlLabel = new Texture("assets/controls_gui/controls_label.png");
        wLabel = new Texture("assets/controls_gui/w_button.png");
        aLabel = new Texture("assets/controls_gui/a_button.png");
        sLabel = new Texture("assets/controls_gui/s_button.png");
        dLabel = new Texture("assets/controls_gui/d_button.png");
        arrowUpLabel = new Texture("assets/controls_gui/arrow_up_button.png");
        arrowLeftLabel = new Texture("assets/controls_gui/arrow_left_button.png");
        arrowDownLabel = new Texture("assets/controls_gui/arrow_down_button.png");
        arrowRightLabel = new Texture("assets/controls_gui/arrow_right_button.png");


        backButtonX = (game.screenWidth - backButtonWidth) /2;
        backButtonY = (float) game.screenHeight / 6 - 100;
        controlLabelX = (game.screenWidth - controlLabelWidth) / 2;
        controlLabelY = game.screenHeight - (controlLabelHeight * 2);
        wLabelX = (game.screenWidth - wLabelWidth) / 2 - 100;
        wLabelY = game.screenHeight - wLabelHeight - 285;
        aLabelX = (game.screenWidth - aLabelWidth) / 2 - 100;
        aLabelY = game.screenHeight - aLabelHeight - 435;
        sLabelX = (game.screenWidth - sLabelWidth) / 2 - 100;
        sLabelY = game.screenHeight - sLabelHeight - 585;
        dLabelX = (game.screenWidth - dLabelWidth) / 2 - 100;
        dLabelY = game.screenHeight - dLabelHeight - 735;
        arrowUpLabelX = (game.screenWidth - arrowUpWidth) / 2 + 100;
        arrowUpLabelY = game.screenHeight - arrowUpHeight - 285;
        arrowLeftLabelX = (game.screenWidth - arrowLeftLabelWidth) / 2 + 100;
        arrowLeftLabelY = game.screenHeight - arrowLeftLabelHeight - 435;
        arrowDownLabelX = (game.screenWidth - arrowDownLabelWidth) / 2 + 100;
        arrowDownLabelY = game.screenHeight - arrowDownLabelHeight - 585;
        arrowRightLabelX = (game.screenWidth - arrowRightLabelWidth) / 2 + 100;
        arrowRightLabelY = game.screenHeight - arrowRightLabelHeight - 735;

        /*
        stage = new Stage();
        Label.LabelStyle labelStyle = new Label.LabelStyle(game.skin.getFont("default-font"), null);
        // Label for displaying text
        Label testLabel = new Label("test", labelStyle);
        testLabel.setPosition(game.screenWidth / 2f - testLabel.getWidth() / 2, game.screenHeight / 2f - testLabel.getHeight() / 2);
        stage.addActor(testLabel);
         */
    }

    private void calculateDimensions(){

    }

    @Override
    public void show() {
        //Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        game.batch.draw(controlLabel, controlLabelX, controlLabelY, controlLabelWidth, controlLabelHeight);
        game.batch.draw(wLabel, wLabelX, wLabelY, wLabelWidth, wLabelHeight);
        game.batch.draw(aLabel, aLabelX, aLabelY, aLabelWidth, aLabelHeight);
        game.batch.draw(sLabel, sLabelX, sLabelY, sLabelWidth, sLabelHeight);
        game.batch.draw(dLabel, dLabelX, dLabelY, dLabelWidth, dLabelHeight);
        game.batch.draw(arrowUpLabel, arrowUpLabelX, arrowUpLabelY, arrowUpWidth, arrowUpHeight);
        game.batch.draw(arrowLeftLabel, arrowLeftLabelX, arrowLeftLabelY, arrowLeftLabelWidth, arrowLeftLabelHeight);
        game.batch.draw(arrowDownLabel, arrowDownLabelX, arrowDownLabelY, arrowDownLabelWidth, arrowDownLabelHeight);
        game.batch.draw(arrowRightLabel, arrowRightLabelX, arrowRightLabelY, arrowRightLabelWidth, arrowRightLabelHeight);
        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        //stage.draw();
        game.batch.end();
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float worldX = screenX * game.defWidth / (float) game.screenWidth;
        float worldY = (game.screenHeight - screenY) * game.defHeight / (float) game.screenHeight;

        if (worldX >= backButtonX && worldX <= backButtonX + backButtonWidth &&
                worldY >= backButtonY && worldY <= backButtonY + backButtonHeight) {
            game.screenManager.setScreen(ScreenType.MAIN_MENU);
            game.gameData.buttonClickedSoundActivate();
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
        backButton.dispose();
        controlLabel.dispose();
        wLabel.dispose();
        aLabel.dispose();
        sLabel.dispose();
        dLabel.dispose();
        arrowUpLabel.dispose();
        arrowLeftLabel.dispose();
        arrowDownLabel.dispose();
        arrowRightLabel.dispose();
    }

}
