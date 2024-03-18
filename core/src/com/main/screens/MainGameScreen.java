package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    Texture menuButton;
    float menuButtonY, menuButtonX, menuButtonWidth, menuButtonHeight;
    float counterBackgroundY, counterBackgroundX, counterBackgroundWidth, counterBackgroundHeight;
    float popupMenuWidth, popupMenuHeight;

    Texture energyBar;
    Texture popupMenu;
    String popupMenuType;
    float energyBarY, energyBarX, energyBarWidth, energyBarHeight;
    int energyCounter = 10;

    Texture counterBackground;
    int dayNum = 1;
    int recActivity, studyHours;

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

        menuButton = new Texture("menu_buttons/menu_icon.png");
        counterBackground = new Texture("conter_background.png");
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
        String counterString = "Day: "+ dayNum + "\nRecreation Activities done: " + recActivity + "\nStudy hours: " + studyHours;
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        game.batch.draw(menuButton, menuButtonX, menuButtonY, menuButtonWidth, menuButtonHeight);
        game.batch.draw(energyBar, energyBarX, energyBarY, energyBarWidth, energyBarHeight);
        game.batch.draw(counterBackground, counterBackgroundX, counterBackgroundY, counterBackgroundWidth, counterBackgroundHeight);
        font.draw(game.batch, counterString, game.screenWidth - 320 * game.scaleFactorX, game.screenHeight - 15 * game.scaleFactorY);
        game.batch.end();
    }

    @Override
    public void render(float delta) {
        player.update(delta); // This line updates player position and animation state.
        ScreenUtils.clear(0, 0, 1, 1);
        drawWorldElements();
        drawUIElements();
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
