package me.leon.samegame.model;

/**
 * The {@code Difficulty} interface defines constant values representing
 * the different difficulty levels for SameGame.
 * The difficulty levels determine how many different colors are within SameGame:
 * <ul>
 *  <li>{@link #EASY} - Easy difficulty level with 3 colors.</li>
 *  <li>{@link #MEDIUM} - Medium difficulty level with 4 colors.</li>
 *  <li>{@link #HARD} - Hard difficulty level with 5 colors.</li>
 * </ul>
 */
public interface Difficulty {
    public static final int EASY = 3;

    public static final int MEDIUM = 4;

    public static final int HARD = 5;
}
