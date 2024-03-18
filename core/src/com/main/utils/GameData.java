package com.main.utils;

import com.badlogic.gdx.Game;
import com.main.sound.GameMusic;
import com.main.sound.GameSound;

/**
 * Stores game settings and data.
 */
public class GameData {
    private boolean gender = true;
    private float playerPosX = 300;
    private float playerPosY = 300;
    GameMusic music;
    GameSound sound;

    /**
     * Constructor initializes the game music settings.
     */
    public GameData(){

        this.music = new GameMusic();
        this.sound = new GameSound();
    }

    /**
     * Returns the player's gender selection.
     *
     * @return A boolean representing the gender, true for male, false for female.
     */
    public boolean getGender(){
        return gender;
    }

    /**
     * Sets the player's gender preference.
     *
     * @param gender A boolean, true for male and false for female.
     */
    public void setGender(boolean gender){
        this.gender = gender;
    }

    /**
     * Gets the current sound level setting.
     *
     * @return The sound level as an integer.
     */
    public int getSoundLevel(){
        return sound.getSoundLevel();
    }

    /**
     * Gets the current music level setting.
     *
     * @return The music level as an integer.
     */
    public int getMusicLevel() {
        return music.getMusicLevel();
    }
    /**
     * Increments the music volume level if not at maximum.
     */
    public void incrementMusicLevel() {
        music.incrementVolume();
    }

    /**
     * Decrements the music volume level if not at minimum.
     */
    public void decrementMusicLevel(){
        music.decrementVolume();
    }

    public void incrementSoundLevel(){ sound.incrementVolume(); }

    public void decrementSoundLevel(){ sound.decrementVolume(); }

    public void upSoundActivate(){ sound.upSoundActivate(); }

    public void downSoundActivate(){ sound.downSoundActivate(); }

    public void buttonClickedSoundActivate(){ sound.buttonClickedSoundActivate(); }

    /**
     * Retrieves the player's current X position.
     *
     * @return The X-coordinate of the player.
     */
    public float getPlayerPosX(){
        return playerPosX;
    }

    /**
     * Sets the player's X position to a specified value.
     *
     * @param x The X-coordinate to set for the player.
     */
    public void setPlayerPosX(float x){
        playerPosX = x;
    }

    /**
     * Retrieves the player's current Y position.
     *
     * @return The Y-coordinate of the player.
     */
    public float getPlayerPosY(){
        return playerPosY;
    }

    /**
     * Sets the player's Y position to a specified value.
     *
     * @param y The Y-coordinate to set for the player.
     */
    public void setPlayerPosY(float y){
        playerPosY = y;
    }
}
