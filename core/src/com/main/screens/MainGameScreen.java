package com.main.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;
import com.main.entity.Player;

public class MainGameScreen implements Screen {
    Player player;
    BitmapFont font;

    float stateTime;

    float x;
    float y;

    Main game;

    public MainGameScreen (Main game) {
        this.game = game;

        font = new BitmapFont();

    }

    @Override
    public void show () {
        player = new Player("character/boy_walk.png", game);
    }

    @Override
    public void render (float delta) {

        String counterString;
        counterString = "Sleeping: x \nEating: y\nRecreation: z\nStudying: s";


        ScreenUtils.clear(0, 0, 1, 1);
        game.batch.begin();

        // Draw the player with the current frame of animation
        game.batch.draw(player.getCurrentFrame(), player.x, player.y, Player.character_width, Player.character_heigth);

        font.draw(game.batch, counterString, game.screenWidth - 100, game.screenHeight - 20);

        game.batch.end();
    }

    @Override
    public void resize (int width, int height) {

    }

    @Override
    public void pause () {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }



    @Override
    public void dispose() {
        game.batch.dispose();
    }
}
