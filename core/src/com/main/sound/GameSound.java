package com.main.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GameSound {
    Music upSound;
    Music downSound;
    Music buttonClickedSound;
    private int soundLevel = 4;

    public GameSound(){
        upSound = Gdx.audio.newMusic(Gdx.files.internal("assets/sfx/high_note.mp3"));
        downSound = Gdx.audio.newMusic(Gdx.files.internal("assets/sfx/low_note.mp3"));
        buttonClickedSound = Gdx.audio.newMusic(Gdx.files.internal("assets/sfx/button_press.mp3"));
    }

    public int getSoundLevel(){
        return this.soundLevel;
    }

    public void upSoundActivate(){
        if (upSound.isPlaying()){
            upSound.stop();
        }
        upSound.play();
    }

    public void downSoundActivate(){
        if (downSound.isPlaying()){
            downSound.stop();
            downSound.play();
        }
        downSound.play();
    }

    public void buttonClickedSoundActivate(){
        if (buttonClickedSound.isPlaying()){
            buttonClickedSound.stop();
        }
        buttonClickedSound.play();
    }

    public void incrementVolume() {
        if (soundLevel <= 3){ // Check if volume is not already at maximum
            soundLevel = soundLevel + 1;
            float floatingMusicLevel = (float) soundLevel;
            upSound.setVolume(floatingMusicLevel*25/100);
            downSound.setVolume(floatingMusicLevel*25/100);
            buttonClickedSound.setVolume(floatingMusicLevel*25/100);
        }
    }

    /**
     * Decrements the music volume level by one, if it is not already at the minimum level. Adjusts the music playback volume accordingly.
     */
    public void decrementVolume() {
        if (this.soundLevel >= 1) { // Check if volume is not already at minimum
            soundLevel = soundLevel - 1;
            float floatingMusicLevel = (float) soundLevel;
            upSound.setVolume(floatingMusicLevel*25/100);
            downSound.setVolume(floatingMusicLevel*25/100);
            buttonClickedSound.setVolume(floatingMusicLevel*25/100);
        }
    }


}


