package me.leon.samegame.controller;

import me.leon.samegame.view.GameView;

/**
 * The {@code GameController} interface defines the contract for a controller in a game.
 * A controller is responsible for managing interactions between the game model and the views.
 */
public interface GameController {
    /**
     * Registers a view with the controller.
     * This method allows the controller to communicate with the view,
     * updating it based on changes in the model.
     *
     * @param gameView the view to be registered with the controller
     */
    void registerView(GameView gameView);

    /**
     * De-registers a view from the controller.
     *
     * @param gameView the view to be de-registered from the controller
     */
    void deRegisterView(GameView gameView);

    /**
     * Called for updating all registered views in the game.
     */
    void updateViews();

    /**
     * Used for triggering a highscore update on the views, e.g. when a game is finished.
     */
    void updateHighscore();
}
