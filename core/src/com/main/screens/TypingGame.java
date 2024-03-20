package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.main.Main;
import com.main.utils.ScreenType;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The TypingGame class implements a mini-game for the player to increase their study hours.
 * Players are shown a number that they need to memorize and then type it correctly to succeed.
 */
public class TypingGame implements Screen, InputProcessor {
    private final Main game;
    private int studyDuration, attempts = 0, currentNumber = 0, correct = 0, startingNumLength = 5;
    private Texture guessButton;
    private String userGuess = "";
    Boolean acceptInput = false, displayCorrect = false, displayWrong = false;
    BitmapFont displayText;
    private float displayTextX, displayTextY, displayTextWidth = 500, displayTextHeight = 100;
    private final float guessButtonX, guessButtonY, guessButtonWidth = 156, guessButtonHeight = 84;
    private Texture title;
    private float titleX, titleY, titleWidth = 74, titleHeight = 14;
    String gameObjective;

    /**
     * Constructs a TypingGame screen with the game instance and study duration.
     *
     * @param game The main game instance.
     * @param studyDuration The duration of the study session in attempts.
     */
    public TypingGame(Main game, int studyDuration){
        displayText = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        guessButton = new Texture("assets/mini_games/guess_button.png");
        title = new Texture("assets/mini_games/number_memoriser_label.png");
        displayText.getData().setScale(4f);

        displayTextX = (game.screenWidth - displayTextWidth)/2;
        displayTextY = (game.screenHeight - displayTextHeight)/2;
        guessButtonX = (game.screenWidth - guessButtonWidth)/2;
        guessButtonY = (game.screenHeight - guessButtonHeight)/2 - 300;
        displayTextX = displayTextX * game.scaleFactorX;
        displayTextY = displayTextY * game.scaleFactorY;
        titleWidth = title.getWidth() * game.scaleFactorX * 11;
        titleHeight = title.getHeight() * game.scaleFactorY * 11;
        titleX = (game.screenWidth - titleWidth)/2;
        titleY = (game.screenHeight - titleHeight)/2+500;

        Gdx.input.setInputProcessor(this);
        this.game = game;
        this.studyDuration = studyDuration;

        gameObjective = "Remember the number given and try to input the number from memory";

        playGame();
    }

    /**
     * Begins a new challenge by generating a number for the player to memorize.
     * Handles the logic for correct and incorrect guesses and progresses the game.
     */
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

    /**
     * Allows the player to input their guess after a short delay.
     */
    public void makeUserGuess(){
        acceptInput = true;
    }

    /**
     * Implements a delay before executing a given runnable task.
     *
     * @param seconds The delay in seconds before running the task.
     * @param runnable The task to execute after the delay.
     */
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
        game.batch.draw(title, titleX, titleY, titleWidth, titleHeight);
        displayText.draw(game.batch, gameObjective, game.screenWidth / 4f, game.screenHeight - 280, game.screenWidth / 2f, Align.center, false);
        
        if (acceptInput){
            displayText.draw(game.batch, userGuess, 0, displayTextY + displayTextHeight / 2, game.screenWidth, Align.center, false);
            game.batch.draw(guessButton, guessButtonX, guessButtonY, guessButtonWidth, guessButtonHeight);
        } else if (displayCorrect){
            displayText.draw(game.batch, "Correct well done.", 0, displayTextY + displayTextHeight / 2, game.screenWidth, Align.center, false);
        } else if (displayWrong) {
            displayText.draw(game.batch, "Incorrect. Answer: " + currentNumber, 0, displayTextY + displayTextHeight / 2, game.screenWidth, Align.center, false);
        } else {
            displayText.draw(game.batch, String.valueOf(currentNumber), 0, displayTextY + displayTextHeight / 2, game.screenWidth, Align.center, false);
        }
        game.batch.end();
    }

    /**
     * Generates a random number for the player to memorize.
     *
     * @return The generated number.
     */
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
        guessButton.dispose();
        title.dispose();
        displayText.dispose();
    }

    @Override
    public boolean keyDown(int keycode){
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (acceptInput) {
            if (character == '\b' && userGuess.length() > 0) { // Handles backspace
                userGuess = userGuess.substring(0, userGuess.length() - 1);
            } else if (Character.isDigit(character) && userGuess.length() < String.valueOf(currentNumber).length()) {
                userGuess += character;
            }
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float worldX = screenX;
        float worldY = game.screenHeight - screenY;

        if (worldX >= guessButtonX && worldX <= guessButtonX + guessButtonWidth &&
                worldY >= guessButtonY && worldY <= guessButtonY + guessButtonHeight) {

            if (userGuess != ""){
                acceptInput = false;
                if (Integer.parseInt(userGuess) == currentNumber){
                    correct = correct + 1;
                    displayCorrect = true;
                } else {
                    displayWrong = true;
                }
                delay(2, this::playGame);
            }
            
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
