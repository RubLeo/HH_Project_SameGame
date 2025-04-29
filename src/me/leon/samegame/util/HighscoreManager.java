package me.leon.samegame.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code HighscoreManager} class manages the highscores for the game.
 * It provides functionality to add, retrieve, save, and load high scores from this class using Serialization.
 * This class is inspired by Horstmann Cay's "Object-Oriented Design and Patterns", chapter 7.5.
 */
public class HighscoreManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Long> highscores;

    /**
     * Constructs a new {@code HighscoreManager} with an initial high score of 408.
     */
    public HighscoreManager() {
        this.highscores = new ArrayList<>();
        highscores.add(408L);
    }

    /**
     * Adds a new score to the list of highscores.
     *
     * @param score score to be added
     */
    public void addScore(long score) {
        highscores.add(score);
    }

    /**
     * Retrieves the list of highscores.
     *
     * @return copy of the list of highscores to prevent external modification
     */
    public List<Long> getHighscores() {
        return new ArrayList<>(highscores); // Return a copy to prevent external modification
    }

    /**
     * Saves the current highscores object to a file using serialization.
     *
     * @param filename name of the file to save the serialized highscores to
     */
    public void saveHighscores(String filename) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            System.err.println("Problem with trying to write the highscore file to the system.");
        }
    }

    /**
     * Loads the highscores from a file.
     *
     * @param filename name of the file to load the high scores from (serialized Highscores object)
     * @return {@code HighscoreManager} instance containing the loaded highscores,
     * or a new instance if the file does not exist or cannot be read
     */
    public static HighscoreManager loadHighscores(String filename) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            return (HighscoreManager) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Probably no highscores have been saved yet, a new file will be created.");
            return new HighscoreManager(); // Returns new instance after first gameplay, or deletion of highscores.dat file
        }
    }
}
