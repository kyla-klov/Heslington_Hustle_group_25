package com.main.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameMap {
    private final TiledMap tiledMap;
    private final OrthogonalTiledMapRenderer tiledMapRenderer;
    private final OrthographicCamera camera;

    public GameMap() {
        // Load the TMX map
        tiledMap = new TmxMapLoader().load("map/MainMap.tmx");

        // Initialize the map renderer
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        // Initialize your camera (assuming you have one set up)
        // This is just an example. Adjust the viewport size as necessary.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    public void render() {
        // Clear the screen or set a background color if desired

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

    public void dispose() {
        // Don't forget to dispose of the map and renderer when done
        tiledMap.dispose();
        tiledMapRenderer.dispose();
    }
}
