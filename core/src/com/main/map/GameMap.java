package com.main.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameMap extends TiledMap {
    private final TiledMap gameMap;
    private final OrthogonalTiledMapRenderer tiledMapRenderer;
    private final OrthographicCamera camera;

    private TiledMapTileLayer collisionLayer;
    public static final String tree_layer = "Trees";

    public GameMap() {
        // Load the .tmx with the MainMap for game
        gameMap = new TmxMapLoader().load("map/MainMap.tmx");

        // Render the MainMap
        tiledMapRenderer = new OrthogonalTiledMapRenderer(gameMap);

        // Collisions
        collisionLayer = (TiledMapTileLayer) gameMap.getLayers().get(tree_layer);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    public void render() {
        // Update the camera and set the tiledMapRenderer's view based on that camera
        camera.update();
        tiledMapRenderer.setView(camera);

        // Render the map
        tiledMapRenderer.render();
    }

    public void update(float delta) {
        // Update game logic here (e.g., player movement, camera control)
        // Make sure to update the camera if its position or zoom changes
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void dispose() {
        gameMap.dispose();
        tiledMapRenderer.dispose();
    }
}
