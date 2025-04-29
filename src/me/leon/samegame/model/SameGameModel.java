package me.leon.samegame.model;

import me.leon.samegame.util.SoundManager;

import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;

/**
 * The {@code SameGameModel} class represents the game model for the SameGame application.
 * It implements the {@link GameModel} and {@link Serializable} interfaces.
 * This class manages the game state, including the game grid, points, game over condition,
 * and provides methods for initializing the game, processing user interactions, and finding the next best move.
 */
public class SameGameModel implements GameModel, Serializable {
    private int difficulty;
    private Tile[][] tiles;
    private Random r;
    private long points;
    private int currentTileGroupCount;
    private HashSet<Integer> currentColumnsToCollapse; // Use a Set for the collection of columns to be collapsed after a move to avoid duplicate checks when multiple tiles in one column are part of the group
    private boolean gameOver;
    private int[] nextBestMove;
    private final int gameRows;
    private final int gameCols;

    /**
     * Constructs a new {@code SameGameModel} with the specified
     * number of rows, columns, and difficulty level.
     *
     * @param gameRows   the number of rows in the game grid
     * @param gameCols   the number of cols in the game grid
     * @param difficulty the difficulty level of the game (number of colors)
     */
    public SameGameModel(int gameRows, int gameCols, int difficulty) {
        this(gameRows, gameCols, difficulty, new Random());
    }

    /**
     * Constructs a new {@code SameGameModel} with the specified
     * number of rows, columns, difficulty level and a seed.
     *
     * @param gameRows   the number of rows in the game grid
     * @param gameCols   the number of cols in the game grid
     * @param difficulty the difficulty level of the game (number of colors)
     * @param seed       the seed to generate game tiles from
     */
    public SameGameModel(int gameRows, int gameCols, int difficulty, long seed) {
        this(gameRows, gameCols, difficulty, new Random(seed));
    }

    /**
     * Constructs a new {@code SameGameModel} with the specified
     * number of rows, columns, difficulty level and a Random object.
     *
     * @param gameRows   the number of rows in the game grid
     * @param gameCols   the number of cols in the game grid
     * @param difficulty the difficulty level of the game (number of colors)
     * @param r          the Random generator, either random or based on a seed for tests
     */
    private SameGameModel(int gameRows, int gameCols, int difficulty, Random r) {
        this.gameRows = gameRows;
        this.gameCols = gameCols;
        this.difficulty = difficulty;
        this.points = 0;
        this.currentTileGroupCount = 0;
        this.currentColumnsToCollapse = new HashSet<>();
        this.gameOver = false;
        this.tiles = new Tile[this.gameRows][this.gameCols];
        this.r = r;
        initialize();
    }

    /**
     * Initializes the game by creating the game grid with random tile colors.
     */
    @Override
    public void initialize() {
        Color[] colors = {Color.red, Color.yellow, Color.blue, Color.green, Color.orange};

        // init array for games tiles
        for (int row = 0; row < gameRows; row++) {
            for (int col = 0; col < gameCols; col++) {
                tiles[row][col] = new Tile(colors[r.nextInt(difficulty)]);
            }
        }

        // re-initialize game if it's lost from the beginning
        if (checkGameOver()) {
            initialize();
        }

        // init next move suggestion
        nextBestMove = findLargestGroupPosition();
    }

    /**
     * Performs a flood fill search algorithm to find a group of connected tiles with the same color.
     * This method recursively explores adjacent tiles starting from the specified position (row, col).
     * It marks visited tiles and removes them from the grid, adding their columns to a set for potential collapsing.
     * This method is inspired by Burger, 2006, "Digitale Bildverarbeitung, Eine Einführung mit Java und ImageJ", p.196 "Flood Fill".
     *
     * @param row   the row index of the starting tile
     * @param col   the column index of the starting tile
     * @param color the color of the group being searched
     */
    private void floodSearchTileGroup(int row, int col, Color color) {
        // check if selected tile is in boundaries,
        if (row < 0 || row >= gameRows || col < 0 || col >= gameCols) return;
        // check if selected tile was visited already,
        // check if selected tile is same color as the searched group
        if (tiles[row][col].isVisited() || tiles[row][col].getColor() != color) return;

        // Mark the tile as visited and remove it (removing colors it white)
        tiles[row][col].setVisited(true);
        tiles[row][col].remove();
        SoundManager.get().playSound("click_tile");

        // add column to Set of columns that are checked for collapsing later in collapseColumn()
        currentColumnsToCollapse.add(col);

        // Recursive search for all 4 directions
        floodSearchTileGroup(row + 1, col, color);
        floodSearchTileGroup(row - 1, col, color);
        floodSearchTileGroup(row, col + 1, color);
        floodSearchTileGroup(row, col - 1, color);

        // increments the size of the currently searched tile group
        currentTileGroupCount++;
    }

    /**
     * Collapses a specified column by moving non-removed tiles to the top and removed tiles to the bottom.
     *
     * @param col the index of the column to collapse
     */
    private void collapseColumn(int col) {
        Tile[] tilesSelectedCol = new Tile[gameRows];

        // Get tiles in the selected column to tilesSelectedCol array
        for (int i = 0; i < tiles.length; i++) {
            tilesSelectedCol[i] = tiles[i][col];
        }

        // Sort tilesSelectedCol so that removed tiles are on top
        Tile[] tilesCollapsedCol = new Tile[tilesSelectedCol.length];
        int index = 0;
        // 1st add all tiles that are not removed
        for (Tile tile : tilesSelectedCol) {
            if (tile.isRemoved()) {
                tilesCollapsedCol[index++] = tile;
            }
        }
        // 2nd add all tiles that are removed on top
        for (Tile tile : tilesSelectedCol) {
            if (!tile.isRemoved()) {
                tilesCollapsedCol[index++] = tile;
            }
        }
        // Replace column in tiles with the new collapsed column
        for (int i = 0; i < tilesCollapsedCol.length; i++) {
            tiles[i][col] = tilesCollapsedCol[i];
        }
    }

    /**
     * When a column is empty this method shifts all right columns to the left
     * and fills in empty columns afterward.
     */
    private void shiftColumnsLeft() {
        // improve performance by cancelling method when all bottom tiles are un-removed
        boolean cancel = true;
        for (int i = 0; i < gameCols - 1; i++) {
            if (tiles[gameRows - 1][i].isRemoved()) {
                cancel = false;
                break;
            }
        }
        if (cancel) {
            return;
        }

        for (int redo = 0; redo < gameCols; redo++) {
            // do the complete process multiple times as there can be multiple columns removed at the same time (not ideally solved)
            cols:
            for (int col = 0; col < gameCols; col++) {
                // continue to next col if 1 unremoved tile is found in current col
                for (int row = 0; row < gameRows; row++) {
                    if (!tiles[row][col].isRemoved()) continue cols;
                }
                // here the current col must be empty, so starting from empty col: move all tiles one to left
                for (int col2 = col; col2 < gameCols - 1; col2++) {
                    for (int row2 = 0; row2 < gameRows; row2++) {
                        tiles[row2][col2] = tiles[row2][col2 + 1];
                    }

                }
                // set last col fixed to removed tiles
                for (int i = 0; i < gameRows; i++) {
                    tiles[i][gameCols - 1] = new Tile(Color.white, true);
                }
            }
        }
    }

    /**
     * Processes the game interaction after a tile is clicked.
     * Returns if the clicked tile is already removed or has no neighbors of the same color.
     * If neighbors of the same color are found, a flood fill algorithm is used to find and remove the group.
     * Columns are collapsed if necessary, points are updated based on the size of the eliminated group, and columns are shifted left if empty.
     * Lastly it updates the next best move and if the game is over, the respective flag will be set.
     *
     * @param clickedRow row index of the clicked tile
     * @param clickedCol col index of the clicked tile
     */
    @Override
    public void processGameInteraction(int clickedRow, int clickedCol) {
        // check if clicked tile is already removed
        if (tiles[clickedRow][clickedCol].isRemoved()) {
            SoundManager.get().playSound("click_empty");
            return;
        }

        // return here already when the clicked tile has no same colored neighbours
        Color clickedColor = tiles[clickedRow][clickedCol].getColor();
        int neighbours = 0;
        if (clickedRow - 1 >= 0 && clickedColor == tiles[clickedRow - 1][clickedCol].getColor())
            neighbours++; // Check top neighbour
        if (clickedRow + 1 < gameRows && clickedColor == tiles[clickedRow + 1][clickedCol].getColor())
            neighbours++; // Check bottom neighbour
        if (clickedCol - 1 >= 0 && clickedColor == tiles[clickedRow][clickedCol - 1].getColor())
            neighbours++; // Check left neighbour
        if (clickedCol + 1 < gameCols && clickedColor == tiles[clickedRow][clickedCol + 1].getColor())
            neighbours++; // Check right neighbour
        if (neighbours == 0) return;

        // find group of tiles based on click
        floodSearchTileGroup(clickedRow, clickedCol, tiles[clickedRow][clickedCol].getColor());

        // collapse columns from set if necessary
        for (Integer col : currentColumnsToCollapse) {
            collapseColumn(col);
        }

        // update the points based on size of eliminated group
        calculatePointsForTileGroup(currentTileGroupCount);
        currentTileGroupCount = 0; // reset counter for how many tiles have been found by the flood search

        // shift left when a column is empty
        shiftColumnsLeft();

        if (checkGameOver()) {
            gameOver = true;
            nextBestMove[0] = -1;
            nextBestMove[1] = -1;
        } else {
            nextBestMove = findLargestGroupPosition();
        }
    }

    /**
     * Calculates the points earned for removing a group of tiles based on the size of the group.
     * Points are calculated using the given formula (2 ^ groupSize - 2).
     *
     * @param currentTileGroupCount size of the tile group that got eliminated
     */
    private void calculatePointsForTileGroup(int currentTileGroupCount) {
        points += (long) (Math.pow(2, currentTileGroupCount) - 2);
    }

    /**
     * Checks if the game is over by examining whether there are any remaining moves possible.
     *
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean checkGameOver() {
        for (int row = 0; row < gameRows; row++) {
            for (int col = 0; col < gameCols; col++) {
                // Only check unremoved tiles for neighbors
                if (!tiles[row][col].isRemoved()) {
                    if (hasNeighborWithSameColor(row, col)) return false; // Game is not over
                }
            }
        }
        // No more moves are possible -> Game over
        return true;
    }

    /**
     * Checks if a tile has any neighboring tiles of the same color.
     *
     * @param row row index of the tile
     * @param col col index of the tile
     * @return true if the tile has neighboring tiles of the same color, false otherwise
     */
    private boolean hasNeighborWithSameColor(int row, int col) {
        Color color = tiles[row][col].getColor();

        // Check neighbor tiles: up, down, left, right
        if ((row > 0 && !tiles[row - 1][col].isRemoved() && tiles[row - 1][col].getColor() == color) ||
                (row < gameRows - 1 && !tiles[row + 1][col].isRemoved() && tiles[row + 1][col].getColor() == color) ||
                (col > 0 && !tiles[row][col - 1].isRemoved() && tiles[row][col - 1].getColor() == color) ||
                (col < gameCols - 1 && !tiles[row][col + 1].isRemoved() && tiles[row][col + 1].getColor() == color)) {
            return true; // Found neighbor tile of the same color
        }
        return false; // No neighbor tile of the same color found
    }

    private final int[] ROW_OFFSETS = {-1, 1, 0, 0};
    private final int[] COL_OFFSETS = {0, 0, -1, 1};

    /**
     * Finds the position of the largest tile group with the same color
     * in the current state of the game.
     * Uses depth-first search (DFS) algorithm.
     *
     * @return the starting position of the largest group as an array with size 2: [row, col]
     */
    private int[] findLargestGroupPosition() {
        boolean[][] visited = new boolean[gameRows][gameCols]; // Can't reuse visited flag from Tile class here.

        int maxGroupSize = 0;
        int[] maxGroupStartPos = {-1, -1};

        for (int i = 0; i < gameRows; i++) {
            for (int j = 0; j < gameCols; j++) {
                if (!visited[i][j]) {
                    int groupSize = dfs(visited, i, j, tiles[i][j].getColor());
                    if (groupSize > maxGroupSize) {
                        maxGroupSize = groupSize;
                        maxGroupStartPos[0] = i;
                        maxGroupStartPos[1] = j;
                    }
                }
            }
        }

        return maxGroupStartPos;
    }

    /**
     * Depth-first search (DFS) to figure out amount of connected tiles of the same color
     * for a specific tile.
     *
     * @param visited boolean array with visited tiles
     * @param row     row index of the current tile
     * @param col     col index of the current tile
     * @param color   color of the group being searched
     * @return size of the group of connected tiles
     */
    private int dfs(boolean[][] visited, int row, int col, Color color) {
        if (row < 0 || row >= gameRows || col < 0 || col >= gameCols || visited[row][col] || !tiles[row][col].getColor().equals(color) || tiles[row][col].isRemoved()) {
            return 0;
        }

        visited[row][col] = true;
        int size = 1;

        for (int d = 0; d < 4; d++) {
            int newRow = row + ROW_OFFSETS[d];
            int newCol = col + COL_OFFSETS[d];
            size += dfs(visited, newRow, newCol, color);
        }

        return size;
    }

    @Override
    public Tile[][] getTiles() {
        return this.tiles;
    }

    @Override
    public long getPoints() {
        return this.points;
    }

    @Override
    public boolean getGameOver() {
        return this.gameOver;
    }

    @Override
    public int[] getNextBestMove() {
        return this.nextBestMove;
    }

    @Override
    public int getGameRows() {
        return gameRows;
    }

    @Override
    public int getGameCols() {
        return gameCols;
    }
}
