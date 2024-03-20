package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;
import com.main.utils.ScreenType;


public class MainControlScreen implements Screen, InputProcessor {
    Main game;
    BitmapFont font;
    String objective;
    private final Texture backButton, controlLabel, controls;
    // X and Y coordinates
    private float backButtonX, backButtonY, controlLabelX, controlLabelY, controlsX, controlsY, objectiveY, instructionX, instructionY;
    // Buttons dimensions
    private float backButtonWidth, backButtonHeight, controlLabelWidth, controlLabelHeight, controlsHeight, controlsWidth, instructionGap;

    public MainControlScreen(Main game) {
        this.game = game;
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));

        backButton = new Texture("settings_gui/back_button.png");
        controlLabel = new Texture("controls_gui/controls_label.png");
        controls = new Texture("controls_gui/controls.png");

        calculateDimensions();
        calculatePositions();

        objective = "Welcome to Heslington Hustle! You are a second-year Computer Science student with exams in only 7 days. Explore the map, \n" +
                "and interact with buildings to eat, study, sleep and have fun. To get a good grade, you need to balance hours of studying with \n" +
                "self-care and recreation. Good luck!";


    }

    private void calculateDimensions(){
        font.getData().setScale(1.5f * game.scaleFactorX, 1.5f * game.scaleFactorY);
        backButtonWidth = 200 * game.scaleFactorX;
        backButtonHeight = 100 * game.scaleFactorY;
        controlLabelWidth = 500 * game.scaleFactorX;
        controlLabelHeight = 130 * game.scaleFactorY;
        controlsWidth = 500/3f * game.scaleFactorX;
        controlsHeight = 500 * game.scaleFactorY;
        instructionGap = 87 * game.scaleFactorY;
    }

    private void calculatePositions(){
        backButtonX = (game.screenWidth - backButtonWidth) / 2f;
        backButtonY = game.screenHeight / 6f - 120 * game.scaleFactorY;
        controlLabelX = (game.screenWidth - controlLabelWidth) / 2f;
        controlLabelY = game.screenHeight - (controlLabelHeight * 1.2f);
        controlsX = game.screenWidth / 3.2f;
        controlsY = (game.screenHeight / 3.5f) - (controlsHeight / 5f);
        objectiveY = game.screenHeight - 160 * game.scaleFactorY;
        instructionY = game.screenHeight / 1.45f;
        instructionX = game.screenWidth / 2f - 90 * game.scaleFactorX;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        font.draw(game.batch, objective, 0, objectiveY, game.screenWidth, Align.center, false);
        float instructionY = this.instructionY;
        String[] instructions = {
                "Up - Move forward",
                "Left - Turn left",
                "Right - Turn right",
                "Down - Move backward",
                "Shift - Sprint",
                "Esc - Pause"
        };

        for (String instruction : instructions) {
            font.draw(game.batch, instruction, instructionX, instructionY);
            instructionY -= instructionGap; // Spacing between instructions
        }
        game.batch.draw(controlLabel, controlLabelX, controlLabelY, controlLabelWidth, controlLabelHeight);
        game.batch.draw(controls, controlsX, controlsY, controlsWidth, controlsHeight);
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);

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
        calculateDimensions();
        calculatePositions();
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
        controls.dispose();
        font.dispose();
    }

}
