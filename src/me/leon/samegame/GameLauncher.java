package me.leon.samegame;

import me.leon.samegame.controller.GameController;
import me.leon.samegame.controller.SameGameController;
import me.leon.samegame.model.Difficulty;
import me.leon.samegame.model.GameModel;
import me.leon.samegame.model.SameGameModel;
import me.leon.samegame.util.SoundManager;
import me.leon.samegame.view.GameView;
import me.leon.samegame.view.SameGameConsoleView;
import me.leon.samegame.view.SameGameView;

/**
 * The {@code GameLauncher} class is the entry point for the SameGame application.
 * It initializes the model, views, and controller, sets up the {@code SoundManager},
 * and ensures that all components are properly registered and instantiated.
 */
public class GameLauncher {
    /**
     * Entry point of the game.
     */
    public static void main(String[] args) {
        // MODEL
        GameModel sameGameModel = new SameGameModel(6, 9, Difficulty.MEDIUM);

        // VIEWS
        GameView sameGameView = new SameGameView(sameGameModel);
        GameView sameGameConsoleView = new SameGameConsoleView(sameGameModel);

        // CONTROLLER (Singleton: Only one Controller should exist at any time)
        GameController sameGameController = SameGameController.get(sameGameModel);

        // make sure SoundManager is instantiated and so has loaded sound files before the game starts (performance)
        SoundManager.get();

        // register views to controller
        sameGameController.registerView(sameGameView);
        sameGameController.registerView(sameGameConsoleView);
    }
}
