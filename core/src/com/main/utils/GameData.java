package com.main.utils;

public class GameData {
    private boolean gender = true;
    private int musicLevel = 4;
    private int soundLevel = 4;

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
            this.soundLevel = musicLevel;
        }
    }
}
