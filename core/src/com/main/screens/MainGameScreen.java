package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;

public class MainGameScreen implements Screen {

    public static final float speed = 420; // walking speed per frame
    public static final float animation_speed = 0.5f; // speed that sprite will animate or frame duration
    public static final int character_width = 24; // this is in reference to the sprite sheet
    public static final int character_heigth = 38;

    Animation[] spriteNums; // the amount of sprite frames there are in total to animate with
    int spriteNum; // which frame the sprite should be on
    float stateTime;

    float x;
    float y;

    Main game;

    public MainGameScreen (Main game) {
        this.game = game;
        y = 15;
        x = (float) game.screenWidth /2 - (float) game.screenHeight /2;
        spriteNum = 2;
        spriteNums = new Animation[8];

        // here the TextureRegions' internal path can be changed with a variable for when the player chooses the gender
        TextureRegion[][] walkSpriteSheet = TextureRegion.split(new Texture("character/boy_walk.png"), character_width, character_heigth); // Splits the sprite sheet up by its frames

        TextureRegion[] animationFrames = walkSpriteSheet[0];
        spriteNums[spriteNum] = new Animation<TextureRegion>(animation_speed, animationFrames);

    }

    @Override
    public void show () {
    }

    @Override
    public void render (float delta) {
        // MOVEMENT WITH DELTA TIME
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += (speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= (speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= (speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += (speed * Gdx.graphics.getDeltaTime());
        }

        stateTime += delta;

        ScreenUtils.clear(0, 0, 1, 1);
        game.batch.begin();

        game.batch.draw((TextureRegion) spriteNums[spriteNum].getKeyFrame(stateTime, true), x, y, character_width, character_heigth);

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
    public void counters() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
    }
}
