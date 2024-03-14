package com.main.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.main.entity.Player;

public class GameMap extends TiledMap {
    private int width;
    private int height;
    private final TiledMap gameMap;
    private final OrthogonalTiledMapRenderer tiledMapRenderer;
    private final OrthographicCamera camera;
    // maybe for map animations
    // AnimatedTiledMapTile animatedTiledMapTile;

    private Player player;

    private TiledMapTileLayer collisionLayer;
    public static final String tree_layer = "Trees";

    public GameMap(OrthographicCamera camera) {
        // Load the .tmx with the MainMap for game
        gameMap = new TmxMapLoader().load("map/MainMap.tmx");
        MapProperties properties = gameMap.getProperties();
        height = properties.get("tileheight", Integer.class) * properties.get("height", Integer.class);
        width = properties.get("tilewidth", Integer.class) * properties.get("width", Integer.class);

        // Render the MainMap
        tiledMapRenderer = new OrthogonalTiledMapRenderer(gameMap);

        // Collisions
        collisionLayer = (TiledMapTileLayer) gameMap.getLayers().get(tree_layer);

        this.camera = camera;
    }

    public void render() {
        // Update the camera and set the tiledMapRenderer's view based on that camera
        camera.update();
        tiledMapRenderer.setView(camera);

        // Render the map
        tiledMapRenderer.render();
    }

    public void update(float delta) {
        // Update map logic here (e.g. map animations)
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public TiledMap getMap(){
        return gameMap;
    }

    public void dispose() {
        gameMap.dispose();
        tiledMapRenderer.dispose();
    }
}
