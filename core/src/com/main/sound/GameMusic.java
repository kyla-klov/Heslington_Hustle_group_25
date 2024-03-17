package com.main.sound;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * The GameMusic class manages game music, including playback and volume level adjustments.
 * Initializes the game's background music and allows for dynamic volume control.
 */
public class GameMusic {

    Music audio;
    // Music levels is quarters
    private int musicLevel = 4;
    private int soundLevel = 4;

    /**
     * Constructor for GameMusic. Initializes and starts playing the game's background music at the maximum volume level.
     */
    public GameMusic() {
        audio = Gdx.audio.newMusic(Gdx.files.internal("assets/music_loop/Ludum Dare 30 - 01.ogg"));
        audio.play();
        audio.setVolume(1); // Set volume to 100%
    }

    /**
     * Returns the current music volume level.
     * @return The current music volume level.
     */
    public int getMusicLevel(){
        return this.musicLevel;
    }

    /**
     * Returns the current sound effects volume level.
     * @return The current sound effects volume level.
     */
    public int getSoundLevel(){
        return soundLevel;
    }

    /**
     * Increments the music volume level by one, if it is not already at the maximum level. Adjusts the music playback volume accordingly.
     */
    public void incrementVolume() {
        if (musicLevel <= 3){ // Check if volume is not already at maximum
            musicLevel = musicLevel + 1;
            float floatingMusicLevel = (float) musicLevel;
            audio.setVolume(floatingMusicLevel*25/100);
        }
    }

    /**
     * Decrements the music volume level by one, if it is not already at the minimum level. Adjusts the music playback volume accordingly.
     */
    public void decrementVolume() {
        if (this.musicLevel >= 1){ // Check if volume is not already at minimum
            musicLevel = musicLevel - 1;
            float floatingMusicLevel = (float) musicLevel;
            audio.setVolume(floatingMusicLevel*25/100);
        }
    }
}
