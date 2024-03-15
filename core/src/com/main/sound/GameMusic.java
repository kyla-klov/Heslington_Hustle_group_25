package com.main.sound;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GameMusic {

    Music audio;
    private int musicLevel = 4;
    private int soundLevel = 4;

    public GameMusic() {
        audio = Gdx.audio.newMusic(Gdx.files.internal("assets/music_loop/Ludum Dare 30 - 01.ogg"));
        audio.play();
        audio.setVolume(1);
    }

    public int getMusicLevel(){
        return this.musicLevel;
    }

    public int getSoundLevel(){
        return soundLevel;
    }

    public void incrementVolume() {
        if (musicLevel <= 3){
            musicLevel = musicLevel + 1;
            float floatingMusicLevel = (float) musicLevel;
            audio.setVolume(floatingMusicLevel*25/100);
        }
    }

    public void decrementVolume() {
        if (this.musicLevel >= 1){
            musicLevel = musicLevel - 1;
            float floatingMusicLevel = (float) musicLevel;
            audio.setVolume(floatingMusicLevel*25/100);
        }
    }


}
