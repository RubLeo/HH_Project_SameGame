package me.leon.samegame.view;

import me.leon.samegame.model.GameModel;
import me.leon.samegame.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Represents the GUI view for the SameGame application.
 * Extends {@link JFrame} and implements {@link GameView} interface.
 */
public class SameGameView extends JFrame implements GameView {
    private GameModel gameModel;
    private JPanel gamePanel;
    private JLabel infoLabel;
    private DecimalFormat decimalFormat;

    /**
     * Constructor to initialize the SameGameView.
     *
     * @param gameModel the GameModel instance associated with the view
     */
    public SameGameView(GameModel gameModel) {
        this.gameModel = gameModel;
        this.decimalFormat = new DecimalFormat("#,###");

        setTitle("SameGame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        infoLabel = new JLabel("Score: 0");
        infoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(infoLabel, BorderLayout.NORTH);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(gameModel.getGameRows(), gameModel.getGameCols()));
        add(gamePanel, BorderLayout.CENTER);

        initializeView();

        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initializes the view by updating its components.
     */
    @Override
    public void initializeView() {
        updateView();
    }

    /**
     * Updates the view by redrawing the game panel and updating the information label.<br>
     *
     * <u>Future Work</u>: Very jiggly - only update views objects where necessary based on models change
     */
    @Override
    public void updateView() {
        gamePanel.removeAll();

        // Redraw
        Tile[][] tiles = gameModel.getTiles();
        for (int row = 0; row < gameModel.getGameRows(); row++) {
            for (int col = 0; col < gameModel.getGameCols(); col++) {
                gamePanel.add(tiles[row][col]);
            }
        }

        // Update Info label
        String info = "Escape: Exit | X: Unplug Console View | C: Plugin Console View | Score: " + decimalFormat.format(gameModel.getPoints());
        if (gameModel.getGameOver()) {
            info += " | Game Over!";
        } else {
            int[] nextBestMove = gameModel.getNextBestMove();
            info += " | " + "Next best move: row=" + nextBestMove[0] + ", col=" + nextBestMove[1];
        }
        infoLabel.setText(info);

        pack();
        setSize(800, 600);
        repaint();
    }

    /**
     * Adds a mouse listener to the games JPanel.
     *
     * @param listener the mouse listener to be added
     */
    @Override
    public void addGameMouseListener(MouseListener listener) {
        gamePanel.addMouseListener(listener);
    }

    /**
     * Adds a key listener to the JFrame.
     *
     * @param listener the key listener to be added
     */
    @Override
    public void addGameKeyListener(KeyListener listener) {
        this.addKeyListener(listener);
    }

    /**
     * Displays the highscores in a JOptionPane dialog.
     *
     * @param scores the list of highscores to be displayed
     */
    @Override
    public void showHighscores(List<Long> scores) {
        StringBuilder message = new StringBuilder();
        message.append("Highscores:\n");
        for (int i = 0; i < scores.size(); i++) {
            message.append("[Game ").append(i + 1).append("] ").append(scores.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Remove MouseListener when gameOver.
     */
    @Override
    public void removeMouseListener() {
        gamePanel.removeMouseListener(gamePanel.getMouseListeners()[0]);
    }

    @Override
    public JPanel getGamePanel() {
        return gamePanel;
    }
}
