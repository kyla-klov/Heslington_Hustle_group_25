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

import static com.badlogic.gdx.Gdx.input;

public class MainControlScreen implements Screen, InputProcessor {
    Main game;
    BitmapFont font;
    String objective;
    private final Texture backButton, controlLabel, controls;
    // X and Y coordinates
    private final float  backButtonX, backButtonY, controlLabelX, controlLabelY, controlsX, controlsY;
    // Buttons dimensions
    private final float backButtonWidth = 200, backButtonHeight = 100, controlLabelWidth = 500, controlLabelHeight = 130, controlsHeight = 594, controlsWidth = 198;

    public MainControlScreen(Main game) {
        this.game = game;
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        font.getData().setScale(1.5f);

        Gdx.input.setInputProcessor(this);
        backButton = new Texture("assets/settings_gui/back_button.png");
        controlLabel = new Texture("assets/controls_gui/controls_label.png");
        controls = new Texture("assets/controls_gui/controls.png");

        backButtonX = (game.screenWidth - backButtonWidth) /2;
        backButtonY = (float) game.screenHeight / 6 - 100;
        controlLabelX = (game.screenWidth - controlLabelWidth) / 2;
        controlLabelY = game.screenHeight - (controlLabelHeight * 2);
        controlsX = game.screenWidth / 3;
        controlsY = (game.screenHeight / 3) - (controlsHeight / 5);

        objective = "Welcome to Heslington Hustle! You are a second-year Computer Science student with exams in only 7 days. Explore the map, \n" +
                "and interact with buildings to eat, study, sleep and have fun. To get a good grade, you need to balance hours of studying with \n" +
                "self-care and recreation. Good luck!";

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void show() {
        //Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();
        font.draw(game.batch, objective, game.screenWidth / 4f, game.screenHeight - 280, game.screenWidth / 2f, Align.center, false);
        float instructionY = (((float) game.screenHeight /2) * game.scaleFactorY);
        String[] instructions = {
                "Up - Move forward",
                "Left - Turn left",
                "Right - Turn right",
                "Down - Move backward",
                "Shift - Sprint",
                "Esc - Pause"
        };

        for (String instruction : instructions) {
            font.draw(game.batch, instruction, (game.screenWidth - font.getRegion().getRegionWidth()) / 2f, instructionY);
            instructionY -= 30; // Spacing between instructions
        }
        game.batch.draw(controlLabel, controlLabelX, controlLabelY, controlLabelWidth, controlLabelHeight);
        game.batch.draw(controls, controlsX, controlsY, controlsWidth, controlsHeight);
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);;

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
        controls.dispose();
        font.dispose();
    }

}
