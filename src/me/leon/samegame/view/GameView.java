package me.leon.samegame.view;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * The {@code GameView} interface defines the contract for a view in a game.
 * A view is responsible for presenting the game state to the user and handling user interactions.
 */
public interface GameView {

    /**
     * Initializes the view, setting up the initial display and layout of the game components.
     */
    void initializeView();

    /**
     * Updates the view to reflect changes in the game state.
     */
    void updateView();

    /**
     * Adds a {@link MouseListener} to the game view to handle mouse events.
     *
     * @param listener the mouse listener to be added
     */
    void addGameMouseListener(MouseListener listener);

    /**
     * Adds a {@link KeyListener} to the game view to handle key events.
     *
     * @param listener the key listener to be added
     */
    void addGameKeyListener(KeyListener listener);

    /**
     * Displays the high scores in the view.
     *
     * @param scores a list of high scores to be displayed
     */
    void showHighscores(List<Long> scores);

    /**
     * Retrieves the main game panel of the view.
     *
     * @return the main game panel
     */
    JPanel getGamePanel();

    /**
     * Remove MouseListener when gameOver.
     */
    void removeMouseListener();
}
