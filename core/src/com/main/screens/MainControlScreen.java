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

/**
 * The MainControlScreen class provides a visual representation of control instructions
 * for the game, alongside a back button to navigate back to the main menu.
 * It implements both the Screen and InputProcessor interfaces from libGDX,
 * handling rendering and input events within the control screen context.
 */
public class MainControlScreen implements Screen, InputProcessor {
    Main game;
    BitmapFont font;
    BitmapFont font2;
    String objective;
    private final Texture backButton, controlLabel, controls;
    // X and Y coordinates
    private final float  backButtonX, backButtonY, controlLabelX, controlLabelY, controlsX, controlsY;
    // Buttons dimensions
    private final float backButtonWidth = 200, backButtonHeight = 100, controlLabelWidth = 500, controlLabelHeight = 130, controlsHeight = 594, controlsWidth = 198;

    /**
     * Constructor for MainControlScreen.
     * Initializes the screen with game controls instructions, sets up textures for display elements,
     * and configures input processing.
     *
     * @param game The main game object that this screen is a part of.
     */
    public MainControlScreen(Main game) {
        this.game = game;
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        font.getData().setScale(1.5f);
        font2.getData().setScale(2f);

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

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    /**
     * The main render method for the screen. Called every frame and responsible for
     * drawing the screen's contents.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();
        font.draw(game.batch, objective, game.screenWidth / 4f, game.screenHeight - 280, game.screenWidth / 2f, Align.center, false);
        float instructionX = (game.screenHeight / 1.5f) * game.scaleFactorX;
        String instructionUp = "Up - Move forward";
        String instructionLeft = "Left - Turn left";
        String instructionRight = "Right - Turn right";
        String instructionDown = "Down - Move backward";
        String instructionShift = "Shift - Sprint";
        String instructionEsc = "Esc - Pause";

        font2.draw(game.batch, instructionUp, instructionX, game.screenHeight /2f * game.scaleFactorY);
        font2.draw(game.batch, instructionLeft, instructionX, game.screenHeight /2.23f * game.scaleFactorY);
        font2.draw(game.batch, instructionRight, instructionX, game.screenHeight /2.55f * game.scaleFactorY);
        font2.draw(game.batch, instructionDown, instructionX, game.screenHeight /3f * game.scaleFactorY);
        font2.draw(game.batch, instructionShift, instructionX, game.screenHeight /3.65f * game.scaleFactorY);
        font2.draw(game.batch, instructionEsc, instructionX, game.screenHeight /4.45f * game.scaleFactorY);

        game.batch.draw(controlLabel, controlLabelX, controlLabelY, controlLabelWidth, controlLabelHeight);
        game.batch.draw(controls, controlsX, controlsY, controlsWidth, controlsHeight);
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);;

        game.batch.end();
    }

    /**
     * Handles touch down input events. Specifically, checks if the back button is pressed
     * and navigates back to the main menu screen.
     *
     * @param screenX The x-coordinate of the touch, in screen coordinates.
     * @param screenY The y-coordinate of the touch, in screen coordinates.
     * @param pointer The pointer for the event.
     * @param button The button pressed.
     * @return true if the event was handled, false otherwise.
     */
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float touchY = game.screenHeight - screenY;

        if (screenX >= backButtonX && screenX <= backButtonX + backButtonWidth &&
                touchY >= backButtonY && touchY <= backButtonY + backButtonHeight) {
            game.screenManager.setScreen(ScreenType.MAIN_MENU);
            game.gameData.buttonClickedSoundActivate();
            return true;
        }
        return false;
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
        controlLabel.dispose();
        controls.dispose();
        font.dispose();
    }

}
