package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class MainGameScreen implements Screen {

    BitmapFont font;
    OrthographicCamera camera;

    public static final float speed = 350; // walking speed per frame
    public static final float animation_speed = 0.5f; // speed that sprite will animate or frame duration
    public static final int character_width = 24; // this is in reference to the sprite sheet
    public static final int character_heigth = 38;

    Animation<TextureRegion> walkDownAnimation, walkRightAnimation, walkLeftAnimation, walkUpAnimation;
    Animation<TextureRegion> idleDownAnimation, idleRightAnimation, idleLeftAnimation, idleUpAnimation;
    Animation<TextureRegion> currentAnimation; // the amount of sprite frames there are in total to animate with
    //int spriteNum; // which frame the sprite should be on
    float stateTime;

    float x;
    float y;

    Main game;

    public MainGameScreen (Main game) {
        this.game = game;
        y = 15;
        x = (float) game.screenWidth /2 - (float) game.screenHeight /2;
        //spriteNum = 2;


        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenWidth, game.screenHeight);
        camera.update();


        // here the TextureRegions' internal path can be changed with a variable for when the player chooses the gender
        Texture idleSheet = new Texture("character/boy_idle.png");
        TextureRegion[][] idleSpriteSheet = TextureRegion.split(idleSheet, character_width, character_heigth); // Splits the sprite sheet up by its frames

        Texture walkSheet = new Texture("character/boy_walk.png");
        TextureRegion[][] walkSpriteSheet = TextureRegion.split(walkSheet, character_width, character_heigth); // Splits the sprite sheet up by its frames

        walkDownAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[0]); // First row for down
        walkRightAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[1]); // Second row for right
        walkLeftAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[2]); // Third row for left
        walkUpAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[3]); // Fourth row for up


        idleDownAnimation = new Animation<>(animation_speed, idleSpriteSheet[0][0], idleSpriteSheet[0][1]);
        idleRightAnimation = new Animation<>(animation_speed, idleSpriteSheet[1][0], idleSpriteSheet[1][1]);
        idleLeftAnimation = new Animation<>(animation_speed, idleSpriteSheet[2][0], idleSpriteSheet[2][1]);
        idleUpAnimation = new Animation<>(animation_speed, idleSpriteSheet[3][0], idleSpriteSheet[3][1]);

        currentAnimation = idleDownAnimation;
    }

    @Override
    public void show () {
    }

    @Override
    public void render (float delta) {

        String counterString;
        counterString = "Sleeping: x \nEating: y\nRecreation: z\nStudying: s";
        boolean isMoving = false;
        // checks movement and updates animation, adjusts speed with delta time
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += (speed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkUpAnimation;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= (speed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkDownAnimation;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= (speed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkLeftAnimation;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += (speed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkRightAnimation;
            isMoving = true;
        }

        if (!isMoving) {
            if (currentAnimation == walkDownAnimation) currentAnimation = idleDownAnimation;
            else if (currentAnimation == walkRightAnimation) currentAnimation = idleRightAnimation;
            else if (currentAnimation == walkLeftAnimation) currentAnimation = idleLeftAnimation;
            else if (currentAnimation == walkUpAnimation) currentAnimation = idleUpAnimation;
        }

        camera.position.set(x+ (float) character_width /2, y + (float) character_heigth /2, 0);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        stateTime += delta;

        ScreenUtils.clear(0, 0, 1, 1);

        game.batch.begin();
        game.batch.draw(currentAnimation.getKeyFrame(stateTime, true), x, y, character_width, character_heigth);
        font.draw(game.batch, counterString, game.screenWidth - 100, game.screenHeight - 20);
        game.batch.end();
    }

    @Override
    public void resize (int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();

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
