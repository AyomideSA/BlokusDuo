/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

import org.junit.jupiter.api.Test;
import src.*;
import static org.testng.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayerTest {

    Board testBoard;
    HumanPlayer testPlayer1;
    HumanPlayer testPlayer2;
    ByteArrayInputStream input;
    InputStream defualtStream = System.in;
    char[][] expectedBoard;

    // Every rotation of every piece //
    int[][] rotations = new int[][]{
            {0, 0, 1, 0, -1, 0, 0, 1, 2, 0},
            {0, 0, 0, -1, 0, 1, 1, 0, 0, -2},
            {0, 0, -1, 0, 1, 0, 0, -1, -2, 0},
            {0, 0, 0, 1, 0, -1, -1, 0, 0, 2},
            {0, 0, 1, 0, -1, 0, 0, 1, 0, -1},
            {0, 0, 0, -1, 0, 1, 1, 0, -1, 0},
            {0, 0, -1, 0, 1, 0, 0, -1, 0, 1},
            {0, 0, 0, 1, 0, -1, -1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 2, 2, 0},
            {0, 0, 0, -1, 1, 0, 2, 0, 0, -2},
            {0, 0, -1, 0, 0, -1, 0, -2, -2, 0},
            {0, 0, 0, 1, -1, 0, -2, 0, 0, 2},
            {0, 0, -1, 0, -1, -1, 0, 1, 1, 1},
            {0, 0, 0, 1, -1, 1, 1, 0, 1, -1},
            {0, 0, 1, 0, 1, 1, 0, -1, -1, -1},
            {0, 0, 0, -1, 1, -1, -1, 0, -1, 1},
            {0, 0, 0, -1, 0, 1, 1, -1, 1, 1},
            {0, 0, -1, 0, 1, 0, -1, -1, 1, -1},
            {0, 0, 0, 1, 0, -1, -1, 1, -1, -1},
            {0, 0, 1, 0, -1, 0, 1, 1, -1, 1},
            {0, 0, 0, 1, 1, 0},
            {0, 0, 1, 0, 0, -1},
            {0, 0, 0, -1, -1, 0},
            {0, 0, -1, 0, 0, 1},
            {0, 0, 1, 0, 0, -1, 1, -1, 0, -2},
            {0, 0, 0, -1, -1, 0, -1, -1, -2, 0},
            {0, 0, -1, 0, 0, 1, -1, 1, 0, 2},
            {0, 0, 0, 1, 1, 0, 1, 1, 2, 0},
            {0, 0, 0, -1, -1, -1, 1, 0, 2, 0},
            {0, 0, -1, 0, -1, 1, 0, -1, 0, -2},
            {0, 0, 0, 1, 1, 1, -1, 0, -2, 0},
            {0, 0, 1, 0, 1, -1, 0, 1, 0, 2},
            {0, 0, 1, 0, -1, 0, 0, 1, 0, 2},
            {0, 0, 0, -1, 0, 1, 1, 0, 2, 0},
            {0, 0, -1, 0, 1, 0, 0, -1, 0, -2},
            {0, 0, 0, 1, 0, -1, -1, 0, -2, 0},
            {0, 0, 1, 0, -1, 0, 0, 1},
            {0, 0, 0, -1, 0, 1, 1, 0},
            {0, 0, -1, 0, 1, 0, 0, -1},
            {0, 0, 0, 1, 0, -1, -1, 0},
            {0, 0, 0, -1, 0, 1, -1, 0, 1, 1},
            {0, 0, -1, 0, 1, 0, 0, 1, 1, -1},
            {0, 0, 0, 1, 0, -1, 1, 0, -1, -1},
            {0, 0, 1, 0, -1, 0, 0, -1, -1, 1},
            {0, 0, 1, 0, -1, 0, 1, 1, -1, -1},
            {0, 0, 0, -1, 0, 1, 1, -1, -1, 1},
            {0, 0, -1, 0, 1, 0, -1, -1, 1, 1},
            {0, 0, 0, 1, 0, -1, -1, 1, 1, -1},
            {0, 0, -1, 0, 0, 1, 1, 1},
            {0, 0, 0, 1, 1, 0, 1, -1},
            {0, 0, 1, 0, 0, -1, -1, -1},
            {0, 0, 0, -1, -1, 0, -1, 1},
            {0, 0, 0, 1, 0, 2, 0, 3, 0, 4},
            {0, 0, 1, 0, 2, 0, 3, 0, 4, 0},
            {0, 0, 0, -1, 0, -2, 0, -3, 0, -4},
            {0, 0, -1, 0, -2, 0, -3, 0, -4, 0},
            {0, 0, 0, 1, 0, 2, 0, 3},
            {0, 0, 1, 0, 2, 0, 3, 0},
            {0, 0, 0, -1, 0, -2, 0, -3},
            {0, 0, -1, 0, -2, 0, -3, 0},
            {0, 0, 1, 0, 2, 0, 3, 0, 0, 1},
            {0, 0, 0, -1, 0, -2, 0, -3, 1, 0},
            {0, 0, -1, 0, -2, 0, -3, 0, 0, -1},
            {0, 0, 0, 1, 0, 2, 0, 3, -1, 0},
            {0, 0, 0, 1, 0, 2},
            {0, 0, 1, 0, 2, 0},
            {0, 0, 0, -1, 0, -2},
            {0, 0, -1, 0, -2, 0},
            {0, 0, 0, 1, 0, 2, 1, 0},
            {0, 0, 1, 0, 2, 0, 0, -1},
            {0, 0, 0, -1, 0, -2, -1, 0},
            {0, 0, -1, 0, -2, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 0, 0, -1},
            {0, 0, -1, 0},
            {0, 0, 0, 1, 1, 1, 1, 0},
            {0, 0, 1, 0, 1, -1, 0, -1},
            {0, 0, 0, -1, -1, -1, -1, 0},
            {0, 0, -1, 0, -1, 1, 0, 1},
            {0, 0},{0, 0},{0, 0},{0, 0}};


    // Every possible flip of every piece //
    int[][] flips = new int[][]{
            {0, 0, 0, -1, 0, 1, -1, 0, 0, -2},
            {0, 0, 0, 1, 0, -1, -1, 0, 0, 2},
            {0, 0, 0, -1, 0, 1, -1, 0, 1, 0},
            {0, 0, 0, 1, 0, -1, -1, 0, 1, 0},
            {0, 0, 0, -1, -1, 0, -2, 0, 0, -2},
            {0, 0, 0, 1, -1, 0, -2, 0, 0, 2},
            {0, 0, 0, 1, 1, 1, -1, 0, -1, -1},
            {0, 0, 0, -1, 1, -1, -1, 0, -1, 1},
            {0, 0, 1, 0, -1, 0, 1, -1, -1, -1},
            {0, 0, 1, 0, -1, 0, 1, 1, -1, 1},
            {0, 0, -1, 0, 0, -1},
            {0, 0, -1, 0, 0, 1},
            {0, 0, 0, -1, 1, 0, 1, -1, 2, 0},
            {0, 0, 0, 1, 1, 0, 1, 1, 2, 0},
            {0, 0, 1, 0, 1, 1, 0, -1, 0, -2},
            {0, 0, 1, 0, 1, -1, 0, 1, 0, 2},
            {0, 0, 0, -1, 0, 1, -1, 0, -2, 0},
            {0, 0, 0, 1, 0, -1, -1, 0, -2, 0},
            {0, 0, 0, -1, 0, 1, -1, 0},
            {0, 0, 0, 1, 0, -1, -1, 0},
            {0, 0, 1, 0, -1, 0, 0, 1, -1, -1},
            {0, 0, 1, 0, -1, 0, 0, -1, -1, 1},
            {0, 0, 0, -1, 0, 1, -1, -1, 1, 1},
            {0, 0, 0, 1, 0, -1, -1, 1, 1, -1},
            {0, 0, 0, 1, -1, 0, -1, -1},
            {0, 0, 0, -1, -1, 0, -1, 1},
            {0, 0, -1, 0, -2, 0, -3, 0, -4, 0},
            {0, 0, -1, 0, -2, 0, -3, 0, -4, 0},
            {0, 0, -1, 0, -2, 0, -3, 0},
            {0, 0, -1, 0, -2, 0, -3, 0},
            {0, 0, 0, -1, 0, -2, 0, -3, -1, 0},
            {0, 0, 0, 1, 0, 2, 0, 3, -1, 0},
            {0, 0, -1, 0, -2, 0},
            {0, 0, -1, 0, -2, 0},
            {0, 0, -1, 0, -2, 0, 0, -1},
            {0, 0, -1, 0, -2, 0, 0, 1},
            {0, 0, -1, 0},
            {0, 0, -1, 0},
            {0, 0, -1, 0, -1, -1, 0, -1},
            {0, 0, -1, 0, -1, 1, 0, 1},
            {0, 0},{0, 0},{0, 0},{0, 0}};


    @Test
    void chooseBlock() {
        testBoard = new Board();
        HumanPlayer testPlayer1 = new HumanPlayer(1, "Ayo");
        input = new ByteArrayInputStream((
                "I5 " + // Valid input
                "C ").getBytes());  // C block does not exist
        System.setIn(input);
        new Input(new Scanner(System.in));
        assertEquals("I5", testPlayer1.chooseBlock(testBoard.getGameBoard()));

        assertThrows(NoSuchElementException.class, () -> {
            testPlayer1.chooseBlock(testBoard.getGameBoard());
        });
        System.setIn(defualtStream);
    }

    @Test
    void rotateBlock() {
        Player test = new HumanPlayer(1, "Ayo");
        GamePieceCollection testPiece = new GamePieceCollection();
        Enumeration<String> keys = testPiece.getPieces().keys();
        String currentKey;
        int[] piece;

        int j = 0;
        // Goes through every rotation of every piece
        // and checks that rotation is as expected
        while (keys.hasMoreElements()) {
            currentKey = keys.nextElement();
            piece = testPiece.getPieces().get(currentKey);
            for (int i = 0; i < 4; i++) {
                piece = test.rotateBlock(piece);
                assertEquals(rotations[j++], piece);
            }
        }
    }

    @Test
    void flipBlock() {
        Player test = new HumanPlayer(1, "Wiki");
        GamePieceCollection testPiece = new GamePieceCollection();
        Enumeration<String> keys = testPiece.getPieces().keys();
        String currentKey;
        int[] piece;

        int j = 0;
        // Goes through every flip of every piece
        // and checks that flip is as expected
        while (keys.hasMoreElements()) {
            currentKey = keys.nextElement();
            piece = testPiece.getPieces().get(currentKey);
            for (int i = 0; i < 2; i++) {
                piece = test.flipBlock(piece);
                assertEquals(piece, flips[j++]);
            }
        }
    }

    @Test
    void getName() {
        testPlayer1 = new HumanPlayer(1, "Boris");
        assertEquals("Boris", testPlayer1.getName());

        testPlayer1 = new HumanPlayer(1, "Ayo");
        assertEquals("Ayo", testPlayer1.getName());
    }

    @Test
    void getSymbol() {
        testPlayer1 = new HumanPlayer(1, "Boris");
        assertEquals('X', testPlayer1.getSymbol());

        testPlayer2 = new HumanPlayer(2, "Wiki");
        assertEquals('O', testPlayer2.getSymbol());
    }

    @Test
    void getPlayerPieceCollection() {
        GamePieceCollection defualtCollection = new GamePieceCollection();
        testPlayer1 = new HumanPlayer(1, "Ayo");
        assertEquals(defualtCollection, testPlayer1.getPlayerPieceCollection());

        defualtCollection.getPieces().remove("Y");
        assertNotEquals(defualtCollection, testPlayer1.getPlayerPieceCollection());
    }

    @Test
    void getFirstTurn() {
        testPlayer1 = new HumanPlayer(2, "Ayo");

        assertTrue(testPlayer1.getFirstTurn());

        testPlayer1.setFirstTurn(false);
        assertFalse(testPlayer1.getFirstTurn());
    }

    @Test
    void setFirstTurn() {
        testPlayer1 = new HumanPlayer(1, "Wiki");

        testPlayer1.setFirstTurn(false);
        assertFalse(testPlayer1.getFirstTurn());

        testPlayer1.setFirstTurn(true);
        assertTrue(testPlayer1.getFirstTurn());
    }

}