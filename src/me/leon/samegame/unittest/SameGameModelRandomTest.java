package me.leon.samegame.unittest;

import me.leon.samegame.model.Difficulty;
import me.leon.samegame.model.SameGameModel;
import me.leon.samegame.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code SameGameModelTest} class contains attempts of testing with JUnit for the {@link SameGameModel} class.
 * These tests ensure the correctness of some of the games' logic.
 * Optional testing with a seed for the game grid generation enables random testing
 * for every test execution.
 */
public class SameGameModelRandomTest {
    private SameGameModel sameGameModel;
    private Random rTest;
    private int gameRows;
    private int gameCols;

    /**
     * Construct test game grid based on specific seed.
     * Shall be done before each Test.
     * Optionally insert seeds for both the model and rTest.
     */
    @BeforeEach
    public void initSameGameModel() {
        gameRows = 6;
        gameCols = 9;
        sameGameModel = new SameGameModel(gameRows, gameCols, Difficulty.MEDIUM);
        rTest = new Random();
    }

    /**
     * Tests the constructor with game over state.
     * Verifies that the game over state is consistent after initialization,
     * and the game is not over right after the start already.
     */
    @Test
    void testConstructorWithGameOver() {
        assertEquals(sameGameModel.getGameOver(), sameGameModel.checkGameOver(), "After init gameOver state shall be consistent");
        assertFalse(sameGameModel.checkGameOver(), "After init the game shall not be over already");
    }

    /**
     * Tests the calculation of {@code nextBestMove()}.
     * Verifies that the {@code nextBestMove} fields is not null,
     * and contains valid row and column indices within the game grid.
     * With the specific test case game init the test also verifies
     * its correctness.
     */
    @Test
    void testNextBestMoveValid() {
        int[] nextBestMove = sameGameModel.getNextBestMove();

        assertNotNull(nextBestMove, "nextBestMove shall not be null");
        assertEquals(2, nextBestMove.length, "nextBestMove shall have exactly 2 values");
        assertTrue(nextBestMove[0] >= 0 && nextBestMove[0] < sameGameModel.getGameRows(), "row index shall be between 0 and gameRows");
        assertTrue(nextBestMove[1] >= 0 && nextBestMove[1] < sameGameModel.getGameCols(), "col index shall be between 0 and gameCols");
    }

    /**
     * Tests all next best moves and general functionality through the constructed game.
     * Based on settings in {@link #initSameGameModel()} method.
     */
    @Test
    void playGameRandomly() {
        // show test game grid
        System.out.println("Grid:");
        Tile[][] tiles = sameGameModel.getTiles();
        for (int row = 0; row < sameGameModel.getGameRows(); row++) {
            for (int col = 0; col < sameGameModel.getGameCols(); col++) {
                System.out.print(tiles[row][col].toString() + " ");
            }
            System.out.println();
        }

        int[] nextBestMove = sameGameModel.getNextBestMove();
        int rowClick;
        int colClick;
        // run game with random clicks until gameOver = true
        for (; ; ) {
            rowClick = rTest.nextInt(gameRows);
            colClick = rTest.nextInt(gameCols);
            System.out.print("getNextBestMove() = " + nextBestMove[0] + "|" + nextBestMove[1]);
            System.out.println(", click = " + rowClick + "|" + colClick);
            assertNotNull(nextBestMove, "nextBestMove shall never be null");
            assertEquals(2, nextBestMove.length, "nextBestMove shall have exactly 2 values at all time");
            assertTrue(nextBestMove[0] >= 0 && nextBestMove[0] < sameGameModel.getGameRows(), "row index shall always be between 0 and gameRows");
            assertTrue(nextBestMove[1] >= 0 && nextBestMove[1] < sameGameModel.getGameCols(), "col index shall always be between 0 and gameCols");
            sameGameModel.processGameInteraction(rowClick, colClick);
            if (sameGameModel.getGameOver()) break;
            else nextBestMove = sameGameModel.getNextBestMove();
        }
        assertTrue(sameGameModel.getGameOver(), "Game shall be over by now.");
    }
}
