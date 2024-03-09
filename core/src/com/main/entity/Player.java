package com.main.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.main.Main;


public class Player extends Entity {
    Main game;

    public static final float animation_speed = 0.5f; // speed that sprite will animate or frame duration
    public static final int character_width = 24;// this is in reference to the sprite sheet
    public static final int character_heigth = 38;

    Animation<TextureRegion> walkDownAnimation, walkRightAnimation, walkLeftAnimation, walkUpAnimation;
    Animation<TextureRegion> idleDownAnimation, idleRightAnimation, idleLeftAnimation, idleUpAnimation;

    public Player(String spriteSheetPath, Main game) {
        this.game = game;
        this.speed = 350;
        y = 15;
        x = (float) game.screenWidth /2 - (float) game.screenHeight /2;

        // here the TextureRegions' internal path can be changed with a variable for when the player chooses the gender
        Texture idleSheet = new Texture("character/boy_idle.png");
        TextureRegion[][] idleSpriteSheet = TextureRegion.split(idleSheet, character_width, character_heigth); // Splits the sprite sheet up by its frames

        Texture walkSheet = new Texture("character/boy_walk.png");
        TextureRegion[][] walkSpriteSheet = TextureRegion.split(walkSheet, character_width, character_heigth); // Splits the sprite sheet up by its frames

        walkDownAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[0]); // First row for down
        walkLeftAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[1]); // Second row for right
        walkRightAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[2]); // Third row for left
        walkUpAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[3]); // Fourth row for up

        idleDownAnimation = new Animation<>(animation_speed, idleSpriteSheet[0][0], idleSpriteSheet[0][1]);
        idleLeftAnimation = new Animation<>(animation_speed, idleSpriteSheet[1][0], idleSpriteSheet[1][1]);
        idleRightAnimation = new Animation<>(animation_speed, idleSpriteSheet[2][0], idleSpriteSheet[2][1]);
        idleUpAnimation = new Animation<>(animation_speed, idleSpriteSheet[3][0], idleSpriteSheet[3][1]);

        currentAnimation = idleDownAnimation;
    }

    public void update(float delta) {
        boolean isMoving = false;
        // Determine if the player is moving diagonally
        boolean isMovingDiagonally = ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) || (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))) && ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) || (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)));
        // Calculate the normalised speed for diagonal movement
        double normalizedSpeed = isMovingDiagonally ? (speed / Math.sqrt(2)) * 1.07 : speed; // the 1.07 increases the diagonal speed by 7%

        // checks movement and updates animation, adjusts speed with delta time
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkUpAnimation;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkDownAnimation;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkLeftAnimation;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkRightAnimation;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            normalizedSpeed *= 2;
        } else {
            normalizedSpeed /= 2;
        }

        if (!isMoving) {
            if (currentAnimation == walkDownAnimation) currentAnimation = idleDownAnimation;
            else if (currentAnimation == walkRightAnimation) currentAnimation = idleRightAnimation;
            else if (currentAnimation == walkLeftAnimation) currentAnimation = idleLeftAnimation;
            else if (currentAnimation == walkUpAnimation) currentAnimation = idleUpAnimation;
        }

        stateTime += delta;

        game.batch.begin();

        game.batch.draw(currentAnimation.getKeyFrame(stateTime, true), x, y, character_width, character_heigth);

        game.batch.end();
    }

    // Getter for the current frame based on the state time
    public TextureRegion getCurrentFrame() {
        return currentAnimation.getKeyFrame(stateTime, true);
    }
}
