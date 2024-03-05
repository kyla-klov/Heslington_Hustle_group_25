package com.main.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;

import java.util.HashMap;
import java.util.Map;

public class MainControlScreen implements Screen {

    Main game;
    private Map<String, String> controlMapping = new HashMap<String, String>();

    public MainControlScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 1, 1);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void addControls(){
        addControl("W", "Up");
        addControl("A", "Left");
        addControl("S", "Right");
        addControl("D", "Down");
    }

    public void addControl(String key, String action) throws IllegalArgumentException{
        if (controlMapping.containsKey(key)){
            throw new IllegalArgumentException("binding already exists");
        } else {
            controlMapping.put(key, action);
        }
    }

}
