package me.leon.samegame.view;

import me.leon.samegame.model.GameModel;
import me.leon.samegame.model.Tile;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.PrintStream;
import java.util.List;

/**
 * The {@code SameGameConsoleView} class represents a console-based view for the SameGame application.
 * It extends {@link PrintStream} and implements the {@link GameView} interface.
 * This view displays the game grid and game information in the console on request.
 */
public class SameGameConsoleView extends PrintStream implements GameView {
    private GameModel gameModel;

    /**
     * Constructs a new {@code SameGameConsoleView} with the specified model.
     *
     * @param gameModel the game model to be associated with this console view
     */
    public SameGameConsoleView(GameModel gameModel) {
        super(System.out);
        this.gameModel = gameModel;

        initializeView();
    }

    /**
     * Initializes the console view by setting the standard output stream to this console view
     * to make use of overwritten println method.
     */
    @Override
    public void initializeView() {
        System.setOut(this);
    }

    /**
     * Updates the console view to reflect changes in the game state.
     * Prints the game grid and game information to the console.
     */
    @Override
    public void updateView() {
        // Redraw
        System.out.println("Grid:");
        Tile[][] tiles = gameModel.getTiles();
        for (int row = 0; row < gameModel.getGameRows(); row++) {
            for (int col = 0; col < gameModel.getGameCols(); col++) {
                System.out.print(tiles[row][col].toString() + " ");
            }
            System.out.println();
        }
        // Print game info
        int[] nextBestMove = gameModel.getNextBestMove();
        System.out.println("Score: " + gameModel.getPoints() + " | " + "Next best move: " + nextBestMove[0] + "," + nextBestMove[1] + " | Game Over = " + gameModel.getGameOver());
    }

    /**
     * Overrides the {@code println} method of {@link PrintStream} to prefix messages with "[SG-Logger]".
     *
     * @param s the string to be printed
     */
    @Override
    public void println(String s) {
        s = "[SG-Logger] " + s;
        super.println(s);
    }

    /**
     * Not used.
     *
     * @param listener the mouse listener to be added
     */
    @Override
    public void addGameMouseListener(MouseListener listener) {
    }

    /**
     * Not used.
     *
     * @param listener the key listener to be added
     */
    @Override
    public void addGameKeyListener(KeyListener listener) {

    }

    /**
     * Not used.
     *
     * @return null
     */
    @Override
    public JPanel getGamePanel() {
        return null;
    }

    /**
     * Displays the highscores in the console view.
     *
     * @param scores a list of highscores to be displayed
     */
    @Override
    public void showHighscores(List<Long> scores) {
        StringBuilder message = new StringBuilder();
        message.append("Highscores:\n");
        for (int i = 0; i < scores.size(); i++) {
            message.append("Game ").append(i + 1).append(": ").append(scores.get(i)).append(", ");
        }
        System.out.println("Last 3 scores: " + message.substring(0, message.length() - 2));
    }

    /**
     * Not used.
     */
    @Override
    public void removeMouseListener() {

    }
}
