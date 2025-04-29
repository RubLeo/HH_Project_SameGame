package me.leon.samegame.controller;

import me.leon.samegame.input.InputObserver;
import me.leon.samegame.input.KeyboardHandler;
import me.leon.samegame.input.MouseHandler;
import me.leon.samegame.model.GameModel;
import me.leon.samegame.util.HighscoreManager;
import me.leon.samegame.view.GameView;
import me.leon.samegame.view.SameGameConsoleView;
import me.leon.samegame.view.SameGameView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code SameGameController} class manages the game logic and interactions in SameGame.
 * It follows the <b>Singleton</b> pattern and implements the {@link GameController} and {@link InputObserver} interfaces.
 * The controller de-/registers views, updates game state, is responsible for coordinating interactions
 * between model and views (following MVC principles), as well as responding to mouse and key events
 * to interact with the game.
 */
public class SameGameController implements GameController, InputObserver {
    private static SameGameController instance;
    private GameModel gameModel;
    private List<GameView> gameViews;

    /**
     * Constructs a new {@code SameGameController} with the specified game model.
     *
     * @param gameModel the game model to be associated with this controller
     */
    private SameGameController(GameModel gameModel) {
        this.gameModel = gameModel;
        this.gameViews = new ArrayList<>();
    }

    /**
     * Registers a view with the controller
     * and sets up event listeners for mouse and keyboard input for the GUI view.
     *
     * @param gameView the view to be registered with the controller
     */
    @Override
    public void registerView(GameView gameView) {
        gameViews.add(gameView);
        if (gameView instanceof SameGameView) {
            gameView.addGameMouseListener(new MouseHandler(this));
            gameView.addGameKeyListener(new KeyboardHandler(this));
        }
    }

    /**
     * De-registers a view from the controller.
     *
     * @param gameView the view to be de-registered from the controller
     */
    @Override
    public void deRegisterView(GameView gameView) {
        gameViews.remove(gameView);
    }

    /**
     * Called for updating all registered views in the game.
     */
    @Override
    public void updateViews() {
        for (GameView gameView : gameViews) {
            gameView.updateView();
        }
    }

    /**
     * Calls {@code processGameInteraction()} within the model at the clicked position.
     * The target tile is being calculated based on the panels size.
     * Updates the views and checks for game over condition.
     *
     * @param e MouseEvent representing the click
     */
    @Override
    public void onMouseClicked(MouseEvent e) {
        // Calculate tile position based on gamePanel
        int clickedRow = 0, clickedCol = 0;
        for (GameView gameView : gameViews) {
            if (gameView instanceof SameGameView) {
                clickedRow = e.getY() / (gameView.getGamePanel().getHeight() / gameModel.getGameRows());
                clickedCol = e.getX() / (gameView.getGamePanel().getWidth() / gameModel.getGameCols());
                break;
            }
        }
        gameModel.processGameInteraction(clickedRow, clickedCol);
        updateViews();
        if (gameModel.getGameOver()) {
            updateHighscore();
        }
    }

    /**
     * Takes user actions such as turning the console view on or off, escaping the game, etc.
     *
     * @param e KeyEvent representing the key pressed
     */
    @Override
    public void onKeyPressed(KeyEvent e) {
        // Turn off Console View on 'X'
        if (e.getKeyCode() == KeyEvent.VK_X) {
            int indexToRemove = -1;
            for (int i = 0; i < gameViews.size(); i++) {
                if (gameViews.get(i) instanceof SameGameConsoleView) {
                    indexToRemove = i;
                    break;
                }
            }
            if (indexToRemove != -1) {
                deRegisterView(gameViews.get(indexToRemove));
            }
        }

        // Turn on Console View on 'C'
        if (e.getKeyCode() == KeyEvent.VK_C) {
            registerView(new SameGameConsoleView(gameModel));
        }

        // Exit game immediately 'Escape'
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    /**
     * Updates the highscores using the {@link HighscoreManager} and triggers
     * their displaying in all registered views.
     */
    @Override
    public void updateHighscore() {
        // Load existing highscores
        HighscoreManager highscoreManager = HighscoreManager.loadHighscores("highscores.dat");
        // Add current game points to highscores
        highscoreManager.addScore(gameModel.getPoints());
        // Save updated highscores
        highscoreManager.saveHighscores("highscores.dat");

        List<Long> scores = highscoreManager.getHighscores();
        for (GameView gameView : gameViews) {
            gameView.showHighscores(scores);
            if (gameView instanceof SameGameView) {
                gameView.removeMouseListener();
            }
        }
    }

    /**
     * Retrieves the individual instance of the controller following the <b>Singleton</b> pattern.
     *
     * @param gameModel the game model to be associated with the controller
     * @return instance of the controller
     */
    public static SameGameController get(GameModel gameModel) {
        if (instance == null) {
            instance = new SameGameController(gameModel);
        }
        return instance;
    }
}
