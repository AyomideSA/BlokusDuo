/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

import org.junit.jupiter.api.Test;
import src.*;
import static org.testng.Assert.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

public class BoardTest {

    Board testBoard = new Board();
    HumanPlayer testPlayer1 = new HumanPlayer(1, "Mary");

    @Test
    void initialization() {
        char[][] expectedArray = new char[][]{  {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};

        assertTrue(Arrays.deepEquals(expectedArray, testBoard.getGameBoard()));
    }

    @Test
    void chooseRandomPlayer() {
        // Don't feel any need to test this method as it is just using
        // a method in the Random class
    }

    @Test
    void isValidMove() {
        Hashtable<String, int[]> testPlayer1CollectionPieces = testPlayer1.getPlayerPieceCollection().getPieces();
        Enumeration<String> keys = testPlayer1CollectionPieces.keys();
        String currentKey;

        // Pieces should only be allowed to be placed in starting position on first turn //
        while (keys.hasMoreElements()) {
            currentKey = keys.nextElement();
            for (int row = 0; row < 14; row++) {
                for (int column = 0; column < 14; column++) {
                    if (!startingPosition(row, column, testPlayer1CollectionPieces.get(currentKey), testPlayer1.getSymbol())) {
                        assertFalse(Board.isValidMove(testPlayer1CollectionPieces.get(currentKey), testBoard.getGameBoard(), row, column, testPlayer1));
                    }
                }
            }
        }

        keys = testPlayer1CollectionPieces.keys();

        while (keys.hasMoreElements()) {
            currentKey = keys.nextElement();
            for (int row = 0; row < 14; row++) {
                for (int column = 0; column < 14; column++) {
                    if (startingPosition(row, column, testPlayer1CollectionPieces.get(currentKey), testPlayer1.getSymbol())) {
                        assertTrue(Board.isValidMove(testPlayer1CollectionPieces.get(currentKey), testBoard.getGameBoard(), row, column, testPlayer1));
                    }
                }
            }
        }

        testPlayer1.setFirstTurn(false);

        // Pieces should not be allowed to place off the board //
        assertFalse(Board.isValidMove(testPlayer1CollectionPieces.get("I1"), testBoard.getGameBoard(), 15, 15, testPlayer1));

        testBoard.setGameBoard(new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','O',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','O',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','O',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}});

        // Piece to be placed should not be allowed to overlap another piece
        assertFalse(Board.isValidMove(testPlayer1CollectionPieces.get("I2"), testBoard.getGameBoard(), 10, 9, testPlayer1));  // If new piece is completely covered by piece already on board
        assertFalse(Board.isValidMove(testPlayer1CollectionPieces.get("I2"), testBoard.getGameBoard(), 9, 9, testPlayer1)); // If new piece is only partially covered by piece already on board

        testBoard.setGameBoard(new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}});

        // Piece allowed to be placed when corner touches piece of same symbol //
        assertTrue(Board.isValidMove(testPlayer1CollectionPieces.get("I2"), testBoard.getGameBoard(), 12, 10, testPlayer1));

        testBoard.setGameBoard(new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}});

        // FLat edge of piece not allowed to touch another piece of same colour //
        assertFalse(Board.isValidMove(testPlayer1CollectionPieces.get("I2"), testBoard.getGameBoard(), 12, 10, testPlayer1));

        testBoard.setGameBoard(new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','O',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}});

        // Flat edge of piece allowed to touch piece of different colour in any way //
        assertTrue(Board.isValidMove(testPlayer1CollectionPieces.get("I2"), testBoard.getGameBoard(), 12, 10, testPlayer1));
    }

    @Test
    void atLeastOneValidMove() {
        resetBoard(testBoard);

        // Fresh board always has at least one valid move //
        assertTrue(Board.atLeastOneValidMove(testBoard.getGameBoard(), testPlayer1));

        testPlayer1.setFirstTurn(false);

        testBoard.setGameBoard(new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}});

        // Should return true as it is not first turn anymore
        // And there is at least one valid place to put piece
        assertTrue(Board.atLeastOneValidMove(testBoard.getGameBoard(), testPlayer1));

        testBoard.setGameBoard(new char[][]{
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'}});

        // No place to touch piece of same colour at corner without touching at edge as well //
        assertFalse(Board.atLeastOneValidMove(testBoard.getGameBoard(), testPlayer1));

        testBoard.setGameBoard(new char[][]{
                {'X','O',' ','O','X','X','X','X','X','X','X','X','X','X'},
                {'X','O',' ','O','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','O','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'}});

        // Only able to place a piece in a place that forces touching with other colour, should be valid move //
        assertTrue(Board.atLeastOneValidMove(testBoard.getGameBoard(), testPlayer1));

        testBoard.setGameBoard(new char[][]{
                {'X','O',' ',' ',' ',' ',' ','O','X','X','X','X','X','X'},
                {'X','X','O','O','O','O','O','O','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X'}});

        // Same as above but need rotation //
        assertTrue(Board.atLeastOneValidMove(testBoard.getGameBoard(), testPlayer1));
    }

    @Test
    void setGameBoard() {
        char[][] newGameBoard =  new char[][]{
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'},
                {'X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' '},
                {' ','X',' ','X',' ','X',' ','X',' ','X',' ','X',' ','X'}};

        testBoard.setGameBoard(newGameBoard);

        assertTrue(Arrays.deepEquals(newGameBoard, testBoard.getGameBoard()));

        // Changing newGameBoard should not affect the 2d array in out board class
        newGameBoard[0][0] = 'O';
        assertFalse(Arrays.deepEquals(newGameBoard, testBoard.getGameBoard()));
    }

    @Test
    void getGameBoard() {
        char[][] expectedBoard = new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};

        // Default board state is returned //
        assertTrue(Arrays.deepEquals(testBoard.getGameBoard(), expectedBoard));

        char[][] expectedBoard2 = new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ','O','O','O',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};

        testBoard.setGameBoard(new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ','O','O','O',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}});

        // Any state that board is in after being updated should be returned //
        assertTrue(Arrays.deepEquals(expectedBoard2, testBoard.getGameBoard()));
    }

    private static boolean startingPosition(int rowPos, int colPos, int[] gamePiece, char playerSymbol) {
        int startRowPosition;
        int startColumnPosition;

        if (playerSymbol == 'X') {
            startRowPosition = 4;
            startColumnPosition = 4;
        } else {
            startRowPosition = 9;
            startColumnPosition = 9;
        }
        for (int i = 0; i < gamePiece.length;) {
            if ((rowPos+gamePiece[i]) == startRowPosition && (colPos+gamePiece[i+1]) == startColumnPosition)
                return true;
            i += 2;
        }
        return false;
    }

    private static void resetBoard(Board testBoard) {
        testBoard.setGameBoard(new char[][]{
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}});
    }

}