package com.main.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.main.map.GameMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.main.Main;


public class Player extends Entity {
    Main game;

    public static final float animation_speed = 0.5f; // speed that sprite will animate or frame duration
    public static final int character_width = 24;// this is in reference to the sprite sheet
    public static final int character_height = 38;

    Animation<TextureRegion> walkDownAnimation, walkRightAnimation, walkLeftAnimation, walkUpAnimation;
    Animation<TextureRegion> idleDownAnimation, idleRightAnimation, idleLeftAnimation, idleUpAnimation;

    private final TiledMapTileLayer collisionLayer;

    public Player(String spriteSheetPath, Main game, GameMap gameMap) {
        this.game = game;
        this.collisionLayer = gameMap.getCollisionLayer();
        this.speed = 350;
        worldY = 15;
        worldX = (float) game.screenWidth /2 - (float) game.screenHeight /2;

        // here the TextureRegions' internal path can be changed with a variable for when the player chooses the gender
        Texture idleSheet = new Texture("character/boy_idle.png");
        TextureRegion[][] idleSpriteSheet = TextureRegion.split(idleSheet, character_width, character_height); // Splits the sprite sheet up by its frames

        Texture walkSheet = new Texture("character/boy_walk.png");
        TextureRegion[][] walkSpriteSheet = TextureRegion.split(walkSheet, character_width, character_height); // Splits the sprite sheet up by its frames

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
        boolean collisionX = false, collisionY = false;

        // Determine if the player is moving diagonally
        boolean isMovingDiagonally = ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) ||
                        (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))) &&
                        ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) ||
                        (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)));
        // Calculate the normalised speed for diagonal movement
        double normalizedSpeed = speed;
        if (isMovingDiagonally) {
            normalizedSpeed = (speed / Math.sqrt(2)) * 1.07; // Adjust speed for diagonal movement
        }
        // shift key doubles player speed
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            normalizedSpeed *= 2; // Increase speed if shift is pressed
        }

        // checks movement and updates animation, adjusts speed with delta time
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            worldY += (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkUpAnimation;
            isMoving = true;
            // top left tile


            // top middle tile

            // top right tile
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            worldY -= (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkDownAnimation;
            isMoving = true;
            // bottom left tile

            // bottom middle tile

            // bottom right tile
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            worldX -= (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkLeftAnimation;
            isMoving = true;
            // top left tile
            //collisionX = collisionLayer.getCell(worldX,worldY);

            // middle left tile

            // bottom left tile
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            worldX += (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkRightAnimation;
            isMoving = true;
            // top right tile

            // middle right tile

            // bottom right tile
        }

        if (!isMoving) {
            if (currentAnimation == walkDownAnimation) currentAnimation = idleDownAnimation;
            else if (currentAnimation == walkRightAnimation) currentAnimation = idleRightAnimation;
            else if (currentAnimation == walkLeftAnimation) currentAnimation = idleLeftAnimation;
            else if (currentAnimation == walkUpAnimation) currentAnimation = idleUpAnimation;
        }

        stateTime += delta;

        game.batch.begin();

        game.batch.draw(currentAnimation.getKeyFrame(stateTime, true), worldX, worldY, character_width, character_height);

        game.batch.end();
    }

    public boolean collidesWith(Texture thing, float thingX, float thingY) {
        Rectangle playerBounds = new Rectangle(worldX, worldY, character_width, character_height);
        Rectangle objectBounds = new Rectangle(thingX, thingY, thing.getWidth(), thing.getHeight()); // Adjust this according to your hit object's position and size
        return playerBounds.overlaps(objectBounds);
    }

    public void setPos(float newX, float newY) {
        worldX = newX;
        worldY = newY;
    }

    // Getter for the current frame based on the state time
    public TextureRegion getCurrentFrame() {
        return currentAnimation.getKeyFrame(stateTime, true);
    }
}
