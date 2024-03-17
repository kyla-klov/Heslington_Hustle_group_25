package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;
import com.main.entity.Player;
import com.main.map.GameMap;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.main.utils.ScreenType;

public class MainGameScreen implements Screen, InputProcessor {
    Player player;
    BitmapFont font;
    GameMap gameMap;

    Texture menuButton;
    float menuButtonY, menuButtonX, menuButtonWidth, menuButtonHeight;

    Texture energyBar;
    float energyBarY, energyBarX, energyBarWidth, energyBarHeight;
    int energyCounter = 10;

    int dayNum = 1;
    int recActivity;
    int studyHours;

//    Texture hit;
//    float hitWidth;
//    float hitHeight;
//    float hitX;
//    float hitY;
    OrthographicCamera camera;

    Main game;

    public MainGameScreen(Main game) {
        this.game = game;
        camera = new OrthographicCamera();
        gameMap = new GameMap(camera);
        player = new Player(game, gameMap, camera);
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));

        menuButton = new Texture("menu_buttons/menu_icon.png");

//        hit = new Texture("energy/hit.png");
//        hitWidth = 100;
//        hitHeight = 100;
//        hitX = (float) game.screenWidth / 2;
//        hitY = (float) game.screenHeight / 2;

        menuButtonWidth = 64;
        menuButtonHeight = 64;
        menuButtonX = 10;
        menuButtonY = (game.screenHeight) - menuButtonHeight - 10;

        energyBarWidth = 200;
        energyBarHeight = 64;
        energyBarX = 30 + menuButtonWidth;
        energyBarY = game.screenHeight - 10 - energyBarHeight;

        energyBar = setEnergyBar();

        camera.setToOrtho(false, game.screenWidth/3f, game.screenHeight/3f);
        camera.update();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        player.updateGender();
        // setting play position to liking
        player.setPos( gameMap.getTileSize() * 68, gameMap.getTileSize() * 62);
    }

    @Override
    public void render(float delta) {
        player.update(delta); // This line updates player position and animation state.

        String counterString;
        counterString = "Day: "+ dayNum + "\nRecreation Activities done: " + recActivity + "\nStudy hours: " + studyHours;

        /*
        if (player.collidesWith(hit, hitX, hitY)) {
            energyCounter--;
            player.setPos(player.getStartPos().x, player.getStartPos().y);
            energyBar.dispose();
            energyBar = setEnergyBar();
        }
         */

        ScreenUtils.clear(0, 0, 1, 1);

        game.batch.begin();

        // render camera
        game.batch.setProjectionMatrix(camera.combined);
//        game.batch.draw(hit, hitX, hitY, hitWidth, hitHeight);
        game.batch.draw(player.getCurrentFrame(), player.worldX, player.worldY, Player.spriteX, Player.spriteY);
        // render map
        gameMap.render();

        game.batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        game.batch.draw(menuButton, menuButtonX, menuButtonY, menuButtonWidth, menuButtonHeight);
        game.batch.draw(energyBar, energyBarX, energyBarY, energyBarWidth, energyBarHeight);
        font.draw(game.batch, counterString, game.screenWidth - 300, game.screenHeight - 10);

        game.batch.end();
    }

    public Texture setEnergyBar() {
        if (energyCounter > 0) {
            System.out.println("energy/energy_" + energyCounter + ".png");
            return new Texture("energy/energy_" + energyCounter + ".png");
        } else {
            return new Texture("energy/energy_0.png");
        }
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        float touchX = screenX * game.defWidth / (float) game.screenWidth;
        float touchY = (game.screenHeight - screenY) * game.defHeight / (float) game.screenHeight;
        if (touchX >= menuButtonX && touchX <= menuButtonX + menuButtonWidth && touchY >= menuButtonY && touchY <= menuButtonY + menuButtonHeight) {
            game.screenManager.setScreen(ScreenType.MAIN_MENU);
        }
        return true;
    }

    @Override
    public void resize(int i, int i1) {

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
