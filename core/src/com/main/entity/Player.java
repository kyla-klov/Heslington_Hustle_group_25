package com.main.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.main.map.GameMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.main.Main;
import com.main.utils.CollisionHandler;


public class Player extends Entity {
    Main game;
    GameMap gameMap;
    TiledMapTileLayer collisionLayer;
    OrthographicCamera camera;
    CollisionHandler collisionHandler;
    int tileSize = 16;

    char dir;

    public static final float animation_speed = 0.5f; // speed that sprite will animate or frame duration
    public static final int spriteX = 24;// this is in reference to the sprite sheet
    public static final int spriteY = 38;

    String boyIdleSpriteSheet = "character/boy_idle.png";
    String boyWalkSpriteSheet = "character/boy_idle.png";
    String girlIdleSpriteSheet = "character/boy_idle.png";
    String girlWalkSpriteSheet = "character/boy_idle.png";

    public float startX;
    public float startY;

    Texture idleSheet;
    Texture walkSheet;

    Animation<TextureRegion> walkDownAnimation, walkRightAnimation, walkLeftAnimation, walkUpAnimation;
    Animation<TextureRegion> idleDownAnimation, idleRightAnimation, idleLeftAnimation, idleUpAnimation;

    public Player(Main game, GameMap gameMap, OrthographicCamera camera) {
        this.game = game;
        this.gameMap = gameMap;
        this.collisionLayer = (TiledMapTileLayer)gameMap.getMap().getLayers().get("Trees");
        this.camera = camera;
        this.collisionHandler = new CollisionHandler(gameMap.getMap(), tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);
        this.collisionHandler.addCollisionLayers("Trees");
        //this.settingsScreen = settingsScreen;

        this.speed = 200;
        startX = (float) game.screenWidth /2 - (float) game.screenHeight /2;
        startY = 500;
        worldX = startX;
        worldY = startY;

        dir = 'D';
        updateGender();
    }

    public void update(float delta) {
        boolean isMoving = false;
        boolean collisionX = false, collisionY = false;
        // Store the original position
        float originalX = worldX;
        float originalY = worldY;
        int tileX = (int) (worldX / tileSize);
        int tileY = (int) (worldY / tileSize);
        TiledMapTileLayer.Cell cell = null;

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
        /*
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            worldY += (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkUpAnimation;
            dir = 'U';
            isMoving = true;
            // check collision top left tile
            cell = collisionLayer.getCell( (int) worldX / tileX, (int) (worldY + tileY) / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionY = true;
            }
            // check collision top middle tile
            cell = collisionLayer.getCell( (int) ((worldX + tileX) / 2) / tileX, (int) (worldY + tileY) / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionY = true;
            }
            // check collision top right tile
            cell = collisionLayer.getCell( (int) (worldX + tileX) / tileX, (int) (worldY + tileY) / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionY = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            worldY -= (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkDownAnimation;
            dir = 'D';
            isMoving = true;
            // check collision bottom left tile
            cell = collisionLayer.getCell( (int) worldX / tileX, (int) worldY / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionY = true;
            }
            // check collision bottom middle tile
            cell = collisionLayer.getCell( (int) (worldX + tileX) / 2, (int) worldY / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionY = true;
            }
            // check collision bottom right tile
            cell = collisionLayer.getCell( (int) (worldX + tileX) / tileX, (int) worldY / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionY = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            worldX -= (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkLeftAnimation;
            dir = 'L';
            isMoving = true;
            // check collision top left tile
            cell = collisionLayer.getCell( (int) worldX / tileX, (int) (worldY + tileY) / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionX = true;
            }
            // check collision middle left tile
            cell = collisionLayer.getCell((int) worldX / tileX, (int) ((worldY + tileY) / 2) / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionX = true;
            }
            // check collision bottom left tile
            cell = collisionLayer.getCell( (int) worldX / tileX, (int) (worldY / tileY));
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionX = true;
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            worldX += (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkRightAnimation;
            dir = 'R';
            isMoving = true;
            // check collision top right tile
            cell = collisionLayer.getCell( (int) (worldX + tileX) / tileX, (int) (worldY + tileY) / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionX = true;
            }
            // check collision middle right tile
            cell = collisionLayer.getCell( (int) (worldX + tileX) / tileX, (int) ((worldY + tileY) / 2) / tileY);
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionX = true;
            }
            // check collision bottom right tile
            cell = collisionLayer.getCell( (int) (worldX + tileX) / tileX, (int) (worldY / tileY));
            if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked")) {
                collisionX = true;
            }
        }
         */
        float targX = worldX;
        float targY = worldY;

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            targY = worldY + (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkUpAnimation;
            dir = 'U';
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            targY = worldY - (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkDownAnimation;
            dir = 'D';
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            targX = worldX - (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkLeftAnimation;
            dir = 'L';
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            targX = worldX + (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkRightAnimation;
            dir = 'R';
            isMoving = true;
        }

        if (targX + spriteX > gameMap.getWidth()){
            targX = gameMap.getWidth() - spriteX;
        }
        else if (targX < 0){
            targX = 0;
        }
        if (targY + spriteY > gameMap.getHeight()){
            targY = gameMap.getHeight() - spriteY;
        }
        else if (targY < 0){
            targY = 0;
        }

        // this will switch sprite sheet to idle sprite sheet when player is not moving
        if (!isMoving) {
            if (currentAnimation == walkDownAnimation) currentAnimation = idleDownAnimation;
            else if (currentAnimation == walkRightAnimation) currentAnimation = idleRightAnimation;
            else if (currentAnimation == walkLeftAnimation) currentAnimation = idleLeftAnimation;
            else if (currentAnimation == walkUpAnimation) currentAnimation = idleUpAnimation;
        }

        Vector2 newPos = collisionHandler.adjustPos(worldX, worldY, targX, targY);
        if (newPos != null){
            worldX = newPos.x;
            worldY = newPos.y;
        }
        else{
            worldX = targX;
            worldY = targY;
        }

        stateTime += delta;

        float camX = worldX + spriteY /2f;
        float camY = worldY + spriteY /2f;

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
        // game.batch.begin();

        // game.batch.draw(currentAnimation.getKeyFrame(stateTime, true), worldX, worldY, character_width, character_height);

        // game.batch.end();
    }

    /*
    public boolean collidesWith(Texture thing, float thingX, float thingY) {
        Rectangle playerBounds = new Rectangle(worldX, worldY, spriteX, spriteY);
        Rectangle objectBounds = new Rectangle(thingX, thingY, thing.getWidth(), thing.getHeight());
        return playerBounds.overlaps(objectBounds);
    }

     */

    public void setPos(float newX, float newY) {
        worldX = newX;
        worldY = newY;
    }

    public Vector2 getStartPos(){
        return new Vector2(startX, startY);
    }

    // collision detection method that checks if the tile is not empty and a collision tile
    private boolean isCollision(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / tileSize), (int) (y / tileSize));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }


    // here the TextureRegions' internal path can be changed with a variable for when the player chooses the gender in the settings menu
    public void updateGender(){
        if (idleSheet != null) {idleSheet.dispose();}
        if (walkSheet != null) {walkSheet.dispose();}
        if (game.gameData.getGender()) {
            idleSheet = new Texture("character/boy_idle.png");
            walkSheet = new Texture("character/boy_walk.png");
        } else {
            idleSheet = new Texture("character/girl_idle.png");
            walkSheet = new Texture("character/girl_walk.png");
        }

        TextureRegion[][] idleSpriteSheet = TextureRegion.split(idleSheet, spriteX, spriteY); // Splits the sprite sheet up by its frames
        TextureRegion[][] walkSpriteSheet = TextureRegion.split(walkSheet, spriteX, spriteY); // Splits the sprite sheet up by its frames

        walkDownAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[0]); // First row for down
        walkLeftAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[1]); // Second row for right
        walkRightAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[2]); // Third row for left
        walkUpAnimation = new Animation<TextureRegion>(animation_speed, walkSpriteSheet[3]); // Fourth row for up

        idleDownAnimation = new Animation<>(animation_speed, idleSpriteSheet[0][0], idleSpriteSheet[0][1]);
        idleLeftAnimation = new Animation<>(animation_speed, idleSpriteSheet[1][0], idleSpriteSheet[1][1]);
        idleRightAnimation = new Animation<>(animation_speed, idleSpriteSheet[2][0], idleSpriteSheet[2][1]);
        idleUpAnimation = new Animation<>(animation_speed, idleSpriteSheet[3][0], idleSpriteSheet[3][1]);

        switch (dir){
            case 'D':
                currentAnimation = idleDownAnimation;
                break;
            case 'L':
                currentAnimation = idleLeftAnimation;
                break;
            case 'R':
                currentAnimation = idleRightAnimation;
                break;
            case 'U':
                currentAnimation = idleUpAnimation;
                break;
        }
    }

    // Getter for the current frame based on the state time
    public TextureRegion getCurrentFrame() {
        return currentAnimation.getKeyFrame(stateTime, true);
    }


    public void dispose(){
        idleSheet.dispose();
        walkSheet.dispose();
    }
}
