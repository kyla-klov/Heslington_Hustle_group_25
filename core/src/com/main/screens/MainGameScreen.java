package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;
import com.main.entity.Player;
import com.main.map.GameMap;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.main.utils.CollisionHandler;
import com.main.utils.ScreenType;

public class MainGameScreen implements Screen, InputProcessor {
    Player player;
    BitmapFont font, popupFont, durationFont;
    GameMap gameMap;

    Texture menuButton;
    float menuButtonY, menuButtonX, menuButtonWidth, menuButtonHeight;
    float counterBackgroundY, counterBackgroundX, counterBackgroundWidth, counterBackgroundHeight;
    float popupMenuWidth, popupMenuHeight;

    private final Color shader;
    private boolean showPopup;
    private String activity;

    Texture energyBar;
    Texture popupMenu;
    Texture durationUpButton, durationDownButton, menuBackButton, menuStudyButton, menuSleepButton, menuGoButton, durationMenuBackground;
    float durationUpButtonX, durationDownButtonX, durationMenuBackgroundX, durationButtonY, durationMenuBackgroundY;
    float durationUpButtonWidth, durationDownButtonWidth, durationMenuBackgroundWidth, durationUpButtonHeight, durationDownButtonHeight, durationMenuBackgroundHeight;
    float menuBackButtonWidth, menuBackButtonHeight, activityButtonWidth, activityButtonHeight;
    float menuBackButtonX, activityButtonX, durationMenuButtonY;
    float durationTextY, menuTitleY, hoursLabelY;
    String popupMenuType;
    float energyBarY, energyBarX, energyBarWidth, energyBarHeight;
    int energyCounter = 10;

    int duration = 1;

    Texture counterBackground;
    int dayNum = 1;
    int recActivity, studyHours;
    private float timeElapsed = 0f; // Time elapsed in the game, in seconds.
    private int currentHour = 10; // Game starts at 10:00 am.
    private final int hoursInDay = 16; // Player sleeps for 8 hours
    float gameDayLengthInSeconds = 60f;
    float secondsPerGameHour;

    final float zoom = 3f;

    OrthographicCamera camera;

    ShapeRenderer shapeRenderer;

    Main game;

    public MainGameScreen(Main game) {
        this.game = game;
        camera = new OrthographicCamera();
        gameMap = new GameMap(camera);
        player = new Player(game, gameMap, camera);
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        popupFont = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        durationFont = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        popupMenuType = "";
        shader = new Color(0.5f, 0.5f, 0.5f, 1);
        showPopup = false;
        activity = "";
        secondsPerGameHour = gameDayLengthInSeconds / hoursInDay;

        shapeRenderer = new ShapeRenderer();
        menuButton = new Texture("menu_buttons/menu_icon.png");
        counterBackground = new Texture("counter_background.png");
        popupMenu = new Texture("popup_menu.png");
        durationMenuBackground = new Texture("assets/duration_menu_background.png");
        durationUpButton = new Texture("assets/settings_gui/arrow_right_button.png");
        durationDownButton = new Texture("assets/settings_gui/arrow_left_button.png");
        menuBackButton = new Texture("assets/settings_gui/back_button.png");
        menuStudyButton = new Texture("assets/study_button.png");
        menuSleepButton = new Texture("assets/sleep_button.png");
        menuGoButton = new Texture("assets/go_button.png");

        calculateDimensions();
        calculatePositions();

        popupFont.getData().setScale(0.4f, 0.4f);

        energyBar = setEnergyBar();

        camera.setToOrtho(false, game.screenWidth/zoom, game.screenHeight/zoom);
        camera.update();

    }

    private void calculateDimensions(){
        menuButtonWidth = 64 * game.scaleFactorX;
        menuButtonHeight = 64 * game.scaleFactorY;
        energyBarWidth = 200 * game.scaleFactorX;
        energyBarHeight = 64 * game.scaleFactorY;
        counterBackgroundWidth = 370 * game.scaleFactorX;
        counterBackgroundHeight = 120 * game.scaleFactorY;
        durationUpButtonWidth = 50 * game.scaleFactorX;
        durationUpButtonHeight = 50 * game.scaleFactorY;
        durationDownButtonWidth = 50 * game.scaleFactorX;
        durationDownButtonHeight = 50 * game.scaleFactorY;
        durationMenuBackgroundWidth = 500 * game.scaleFactorX;
        durationMenuBackgroundHeight = 500 * game.scaleFactorY;
        menuBackButtonWidth = 150 * game.scaleFactorX;
        menuBackButtonHeight = 75 * game.scaleFactorY;
        activityButtonWidth = 150 * game.scaleFactorX;
        activityButtonHeight = 75 * game.scaleFactorY;
        popupMenuWidth = 35;
        popupMenuHeight = 10;
        font.getData().setScale(game.scaleFactorX, game.scaleFactorY);
        durationFont.getData().setScale(3f * game.scaleFactorX, 3f * game.scaleFactorY);

    }

    private void calculatePositions(){
        menuButtonX = 10 * game.scaleFactorX;
        menuButtonY = game.screenHeight - menuButtonHeight - 10 * game.scaleFactorY;
        energyBarX = 30 * game.scaleFactorX + menuButtonWidth;
        energyBarY = game.screenHeight - energyBarHeight - 10 * game.scaleFactorY;
        counterBackgroundX = game.screenWidth - counterBackgroundWidth;
        counterBackgroundY = game.screenHeight - counterBackgroundHeight;
        durationMenuBackgroundX = game.screenWidth/2f - durationMenuBackgroundWidth/2f;
        durationMenuBackgroundY = game.screenHeight/2f - durationMenuBackgroundHeight/2f;
        durationDownButtonX = game.screenWidth/2f - durationDownButtonWidth/2f - 150 * game.scaleFactorX;
        durationUpButtonX = game.screenWidth/2f - durationUpButtonWidth/2f + 150 * game.scaleFactorX;
        durationButtonY = game.screenHeight/2f - durationUpButtonHeight/2f - 60 * game.scaleFactorY;
        menuBackButtonX = durationDownButtonX;
        activityButtonX = durationUpButtonX + durationUpButtonWidth - activityButtonWidth;
        durationMenuButtonY = durationButtonY - 125 * game.scaleFactorY;
        menuTitleY = 730 * game.scaleFactorY;
        durationTextY = 503 * game.scaleFactorY;
        hoursLabelY = 580 * game.scaleFactorY;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        player.updateGender();
        // setting play position to liking
        player.setPos( gameMap.getTileSize() * 68, gameMap.getTileSize() * 62);
    }

    private void isHovering(float posX, float posY){
        int mouseX = Gdx.input.getX();
        int mouseY = game.screenHeight - Gdx.input.getY();
        Vector3 menuOpt = camera.project(new Vector3(posX, posY, 0));
        if (mouseX >= menuOpt.x && mouseX <= menuOpt.x + popupMenuWidth * zoom && mouseY >= menuOpt.y && mouseY <= menuOpt.y + popupMenuHeight * zoom) {
            game.batch.setColor(shader);
        }
        else {
            game.batch.setColor(Color.WHITE);
        }
    }

    private void drawMenuOption(float posX, float posY, String text){
        isHovering(posX, posY);
        GlyphLayout layout = new GlyphLayout();
        layout.setText(popupFont, text);
        game.batch.draw(popupMenu, posX, posY, popupMenuWidth, popupMenuHeight);
        popupFont.draw(game.batch, text, posX + (popupMenuWidth - layout.width)/2, posY + (popupMenuHeight + layout.height)/2f - popupFont.getDescent() - layout.height/4f);
        game.batch.setColor(Color.WHITE);
    }

    private void drawDurationMenu(){
        game.batch.begin();
        GlyphLayout layout = new GlyphLayout();
        float textX;
        Texture activityButton = null;
        String title = "";

        if (activity.equals("study")){
            activityButton = menuStudyButton;
            title = "Study Schedule";
        }
        else if (activity.equals("sleep")){
            activityButton = menuSleepButton;
            title = "Sleep Duration";
        }
        else if (activity.equals("exercise")){
            activityButton = menuGoButton;
            title = "Exercise";
        }

        game.batch.draw(durationMenuBackground, durationMenuBackgroundX, durationMenuBackgroundY, durationMenuBackgroundWidth, durationMenuBackgroundHeight);
        game.batch.draw(durationDownButton, durationDownButtonX, durationButtonY, durationDownButtonWidth, durationDownButtonHeight);
        game.batch.draw(durationUpButton, durationUpButtonX, durationButtonY, durationUpButtonWidth, durationUpButtonHeight);
        game.batch.draw(menuBackButton, menuBackButtonX, durationMenuButtonY, menuBackButtonWidth, menuBackButtonHeight);
        game.batch.draw(activityButton, activityButtonX, durationMenuButtonY, activityButtonWidth, activityButtonHeight);

        layout.setText(durationFont, Integer.toString(duration));
        textX = durationMenuBackgroundX + (durationMenuBackgroundWidth - layout.width)/2f;
        durationFont.draw(game.batch, Integer.toString(duration), textX, durationTextY);
        layout.setText(durationFont, title);
        textX = durationMenuBackgroundX + (durationMenuBackgroundWidth - layout.width)/2f;
        durationFont.draw(game.batch, title, textX, menuTitleY);
        layout.setText(durationFont, "Hours");
        textX = durationMenuBackgroundX + (durationMenuBackgroundWidth - layout.width)/2f;
        durationFont.draw(game.batch, "Hours", textX, hoursLabelY);

        game.batch.end();
    }

    private void drawShadeOverlay(float alpha){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, alpha); // Adjust alpha for darkness
        shapeRenderer.rect(0, 0, gameMap.getWidth(), gameMap.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawWorldElements(){
        CollisionHandler collisionHandler = player.getCollisionHandler();
        popupMenuType = "";
        gameMap.render();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(player.getCurrentFrame(), player.worldX, player.worldY, Player.spriteX, Player.spriteY);
        if (collisionHandler.isTouching("Comp_sci_door", player.getHitBox())){
            popupMenuType = "Comp_sci_door";
            drawMenuOption(player.worldX + 30, player.worldY + 20, "Study");
        }
        else if (collisionHandler.isTouching("Piazza_door", player.getHitBox())){
            popupMenuType = "Piazza_door";
            drawMenuOption(player.worldX + 30, player.worldY + 20, "Study");
            drawMenuOption(player.worldX + 30, player.worldY + 35, "Eat");
        }
        else if (collisionHandler.isTouching("Goodricke_door", player.getHitBox())){
            popupMenuType = "Goodricke_door";
            drawMenuOption(player.worldX + 30, player.worldY + 20, "Sleep");
        }
        else if (collisionHandler.isTouching("Gym_door", player.getHitBox())){
            popupMenuType = "Gym_door";
            drawMenuOption(player.worldX + 30, player.worldY + 20, "Exercise");
        }
        game.batch.end();
    }

    private void drawUIElements(){
        String counterString = String.format("Recreation Activities done: " + recActivity + "\nStudy hours: " + studyHours, dayNum, timeElapsed );
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        if (showPopup) drawDurationMenu();
        game.batch.begin();
        game.batch.draw(menuButton, menuButtonX, menuButtonY, menuButtonWidth, menuButtonHeight);
        game.batch.draw(energyBar, energyBarX, energyBarY, energyBarWidth, energyBarHeight);
        game.batch.draw(counterBackground, counterBackgroundX, counterBackgroundY, counterBackgroundWidth, counterBackgroundHeight);
        font.draw(game.batch, counterString, game.screenWidth - 320 * game.scaleFactorX, game.screenHeight - 40 * game.scaleFactorY);
        game.batch.end();
    }

    @Override
    public void render(float delta) {
        player.update(delta); // This line updates player position and animation state.
        updateGameTime(delta); // Update the game clock
        ScreenUtils.clear(0, 0, 1, 1);
        drawWorldElements();
        drawUIElements();
        drawGameTime(); // Draw current time
    }

    private void updateGameTime(float delta) {
        timeElapsed += delta;

        // Calculate the current hour in game time
        int hoursPassed = (int)(timeElapsed / secondsPerGameHour);
        currentHour = 10 + hoursPassed; // Starts at 10:00 AM

        // Ensure the hour cycles through the active hours correctly (10 AM to 2 AM)
        if (currentHour >= 26) { // If it reaches 2 AM, reset to 10 AM the next day
            currentHour = 10 + (currentHour - 26);
            dayNum++;
            timeElapsed -= gameDayLengthInSeconds;
        }
    }

    private void drawGameTime() {
        // Adjust the format if you want to display minutes or seconds
        String timeString = String.format("Day: %d       Time: %02d:00", dayNum, currentHour);
        game.batch.begin();
        font.draw(game.batch, timeString, game.screenWidth - 320 * game.scaleFactorX, game.screenHeight - 15 * game.scaleFactorY);
        game.batch.end();
    }

    public Texture setEnergyBar() {
        if (energyCounter > 0) {
            return new Texture("energy/energy_" + energyCounter + ".png");
        } else {
            return new Texture("energy/energy_0.png");
        }
    }

    @Override
    public boolean touchDown(int touchX, int touchY, int pointer, int button){
        touchY = game.screenHeight - touchY;

        if (touchX >= menuButtonX && touchX <= menuButtonX + menuButtonWidth && touchY >= menuButtonY && touchY <= menuButtonY + menuButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.setScreen(ScreenType.MAIN_MENU);
        }
        else if (showPopup){
            if (activity.equals("study")) {
                if (touchX >= durationUpButtonX && touchX <= durationUpButtonX + durationUpButtonWidth && touchY >= durationButtonY && touchY <= durationButtonY + durationUpButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    if (duration < 4) duration++;
                } else if (touchX >= durationDownButtonX && touchX <= durationDownButtonX + durationDownButtonWidth && touchY >= durationButtonY && touchY <= durationButtonY + durationDownButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    if (duration > 1) duration--;
                } else if (touchX >= menuBackButtonX && touchX <= menuBackButtonX + menuBackButtonWidth && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + menuBackButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    showPopup = false;
                    duration = 1;
                } else if (touchX >= activityButtonX && touchX <= activityButtonX + activityButtonWidth && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + activityButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    game.screenManager.setScreen(ScreenType.MINI_GAME, duration);
                }
            }
            else if (activity.equals("exercise")) {
                if (touchX >= durationUpButtonX && touchX <= durationUpButtonX + durationUpButtonWidth && touchY >= durationButtonY && touchY <= durationButtonY + durationUpButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    if (duration < 4) duration++;
                } else if (touchX >= durationDownButtonX && touchX <= durationDownButtonX + durationDownButtonWidth && touchY >= durationButtonY && touchY <= durationButtonY + durationDownButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    if (duration > 1) duration--;
                } else if (touchX >= menuBackButtonX && touchX <= menuBackButtonX + menuBackButtonWidth && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + menuBackButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    showPopup = false;
                    duration = 1;
                } else if (touchX >= activityButtonX && touchX <= activityButtonX + activityButtonWidth && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + activityButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    showPopup = false;
                    recActivity++;
                    timeElapsed += duration * secondsPerGameHour;
                    duration = 1;
                }
            }
            else if (activity.equals("sleep")){
                if (touchX >= durationUpButtonX && touchX <= durationUpButtonX + durationUpButtonWidth && touchY >= durationButtonY && touchY <= durationButtonY + durationUpButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    if (duration < 10) duration++;
                } else if (touchX >= durationDownButtonX && touchX <= durationDownButtonX + durationDownButtonWidth && touchY >= durationButtonY && touchY <= durationButtonY + durationDownButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    if (duration > 1) duration--;
                } else if (touchX >= menuBackButtonX && touchX <= menuBackButtonX + menuBackButtonWidth && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + menuBackButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    showPopup = false;
                    duration = 1;
                } else if (touchX >= activityButtonX && touchX <= activityButtonX + activityButtonWidth && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + activityButtonHeight) {
                    game.gameData.buttonClickedSoundActivate();
                    showPopup = false;
                    duration = 1;
                }
            }
        }
        else if (popupMenuType.equals("Comp_sci_door")){
            Vector3 studyOpt = camera.project(new Vector3(player.worldX + 30, player.worldY + 20, 0));
            if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                game.gameData.buttonClickedSoundActivate();
                showPopup = true;
                activity = "study";
                duration = 1;
            }
        }
        else if (popupMenuType.equals("Goodricke_door")){
            Vector3 studyOpt = camera.project(new Vector3(player.worldX + 30, player.worldY + 20, 0));
            if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                game.gameData.buttonClickedSoundActivate();
                showPopup = true;
                activity = "sleep";
                duration = 1;
            }
        }
        else if (popupMenuType.equals("Gym_door")){
            Vector3 studyOpt = camera.project(new Vector3(player.worldX + 30, player.worldY + 20, 0));
            if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                game.gameData.buttonClickedSoundActivate();
                showPopup = true;
                activity = "exercise";
                duration = 1;
            }
        }
        else if (popupMenuType.equals("Piazza_door")){
            Vector3 studyOpt = camera.project(new Vector3(player.worldX + 30, player.worldY + 20, 0));
            Vector3 eatOpt = camera.project(new Vector3(player.worldX + 30, player.worldY + 35, 0));
            if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                game.gameData.buttonClickedSoundActivate();
                showPopup = true;
                activity = "study";
                duration = 1;
            }
            else if (touchX >= eatOpt.x && touchX <= eatOpt.x + popupMenuWidth * zoom && touchY >= eatOpt.y && touchY <= eatOpt.y + popupMenuHeight * zoom) {
                game.gameData.buttonClickedSoundActivate();
                energyCounter += 3;
                if (energyCounter > 10) energyCounter = 10;
                energyBar.dispose();
                energyBar = setEnergyBar();
                System.out.println("NOM NOM NOM");
            }
        }

        return true;
    }

    @Override
    public void resize(int i, int i1) {
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
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
