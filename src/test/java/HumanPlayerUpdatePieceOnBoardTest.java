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

public class HumanPlayerUpdatePieceOnBoardTest {

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
                "T5 p 4 9 " + // No rotations or flips
                "T5 r p 4 9 " + // Rotation no flip
                "Z4 f p 4 9 " + // Flip no rotation
                "F r f p 4 9 ").getBytes());  // Flip and rotation


        System.setIn(input);
        new Input(new Scanner(System.in));
        testBoard.setGameBoard(testPlayer1.updatePieceOnBoard(testBoard.getGameBoard()));
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
        testBoard.setGameBoard(testPlayer1.updatePieceOnBoard(testBoard.getGameBoard()));
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
        testBoard.setGameBoard(testPlayer1.updatePieceOnBoard(testBoard.getGameBoard()));
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
        testBoard.setGameBoard(testPlayer1.updatePieceOnBoard(testBoard.getGameBoard()));
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
