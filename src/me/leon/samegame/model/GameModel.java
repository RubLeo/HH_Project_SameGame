package me.leon.samegame.model;

/**
 * The {@code GameModel} interface defines the contract for a model in a game.
 * The game model is responsible for maintaining the state of the game, including the game board, score, status, etc.
 */
public interface GameModel {

    /**
     * Initializes the game model.
     */
    void initialize();

    /**
     * Checks if the game is over. Not to be confused with getter for gameOver state.
     *
     * @return {@code true} if the game is over, {@code false} otherwise
     */
    boolean checkGameOver();

    /**
     * Returns the current state of the game board as a 2D array of {@link Tile}s.
     *
     * @return 2D array representing the game board
     */
    Tile[][] getTiles();

    /**
     * Processes a game interaction at the specified coordinates.
     * This method handles the logic for what happens when a player clicks on a tile.
     *
     * @param clickedRow row of the clicked tile
     * @param clickedCol col of the clicked tile
     */
    void processGameInteraction(int clickedRow, int clickedCol);

    /**
     * Retrieves the current score of the game.
     *
     * @return games current score
     */
    long getPoints();

    /**
     * Returns game over state.
     *
     * @return {@code true} if the game is over, {@code false} otherwise
     */
    boolean getGameOver();

    /**
     * Retrieves the number of rows in the game board.
     *
     * @return number of rows
     */
    int getGameRows();

    /**
     * Retrieves the number of columns in the game board.
     *
     * @return number of cols
     */
    int getGameCols();

    /**
     * Retrieves the next best move as an array of two integers representing the row and column of the move.
     *
     * @return array containing row and col of the next best move
     */
    int[] getNextBestMove();
}
