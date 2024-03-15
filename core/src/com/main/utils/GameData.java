package com.main.utils;

import com.main.sound.GameMusic;

public class GameData {
    private boolean gender = true;
    private float playerPosX = 300; //For future use
    private float playerPosY = 300; //For future use

    GameMusic music;

    public GameData(){
        this.music = new GameMusic();
    }

    public boolean getGender(){
        return gender;
    }

    public void setGender(boolean gender){
        this.gender = gender;
    }

    public int getSoundLevel(){
        return music.getSoundLevel();
    }

    public int getMusicLevel(){
        return music.getMusicLevel();
    }

    public void incrementMusicLevel() {
        music.incrementVolume();
    }

    public void decrementMusicLevel(){
        music.decrementVolume();
    }

    public float getPlayerPosX(){
        return playerPosX;
    }

    public void setPlayerPosX(float x){
        playerPosX = x;
    }

    public float getPlayerPosY(){
        return playerPosY;
    }

    public void setPlayerPosY(float y){
        playerPosY = y;
    }
}
