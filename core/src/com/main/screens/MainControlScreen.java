package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
<<<<<<< Updated upstream
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
=======
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
>>>>>>> Stashed changes
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.main.Main;
import com.main.utils.ScreenType;

import static com.badlogic.gdx.Gdx.input;

public class MainControlScreen implements Screen, InputProcessor {

    Main game;
    BitmapFont font;

<<<<<<< Updated upstream
    private float fontX, fontY, fontWidth = 500, fontHeight= 100;
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
=======
    Music music;
    private final Texture backButtonTexture;
    private final float backButtonX, backButtonY, backButtonWidth = 100, backButtonHeight = 50;

    private final Texture controlLabel;

    private final float controlLabelX;
    private final float controlLabelY;
    private final float controlLabelWidth = 500;
    private final float controlLabelHeight = 130;

    private final Texture wLabel;
    private final float wLabelX;
    private final float wLabelY;
    private final float wLabelWidth = 100;
    private final float wLabelHeight = 100;

    private final Texture arrowUp;
>>>>>>> Stashed changes

    private final float arrowUpX;

    private final float arrowUpY;
    private final float arrowUpWidth = 100;
    private final float arrowUpHeight= 100;

    private final Texture arrowRight;
    private final float arrowRightX;
    private final float arrowRightY;
    private final float arrowRightWidth = 100;
    private final float arrowRightHeight = 100;

    private final Texture aLabel;
    private final float aLabelX;
    private final float aLabelY;
    private final float aLabelWidth = 100;
    private final float aLabelHeight = 100;

    private final Texture arrowDown;
    private final float arrowDownX;
    private final float arrowDownY;
    private final float arrowDownWidth = 100;
    private final float arrowDownHeight = 100;

    private final Texture sLabel;
    private final float sLabelX;
    private final float sLabelY;
    private final float sLabelWidth = 100;
    private final float sLabelHeight = 100;

    private final Texture dLabel;
    private final float dLabelX;
    private final float dLabelY;
    private final float dLabelWidth = 100;
    private final float dLabelHeight = 100;

    private final Texture arrowLeft;
    private final float arrowLeftX;
    private final float arrowLeftY;
    private final float arrowLeftWidth = 100;
    private final float arrowLeftHeight = 100;

    public MainControlScreen(Main game, Music music) {
        this.game = game;
<<<<<<< Updated upstream
        backButton = new Texture("assets/settings_gui/back_button.png");
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));

        Gdx.input.setInputProcessor(this);

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
        backButtonY = (float) game.screenHeight / 6 - 150;
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
        fontX = (game.screenWidth - fontWidth) / 2;
        fontY = (game.screenHeight - fontHeight) / 2;

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
=======
        this.music = music;
        backButtonTexture = new Texture("assets/settings_gui/back_button.png");
        controlLabel = new Texture("assets/controls_gui/controls_label.png");
        wLabel = new Texture("assets/controls_gui/w_button.png");
        arrowUp = new Texture("assets/controls_gui/arrow_up_button.png");
        arrowRight = new Texture("assets/controls_gui/arrow_right_button.png");
        aLabel = new Texture("assets/controls_gui/a_button.png");
        arrowDown = new Texture("assets/controls_gui/arrow_down_button.png");
        sLabel = new Texture("assets/controls_gui/s_button.png");
        dLabel = new Texture("assets/controls_gui/d_button.png");
        arrowLeft = new Texture("assets/controls_gui/arrow_left_button.png");


        backButtonX = (game.screenWidth - backButtonWidth) /2;
        backButtonY = (float) game.screenHeight / 6 - 100;
        controlLabelX = (game.screenWidth - controlLabelWidth) / 2;
        controlLabelY = game.screenHeight - (controlLabelHeight * 2);
        wLabelX = (game.screenWidth - wLabelWidth) / 2 - 100;
        wLabelY = game.screenHeight - wLabelHeight - 300;
        arrowUpX = (game.screenWidth - arrowUpWidth) / 2 + 100;
        arrowUpY = game.screenHeight - arrowUpHeight - 300;
        arrowRightX = (game.screenWidth - arrowRightWidth) / 2 + 100;
        arrowRightY = game.screenHeight - arrowRightHeight - 425;
        aLabelX = (game.screenWidth - aLabelWidth) / 2 - 100;
        aLabelY = game.screenHeight - aLabelHeight - 425;
        sLabelX = (game.screenWidth - sLabelWidth) / 2 - 100;
        sLabelY = game.screenHeight - sLabelHeight - 550;
        arrowDownX = (game.screenWidth - arrowDownWidth) / 2 + 100;
        arrowDownY = game.screenHeight - arrowDownHeight - 550;
        dLabelX = (game.screenWidth - dLabelWidth) / 2 - 100;
        dLabelY = game.screenHeight - dLabelHeight - 675;
        arrowLeftX = (game.screenWidth - arrowLeftWidth) / 2 + 100;
        arrowLeftY = game.screenHeight - arrowLeftHeight - 675;
    }

>>>>>>> Stashed changes

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        game.batch.draw(controlLabel, controlLabelX, controlLabelY, controlLabelWidth, controlLabelHeight);
        game.batch.draw(wLabel, wLabelX, wLabelY, wLabelWidth, wLabelHeight);
<<<<<<< Updated upstream
        game.batch.draw(aLabel, aLabelX, aLabelY, aLabelWidth, aLabelHeight);
        game.batch.draw(sLabel, sLabelX, sLabelY, sLabelWidth, sLabelHeight);
        game.batch.draw(dLabel, dLabelX, dLabelY, dLabelWidth, dLabelHeight);
        game.batch.draw(arrowUpLabel, arrowUpLabelX, arrowUpLabelY, arrowUpWidth, arrowUpHeight);
        game.batch.draw(arrowLeftLabel, arrowLeftLabelX, arrowLeftLabelY, arrowLeftLabelWidth, arrowLeftLabelHeight);
        game.batch.draw(arrowDownLabel, arrowDownLabelX, arrowDownLabelY, arrowDownLabelWidth, arrowDownLabelHeight);
        game.batch.draw(arrowRightLabel, arrowRightLabelX, arrowRightLabelY, arrowRightLabelWidth, arrowRightLabelHeight);
        String description_one = "Welcome to Heslington Hustle!";
        String description_two = "You are a second-year Computer Science student with exams in only 7 days.";
        String description_three = "Explore the map and interact with buildings to eat, study, sleep, and have fun.";
        String description_four = "To get a good grade, you need to balance studying with self-care and recreation.";
        String description_five = "Good luck!";
        font.draw(game.batch, description_five, fontX+475, fontY+10);
        font.draw(game.batch, description_four, fontX+475, fontY+35);
        font.draw(game.batch, description_three, fontX+475, fontY+60);
        font.draw(game.batch, description_two, fontX+475, fontY+85);
        font.draw(game.batch, description_one, fontX+475, fontY+110);
=======
        game.batch.draw(arrowUp, arrowUpX, arrowUpY, arrowUpWidth, arrowUpHeight);
        game.batch.draw(arrowRight, arrowRightX, arrowRightY, arrowRightWidth, arrowRightHeight);
        game.batch.draw(aLabel, aLabelX, aLabelY, aLabelWidth, aLabelHeight);
        game.batch.draw(sLabel, sLabelX, sLabelY, sLabelWidth, sLabelHeight);
        game.batch.draw(arrowDown, arrowDownX, arrowDownY, arrowDownWidth, arrowDownHeight);
        game.batch.draw(dLabel, dLabelX, dLabelY, dLabelWidth, dLabelHeight);
        game.batch.draw(arrowLeft, arrowLeftX, arrowLeftY, arrowLeftWidth, arrowLeftHeight);
>>>>>>> Stashed changes
        game.batch.end();


    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float worldX = screenX * game.defWidth / (float) game.screenWidth;
        float worldY = (game.screenHeight - screenY) * game.defHeight / (float) game.screenHeight;

        if (worldX >= backButtonX && worldX <= backButtonX + backButtonWidth &&
                worldY >= backButtonY && worldY <= backButtonY + backButtonHeight) {
<<<<<<< Updated upstream
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.setScreen(ScreenType.MAIN_MENU);

=======
            game.screenManager.setScreen(ScreenType.SETTINGS, game.music); //idfk
>>>>>>> Stashed changes
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
    }

}
