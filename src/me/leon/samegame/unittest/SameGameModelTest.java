package me.leon.samegame.unittest;

import me.leon.samegame.model.Difficulty;
import me.leon.samegame.model.SameGameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code SameGameModelTest} class contains attempts of testing with JUnit for the {@link SameGameModel} class.
 * These tests ensure the correctness of some of the games' logic.
 * Deterministic testing with a seed for the game grid generation ensures consistent results
 * for every test execution.
 */
public class SameGameModelTest {
    private SameGameModel sgm;

    /**
     * Construct test game grid based on specific seed.
     * Shall be done before each Test.
     */
    @BeforeEach
    public void initSameGameModel() {
        sgm = new SameGameModel(6, 9, Difficulty.MEDIUM, 408);
    }

    /**
     * Tests the constructor with game over state.
     * Verifies that the game over state is consistent after initialization,
     * and the game is not over right after the start already.
     */
    @Test
    void testConstructorWithGameOver() {
        assertEquals(sgm.getGameOver(), sgm.checkGameOver(), "After init gameOver state shall be consistent");
        assertFalse(sgm.checkGameOver(), "After init the game shall not be over already");
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
        int[] nextBestMove = sgm.getNextBestMove();

        assertNotNull(nextBestMove, "nextBestMove shall not be null");
        assertEquals(2, nextBestMove.length, "nextBestMove shall have exactly 2 values");
        assertTrue(nextBestMove[0] >= 0 && nextBestMove[0] < sgm.getGameRows(), "row index shall be between 0 and gameRows");
        assertTrue(nextBestMove[1] >= 0 && nextBestMove[1] < sgm.getGameCols(), "col index shall be between 0 and gameCols");
        assertEquals(3, nextBestMove[0], "row index for nextBestMove shall be 3 in this test case");
        assertEquals(7, nextBestMove[1], "col index for nextBestMove shall be 7 in this test case");
    }

    /**
     * Tests the processing of points during a game interaction.
     * Example tile of biggest group (5 tiles) in this test case is red,
     * tiles[3][7].
     */
    @Test
    void testProcessGameInteraction() {
        sgm.processGameInteraction(3, 7);
        assertEquals(30, sgm.getPoints(), "Score shall be 30 for this move");
    }

    /**
     * Tests all next best moves through the constructed game.
     */
    @Test
    void testFindLargestGroupPosition() {
        int[][] testMoves = {{3, 7}, {1, 0}, {0, 3}, {1, 1}, {1, 4}, {3, 1}, {4, 0}, {4, 0}, {4, 2}, {4, 3}, {4, 2}, {5, 2}, {5, 4}};
        int[] nextBestMove;

        for (int i = 0; i < testMoves.length; i++) {
            nextBestMove = sgm.getNextBestMove();
            assertEquals(testMoves[i][0], nextBestMove[0], "Expected row value for move " + i + " shall be " + testMoves[i][0]);
            assertEquals(testMoves[i][1], nextBestMove[1], "Expected col value for move " + i + " shall be " + testMoves[i][1]);
            sgm.processGameInteraction(testMoves[i][0], testMoves[i][1]);
        }
        assertTrue(sgm.getGameOver(), "Game shall be over by now.");
    }
}
