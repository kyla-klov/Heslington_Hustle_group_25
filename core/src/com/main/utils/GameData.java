package com.main.utils;

public class GameData {
    private boolean gender = true;
    private int musicLevel = 4;
    private int soundLevel = 4;

    private float playerPosX = 300; //For future use
    private float playerPosY = 300; //For future use

    public boolean getGender(){
        return gender;
    }

    public void setGender(boolean gender){
        this.gender = gender;
    }

    public int getMusicLevel(){
        return musicLevel;
    }

    public void setMusicLevel(int musicLevel) {
        if (musicLevel >= 0 && musicLevel <= 4) {
            this.musicLevel = musicLevel;
        }
    }

    public int getSoundLevel(){
        return soundLevel;
    }

    public void setSoundLevel(int soundLevel) {
        if (soundLevel >= 0 && soundLevel <= 4) {
            this.soundLevel = soundLevel;
        }
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
