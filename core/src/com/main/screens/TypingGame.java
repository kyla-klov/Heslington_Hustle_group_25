package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.main.Main;
import com.main.utils.ScreenType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TypingGame implements Screen, InputProcessor {
    private final Main game;
    private int studyDuration, attempts = 0, currentNumber = 0, correct = 0, startingNumLength = 5;
    private Texture makeGuess;
    private String userGuess = "";
    Boolean acceptInput = false, displayCorrect = false, displayWrong = false;
    BitmapFont displayText;
    private float displayTextX, displayTextY, displayTextWidth = 100, displayTextHeight = 100;
    private float makeGuessX, makeGuessY, makeGuessWidth = 100, makeGuessHeight = 100;

    public TypingGame(Main game, int studyDuration){
        displayText = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        makeGuess = new Texture("assets/menu_buttons/continue_button.png");

        displayTextX = (game.screenWidth - displayTextWidth)/2;
        displayTextY = (game.screenHeight - displayTextHeight)/2;
        makeGuessX = (game.screenWidth - makeGuessWidth)/2;
        makeGuessY = (game.screenHeight - makeGuessHeight)/2 - 300;


        Gdx.input.setInputProcessor(this);
        this.game = game;
        this.studyDuration = studyDuration;

        playGame();
    }

    public void playGame(){
        userGuess = "";
        displayWrong = false;
        displayCorrect = false;
        if (attempts < studyDuration){
            currentNumber = generateNumber();
            delay(5, this::makeUserGuess);
        } else {
            game.screenManager.setScreen(ScreenType.GAME_SCREEN);
        }


    }

    public void makeUserGuess(){
        acceptInput = true;
    }

    public void delay(int seconds, Runnable runnable){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                runnable.run();
            }
        }, seconds);
    }
  

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.begin();
        
        if (acceptInput){
            displayText.draw(game.batch, userGuess, displayTextX, displayTextY);
            game.batch.draw(makeGuess, makeGuessX, makeGuessY, makeGuessWidth, makeGuessHeight);
        } else if (displayCorrect){
            displayText.draw(game.batch, "Correct", displayTextX, displayTextY);
        } else if (displayWrong) {
            displayText.draw(game.batch, "Wrong", displayTextX, displayTextY);
        } else {
            displayText.draw(game.batch, Integer.toString(currentNumber), displayTextX, displayTextY);
        }


        game.batch.end();
    }

    public int generateNumber(){
        int startingNum = (int) (10*Math.pow(10, startingNumLength-1));
        int lowerLimit = (int) (startingNum*Math.pow(10, attempts-1));
        int num = ThreadLocalRandom.current().nextInt(lowerLimit, lowerLimit*10-1);
        attempts++;
        return num;
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

    @Override
    public boolean keyDown(int keycode){

        if (acceptInput){
            char character = Input.Keys.toString(keycode).charAt(0);
            if (Character.isDigit(character) && userGuess.length() < String.valueOf(currentNumber).length()){
                String x = String.valueOf(character);
                userGuess = userGuess + x;
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float worldX = screenX;
        float worldY = game.screenHeight - screenY;

        if (worldX >= makeGuessX && worldX <= makeGuessX + makeGuessWidth &&
                worldY >= makeGuessY && worldY <= makeGuessY + makeGuessHeight) {
            acceptInput = false;

            if (Integer.parseInt(userGuess) == currentNumber){
                correct = correct + 1;
                displayCorrect = true;
            } else {
                displayWrong = true;
            }
            delay(2, this::playGame);
            
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
