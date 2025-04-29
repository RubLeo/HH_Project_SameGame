package me.leon.samegame.util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * A singleton class responsible for managing sound effects in the SameGame application.
 * Sounds are played when a tile is clicked to remove a block or when whitespace is clicked.
 */
public class SoundManager {
    private static SoundManager instance;
    private Map<String, Clip> sounds;

    /**
     * Private constructor to initialize the SoundManager.
     * Loads sound files for "click_empty" and "click_tile".
     */
    private SoundManager() {
        sounds = new HashMap<>();
        loadSound("click_empty", "ress/click_empty.wav");
        loadSound("click_tile", "ress/click_tile.wav");
    }

    /**
     * Loads a sound file into the sounds map.
     *
     * @param name      the name/key of the sound
     * @param soundFile the file path of the sound file
     */
    private void loadSound(String name, String soundFile) {
        try {
            URL url = getClass().getResource(soundFile);
            if (url == null) {
                System.err.println("Sound file not found: " + soundFile);
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            sounds.put(name, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Some unforeseen error occurred: " + e);
        }
    }

    /**
     * Plays the sound associated with the given name.
     *
     * @param soundName the name of the sound to be played
     */
    public void playSound(String soundName) {
        Clip clip = sounds.get(soundName);
        try {
            if (clip.isRunning()) {
                clip.stop(); // Stop the clip if it is still running
            }
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start();
        } catch (NullPointerException e) {
            System.err.println("Sound not found: " + soundName);
        }
    }

    /**
     * Retrieves the singleton instance of the SoundManager.
     *
     * @return the singleton instance of SoundManager
     */
    public static SoundManager get() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }
}
