/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src;

import java.util.*;

public class Board {

    private char[][] gameBoard;
    public final static int BOARD_WIDTH = 14;
    public final static int BOARD_HEIGHT = 14;
    
    public Board(){
        gameBoard = new char [BOARD_HEIGHT][BOARD_WIDTH];

        //Set up the board
        initialization();
    }

    public void initialization() {
        //Sets actual positions on the board
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                gameBoard[i][j]= ' ';
            }
        }
        //Starting Positions
        gameBoard[4][4] = '*';
        gameBoard[9][9] = '*';
    }

    public static int chooseRandomPlayer() {
        //This allows the game to be more fair (either player can start).
        Random randomPlayer = new Random();
        return randomPlayer.nextInt(2)+1;
    }
    
    public static boolean isValidMove(int[] gamePiece, char[][] gameBoard, int row, int col, Player currentPlayer) {
        if (currentPlayer.getPlayerPieceCollection().getPieces().isEmpty()) {
            return false;
        }
        //First piece has to be placed in starting position!
        if (currentPlayer.getFirstTurn() && !startingPosition(row, col, gamePiece, currentPlayer.getSymbol())) {
            return false;
        } else {
            //Checks if moves other than the first move are valid placements
            for (int i = 0; i < gamePiece.length; i += 2) {
                //Checking if a position is already occupied
                try {
                    if (gameBoard[row + gamePiece[i]][col + gamePiece[i + 1]] == 'X' ||
                        gameBoard[row + gamePiece[i]][col + gamePiece[i + 1]] == 'O') {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return false;
                }
            }
        }
        //Other conditions for checking when it is not a player's first turn
        if (!currentPlayer.getFirstTurn()) {
            //Elements in each row of 2d array are in the format of {rowOffset, columnOffset}
            //-1 indicates a decrease in the current row/column, 1 indicates an increase and 0 indicates no change
            int[][] directionsPieceEdgesPerimeter = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
            int[][] directionsPiecesCornersPerimeter = new int[][]{{-1,1},{1,-1},{-1,-1},{1,1}};

            ArrayList<Integer> positionsToCheckForSameColour = new ArrayList<>();
            ArrayList<Integer> positionstoCheckForDiffColour = new ArrayList<>();
            int rowOffset;
            int columnOffset;

            //Goes through every block that makes up a game piece and gets positions on board
            //(in terms of offset from reference point) that are next to flat edges
            for (int i = 0; i < gamePiece.length; i += 2) {
                for (int[] direction : directionsPieceEdgesPerimeter) {
                    rowOffset = gamePiece[i] + direction[0];
                    columnOffset = gamePiece[i + 1] + direction[1];
                    //Checks that position is not included in the set of positions that make up a game piece
                    if (!yxPositionIsInArray(rowOffset, columnOffset, gamePiece)) {
                        //gameBoard[row + rowOffset][col + columnOffset] = 'F'; ***********
                        positionsToCheckForSameColour.add(rowOffset);
                        positionsToCheckForSameColour.add(columnOffset);
                    }
                }
            }

            int[] flatPos = changeToPrimitive(positionsToCheckForSameColour);

            // Goes through every block that makes up a game piece and gets positions on board (in terms of offset from reference point) that are next to corners on piece //
            // Some positions are duplicates of positions in the positionsToCheckForSameColour ArrayList //
            for (int i = 0; i < gamePiece.length; i += 2) {
                for (int[] direction : directionsPiecesCornersPerimeter) {
                    rowOffset = gamePiece[i] + direction[0];
                    columnOffset = gamePiece[i + 1] + direction[1];
                    // Checks that position is not included in the set of positions that make up a game piece and that
                    // they are not duplicates of positions in positionsToCheckForSameColour ArrayList
                    if (!yxPositionIsInArray(rowOffset, columnOffset, gamePiece) &&
                            !yxPositionIsInArray(rowOffset, columnOffset, flatPos)) {
                        //gameBoard[row + rowOffset][col + columnOffset] = 'A';
                        positionstoCheckForDiffColour.add(rowOffset);
                        positionstoCheckForDiffColour.add(columnOffset);
                    }
                }
            }
            //Checks for validity in relation to the player's own pieces on the board (pieces have to touch corner but
            //cannot touch at sides).
            return !touchingSameColourAtEdge(gameBoard, row, col, positionsToCheckForSameColour, currentPlayer.getSymbol())
                && touchingSameColourAtCorner(gameBoard, row, col, positionstoCheckForDiffColour, currentPlayer.getSymbol());
        }
        //All conditions are met and this is a valid move!
        return true;
    }

    private static int[] changeToPrimitive(ArrayList<Integer> integerObjectArrayList) {
        int[] primitiveIntArray = new int[integerObjectArrayList.size()];

        for (int i = 0; i < integerObjectArrayList.size(); i++) {
            primitiveIntArray[i] = integerObjectArrayList.get(i);
        }

        return primitiveIntArray;
    }

    public static boolean atLeastOneValidMove(char[][] gameBoard, Player currentPlayer) {
        Hashtable<String, int[]> currentPlayerPieceCollection = currentPlayer.getPlayerPieceCollection().getPieces();
        Enumeration<String> playerKeys = currentPlayerPieceCollection.keys();
        int[] currentPiece;
        String currentElement;

        while (playerKeys.hasMoreElements()) { // For every piece remaining
            currentElement = playerKeys.nextElement();
            currentPiece = currentPlayer.getPlayerPieceCollection().getPieces().get(currentElement).clone();
            for (int row = 0; row < 14; row++) { // Go through every cell on the game board
                for (int column = 0; column < 14; column++) {
                    for (int rotationState  = 0; rotationState < 4; rotationState++) { // For every rotation of a piece,
                        // when rotationState hits 3 piece is rotated to normal position
                        currentPiece = currentPlayer.rotateBlock(currentPiece);
                        for (int flipState = 0; flipState < 2; flipState++) { // For every flip of a piece, when
                            // flipState is 1, piece is flipped back to previous position
                            currentPiece = currentPlayer.flipBlock(currentPiece);
                            if (Board.isValidMove(currentPiece, gameBoard, row, column, currentPlayer)) { // Check if a
                                // move with current piece state (current rotation and flip) is possible
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private static boolean touchingSameColourAtEdge(char[][] gameBoard, int row, int col, ArrayList<Integer> flatEdgePerimeterOffsets, char playerSymbol) {
        int rowOffset;
        int columnOffset;

        for (int i = 0; i < flatEdgePerimeterOffsets.size(); i += 2) {
            rowOffset = flatEdgePerimeterOffsets.get(i);
            columnOffset = flatEdgePerimeterOffsets.get(i+1);
            try {
                if (gameBoard[row + rowOffset][col + columnOffset] == playerSymbol) {
                   // System.out.println("\nYour piece is not allowed to touch a piece of
                    // the same colour at the an edge!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {} // Sometimes checks may go off the board.
            // It is enough to just ignore them
        }

        return false;
    }

    private static boolean touchingSameColourAtCorner(char[][] gameBoard, int row, int col, ArrayList<Integer> cornerEdgePerimeterOffsets, char playerSymbol) {
        int rowOffset;
        int columnOffset;

        for (int i = 0; i < cornerEdgePerimeterOffsets.size(); i += 2) {
            rowOffset = cornerEdgePerimeterOffsets.get(i);
            columnOffset = cornerEdgePerimeterOffsets.get(i+1);
            try {
                if (gameBoard[row + rowOffset][col + columnOffset] == playerSymbol)
                    return true;
            } catch (ArrayIndexOutOfBoundsException ignored) {} // Sometimes checks may go off the board. It is enough to just ignore them
        }
       // System.out.println("\nYour piece has to touch a piece of the same colour at a corner");
        return false;
    }

    private static boolean yxPositionIsInArray(int y, int x, int[] piece) {
        for (int i = 0; i < piece.length;) {
            if (piece[i] == y && piece[i+1] == x)
                return true;
            i += 2;
        }

        return false;
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
            if ((rowPos+gamePiece[i]) == startRowPosition && (colPos+gamePiece[i+1]) == startColumnPosition) {
                return true;
            }
            i += 2;
        }

        return false;
    }

    //Getters and/or Setters below this point
    public char[][] getGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(char[][] gameBoard) {
        for (int i = 0; i < this.gameBoard.length; i++)
            this.gameBoard[i] = gameBoard[i].clone();
    }

}
