package com.main.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.main.map.GameMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.main.Main;
import com.main.screens.MainSettingsScreen;


public class Player extends Entity {
    Main game;
    GameMap gameMap;
    //private MainSettingsScreen settingsScreen;

    OrthographicCamera camera;
    public static final float animation_speed = 0.5f; // speed that sprite will animate or frame duration
    public static final int character_width = 24;// this is in reference to the sprite sheet
    public static final int character_height = 38;

    String boyIdleSpriteSheet = "character/boy_idle.png";
    String boyWalkSpriteSheet = "character/boy_idle.png";
    String girlIdleSpriteSheet = "character/boy_idle.png";
    String girlWalkSpriteSheet = "character/boy_idle.png";

    private float startX;
    private float startY;

    Animation<TextureRegion> walkDownAnimation, walkRightAnimation, walkLeftAnimation, walkUpAnimation;
    Animation<TextureRegion> idleDownAnimation, idleRightAnimation, idleLeftAnimation, idleUpAnimation;

    private final TiledMapTileLayer collisionLayer;

    public Player(Main game, GameMap gameMap, OrthographicCamera camera) {
        this.game = game;
        this.gameMap = gameMap;
        this.collisionLayer = gameMap.getCollisionLayer();
        this.camera = camera;
        //this.settingsScreen = settingsScreen;

        this.speed = 350;
        startX = (float) game.screenWidth /2 - (float) game.screenHeight /2;
        startY = 300;
        worldX = startX;
        worldY = startY;

        Texture idleSheet;
        Texture walkSheet;

        // here the TextureRegions' internal path can be changed with a variable for when the player chooses the gender in the settings menu
        if (game.gameData.getGender()) {
            idleSheet = new Texture("character/boy_idle.png");
            walkSheet = new Texture("character/boy_walk.png");
        } else {
            idleSheet = new Texture("character/girl_idle.png");
            walkSheet = new Texture("character/girl_walk.png");
        }

        TextureRegion[][] idleSpriteSheet = TextureRegion.split(idleSheet, character_width, character_height); // Splits the sprite sheet up by its frames
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

        if (worldX + character_width > gameMap.getWidth()){
            worldX = gameMap.getWidth() - character_width;
        }
        else if (worldX < 0){
            worldX = 0;
        }
        if (worldY + character_height > gameMap.getHeight()){
            worldY = gameMap.getHeight() - character_height;
        }
        else if (worldY < 0){
            worldY = 0;
        }

        float camX = worldX + character_width/2f;
        float camY = worldY + character_height/2f;

        camera.position.set(camX, camY, 0);

        if (camX + camera.viewportWidth/2f > gameMap.getWidth()) {
            camera.position.set(gameMap.getWidth() - camera.viewportWidth/2f, camera.position.y, 0);
        }
        else if (camX - camera.viewportWidth/2f < 0){
            camera.position.set(camera.viewportWidth/2f, camera.position.y, 0);
        }
        if (camY + camera.viewportHeight/2f > gameMap.getHeight()) {
            camera.position.set(camera.position.x, gameMap.getHeight() - camera.viewportHeight/2f, 0);
        }
        else if (camY - camera.viewportHeight/2f < 0){
            camera.position.set(camera.position.x, camera.viewportHeight/2f, 0);
        }

        camera.update();

        //game.batch.begin();

       // game.batch.draw(currentAnimation.getKeyFrame(stateTime, true), worldX, worldY, character_width, character_height);

        //game.batch.end();
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

    public Vector2 getStartPos(){
        return new Vector2(startX, startY);
    }

    // Getter for the current frame based on the state time
    public TextureRegion getCurrentFrame() {
        return currentAnimation.getKeyFrame(stateTime, true);
    }

}
