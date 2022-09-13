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
import java.util.Arrays;
import java.util.Scanner;

public class HumanPlayerPlacePieceTest {

    Board testBoard;
    HumanPlayer testPlayer1;
    ByteArrayInputStream input;
    InputStream defualtStream = System.in;
    char[][] expectedBoard;

    @Test
    void placePiece() {
        testBoard = new Board();
        HumanPlayer testPlayer1 = new HumanPlayer(1, "Ayo");

        input = new ByteArrayInputStream(("" +
                "p 4 9 " + // No rotations or flips
                "r p 4 9 " + // Rotation no flip
                "f p 4 9 " + // Flip no rotation
                "r f p 4 9 ").getBytes());  // Flip and rotation


        System.setIn(input);
        testBoard.setGameBoard(testPlayer1.placePiece("T5", testBoard.getGameBoard()));
        expectedBoard = new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ','X','X','X',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};

        assertTrue(Arrays.deepEquals(expectedBoard, testBoard.getGameBoard()));


        testBoard = new Board();
        testPlayer1 = new HumanPlayer(1,"Ayo");
        testBoard.setGameBoard(testPlayer1.placePiece("T5", testBoard.getGameBoard()));
        expectedBoard = new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','X','X','X',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};

        assertTrue(Arrays.deepEquals(expectedBoard, testBoard.getGameBoard()));

        testBoard = new Board();
        testPlayer1 = new HumanPlayer(1,"Ayo");
        testBoard.setGameBoard(testPlayer1.placePiece("Z4", testBoard.getGameBoard()));
        expectedBoard = new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ','X','X',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','X','X',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};

        assertTrue(Arrays.deepEquals(expectedBoard, testBoard.getGameBoard()));

        testBoard = new Board();
        testPlayer1 = new HumanPlayer(1,"Ayo");
        testBoard.setGameBoard(testPlayer1.placePiece("F", testBoard.getGameBoard()));
        expectedBoard = new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ','X','X','X',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};

        assertTrue(Arrays.deepEquals(expectedBoard, testBoard.getGameBoard()));
        System.setIn(defualtStream);
    }
}
