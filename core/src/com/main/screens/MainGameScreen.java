package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;
import com.main.entity.Player;
import com.main.map.GameMap;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainGameScreen implements Screen {
    Player player;
    BitmapFont font;
    GameMap gameMap;

    Texture menuButton;
    float menuButtonY;
    float menuButtonX;
    float menuButtonWidth;
    float menuButtonHeight;
    OrthographicCamera camera;

    Main game;

    public MainGameScreen(Main game) {
        this.game = game;

        font = new BitmapFont();

        menuButton = new Texture("menu_buttons/menu_icon.png");

        menuButtonWidth = 64;
        menuButtonHeight = 64;
        menuButtonX = 10;
        menuButtonY = (game.screenHeight) - menuButtonHeight - 10;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenWidth, game.screenHeight);
        camera.update();

        gameMap = new GameMap();

    }

    @Override
    public void show() {
        player = new Player("character/boy_walk.png", game);
    }

    @Override
    public void render(float delta) {
        player.update(delta); // This line updates player position and animation state.
        //gameMap.update(delta); //for future map animations

        String counterString;
        counterString = "Sleeping: x \nEating: y\nRecreation: z\nStudying: s";

        /*
        camera.position.set(x+ (float) character_width /2, y + (float) character_heigth /2, 0);
        camera.update();
        */

        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 1, 1);

        game.batch.begin();

        gameMap.render();

        game.batch.draw(menuButton, menuButtonX, menuButtonY, menuButtonWidth, menuButtonHeight);
        if (Gdx.input.justTouched()) {
            int touchX = Gdx.input.getX();
            int touchY = game.screenHeight - Gdx.input.getY();

            if (touchX >= menuButtonX && touchX <= menuButtonX + menuButton.getWidth()
                    && touchY >= menuButtonY && touchY <= menuButtonY + menuButton.getHeight()) {
                game.setScreen(new MainMenuScreen(game));

            }
        }
        // Draw the player with the current frame of animation
        game.batch.draw(player.getCurrentFrame(), player.x, player.y, Player.character_width, Player.character_heigth);

        font.draw(game.batch, counterString, game.screenWidth - 100, game.screenHeight - 20);
        game.batch.end();
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
}
