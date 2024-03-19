package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    BitmapFont font;
    BitmapFont font2;
    GameMap gameMap;
    ShapeRenderer shapeRenderer;

    Texture menuButton;
    float menuButtonY, menuButtonX, menuButtonWidth, menuButtonHeight;
    float counterBackgroundY, counterBackgroundX, counterBackgroundWidth, counterBackgroundHeight;
    float popupMenuWidth, popupMenuHeight;

    Texture energyBar;
    Texture popupMenu;
    String popupMenuType;
    float energyBarY, energyBarX, energyBarWidth, energyBarHeight;
    int energyCounter = 10;

    // counters, time and day
    Texture counterBackground;
    int dayNum = 1;
    int recActivity, studyHours;
    private float timeElapsed = 0f; // Time elapsed in the game, in seconds.
    private int currentHour = 10; // Game starts at 10:00 am.
    private final int hoursInDay = 16; // Player sleeps for 8 hours

    // fading black
    private boolean isFading = false;
    private float fadeAlpha = 0f;
    private final float fadeDuration = 2f; // Duration of the fade effect in seconds
    private float fadeTimer = 0f;

    final float zoom = 3f;

    OrthographicCamera camera;

    Main game;

    public MainGameScreen(Main game) {
        this.game = game;
        camera = new OrthographicCamera();
        gameMap = new GameMap(camera);
        player = new Player(game, gameMap, camera);
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        popupMenuType = "";
        shapeRenderer = new ShapeRenderer();

        menuButton = new Texture("menu_buttons/menu_icon.png");
        counterBackground = new Texture("counter_background.png");
        popupMenu = new Texture("popup_menu.png");

        calculateDimensions();
        calculatePositions();

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
        popupMenuWidth = 25;
        popupMenuHeight = 25;
        font.getData().setScale(game.scaleFactorX, game.scaleFactorY);
        font2.getData().setScale(0.3f * game.scaleFactorX, 0.3f * game.scaleFactorY);
    }

    private void calculatePositions(){
        menuButtonX = 10 * game.scaleFactorX;
        menuButtonY = game.screenHeight - menuButtonHeight - 10 * game.scaleFactorY;
        energyBarX = 30 * game.scaleFactorX + menuButtonWidth;
        energyBarY = game.screenHeight - energyBarHeight - 10 * game.scaleFactorY;
        counterBackgroundX = game.screenWidth - counterBackgroundWidth;
        counterBackgroundY = game.screenHeight - counterBackgroundHeight;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        player.updateGender();
        // setting play position to liking
        player.setPos( gameMap.getTileSize() * 68, gameMap.getTileSize() * 62);
    }

    private void drawWorldElements(){
        CollisionHandler collisionHandler = player.getCollisionHandler();
        popupMenuType = "";
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        gameMap.render();
        game.batch.draw(player.getCurrentFrame(), player.worldX, player.worldY, Player.spriteX, Player.spriteY);
        if (collisionHandler.isTouching("Gym_door", player.getHitBox())){
            popupMenuType = "gym";
            game.batch.draw(popupMenu, player.worldX + 30, player.worldY + 20, popupMenuWidth, popupMenuHeight);
            font2.draw(game.batch, "Click Me", player.worldX + 31, player.worldY + 45);
        }
        game.batch.end();
    }

    private void drawUIElements(){
        String counterString = String.format("Recreation Activities done: " + recActivity + "\nStudy hours: " + studyHours, dayNum, timeElapsed );
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
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
        drawFadeEffect();
    }

    private void updateGameTime(float delta) {
        float gameDayLengthInSeconds = 60f;
        float secondsPerGameHour = gameDayLengthInSeconds / hoursInDay;
        timeElapsed += delta;

        // Calculate the current hour in game time
        int hoursPassed = (int)(timeElapsed / secondsPerGameHour);
        currentHour = 8 + hoursPassed;

        // Ensures active hours (8:00AM to 12:00AM)
        if (currentHour >= 24) {
            currentHour = 10 + (currentHour - 26);
            dayNum++;
            timeElapsed -= gameDayLengthInSeconds;
        }
        if (currentHour == 24 && !isFading) {
            isFading = true;
            fadeTimer = 0f;
        }

        if (isFading) {
            fadeTimer += delta;
            fadeAlpha = Math.min(fadeTimer / fadeDuration, 1f); // Fade in

            if (fadeTimer >= fadeDuration) {
                // After the fade-in completes, reset for fade-out or end the fade effect
                if (fadeAlpha == 1f) { // Fully faded in, start fading out
                    fadeTimer = 0f;
                    isFading = false; // or keep true to fade out immediately after fade in
                } else {
                    fadeAlpha = 1f - Math.min((fadeTimer - fadeDuration) / fadeDuration, 1f); // Fade out
                    if (fadeTimer >= fadeDuration * 2) { // Fade-out complete
                        isFading = false;
                        fadeTimer = 0f;
                        fadeAlpha = 0f;
                        // Here, you can also increment dayNum or perform other actions
                    }
                }
            }
        }
    }

    private void drawGameTime() {
        // Adjust the format if you want to display minutes or seconds
        String timeString = String.format("Day: %d       Time: %02d:00", dayNum, currentHour);
        game.batch.begin();
        font.draw(game.batch, timeString, game.screenWidth - 320 * game.scaleFactorX, game.screenHeight - 15 * game.scaleFactorY);
        game.batch.end();
    }

    private void drawFadeEffect() {
        if (isFading) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, fadeAlpha); // Black with varying alpha
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();

            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }

    public Texture setEnergyBar() {
        if (energyCounter > 0) {
            System.out.println("energy/energy_" + energyCounter + ".png");
            return new Texture("energy/energy_" + energyCounter + ".png");
        } else {
            return new Texture("energy/energy_0.png");
        }
    }

    @Override
    public boolean touchDown(int touchX, int touchY, int pointer, int button){
        touchY = game.screenHeight - touchY;
        Vector3 gym_menu = camera.project(new Vector3(player.worldX + 30, player.worldY + 20, 0));

        if (touchX >= menuButtonX && touchX <= menuButtonX + menuButtonWidth && touchY >= menuButtonY && touchY <= menuButtonY + menuButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.setScreen(ScreenType.MAIN_MENU);
        }
        else if (popupMenuType.equals("gym") && touchX >= gym_menu.x && touchX <= gym_menu.x + popupMenuWidth * zoom && touchY >= gym_menu.y && touchY <= gym_menu.y + popupMenuHeight * zoom) {
            System.out.println("Hello World");
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
        shapeRenderer.dispose();
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
