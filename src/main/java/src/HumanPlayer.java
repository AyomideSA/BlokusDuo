/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src;

import java.util.Arrays;

public class HumanPlayer implements Player {

    private final String name;
    private final char pieceSymbol;
    private final GamePieceCollection playerPieceCollection;
    private boolean firstTurn;
    private final Score score;
    private boolean currentTurn = false;

    public HumanPlayer(int playerNum) {
        System.out.println("Player"+ playerNum + " please enter your name:");

        String name = "";
        if (Input.in.hasNextLine()) {
            name = Input.in.nextLine();
        }
        if (name.isEmpty()) {
            name = Integer.toString(playerNum);
        }

        if (playerNum == 1) {
            this.pieceSymbol = 'X';
        } else {
            this.pieceSymbol = 'O';
        }

        this.name = name;
        this.firstTurn = true;
        this.playerPieceCollection = new GamePieceCollection();
        this.score = new Score();
    }

    // Constructor that doesn't take input from player within //
    // Used during tests //
    public HumanPlayer(int playerNum, String name) {
        if (playerNum == 1) {
            this.pieceSymbol = 'X';
        }
        else {
            this.pieceSymbol = 'O';
        }

        this.name = name;
        this.firstTurn = true;
        this.playerPieceCollection = new GamePieceCollection();
        this.score = new Score();
    }

    // Constructor for testing purposes //
    public HumanPlayer(String name, char pieceSymbol, GamePieceCollection playerPieceCollection) {
        this.name = name;
        this.pieceSymbol = pieceSymbol;
        this.playerPieceCollection = playerPieceCollection;
        this.score = new Score();
    }

    @Override
    public String chooseBlock(char[][] board) {
        System.out.println("Player " + name + " (" + pieceSymbol + ")" + " enter the name of the gamepiece you would like to play:");
        String pieceName;
        int[] piece;

        do {
            pieceName = Input.in.next().toUpperCase();
            piece = playerPieceCollection.getPieces().get(pieceName);
            if (!pieceFound(piece)) {
                System.out.println("You don't have this piece left!");
            }
        } while (!pieceFound(piece));

        GamePiece.printPiece(piece, pieceSymbol);

        return pieceName;
    }

    public char[][] updatePieceOnBoard(char[][] board) {
        String chosen_piece;
        char[][] newBlokusBoard;

         do {
            chosen_piece = chooseBlock(board);
            newBlokusBoard = placePiece(chosen_piece, board);
        } while (Arrays.deepEquals(newBlokusBoard, board)); // While the board remains unchanged (invalid move)

        return newBlokusBoard;
    }

    @Override
    public int[] rotateBlock(int[] piece) {
        if (piece.length > 2) {
            for (int i = 0; i < piece.length; i += 2) {
                piece[i] *= -1; // sign of x coordinate changed

                int temp_ind = piece[i];
                // x and y coordinates swapped
                piece[i] = piece[i+1];
                piece[i+1] = temp_ind;
            }
        }
        return piece;
    }

    public int[] flipBlock(int[] piece){
        if (piece.length > 2) {
            for (int j = 1; j < piece.length; j += 2) {
                piece[j] = piece[j] * -1; // signs of x coordinates changed to flip across y-axis
            }
        }
        return piece;
    }


    public char[][] placePiece(String pieceName, char[][] board) {
        //Starting positions (represented by 2D array index, not board index) are (4,4) and (9,9).
        char[][] newBlokusBoard = new char[board.length][];
        //Cloned board permits modification without making changes to original board (security measure).
        //To completely copy a 2D array you need to clone row by row
        for (int i = 0; i < board.length; i++) {
            newBlokusBoard[i] = board[i].clone();
        }

        int[] piece = playerPieceCollection.getPieces().get(pieceName);
        boolean placingPiece = false;

        while (!placingPiece) {
            System.out.println("Enter 'r' to rotate, 'f' to flip, or 'p' to place the gamepiece:");
            String action = Input.in.next();
            if (action.equalsIgnoreCase("r")) {
                piece = rotateBlock(piece);
                GamePiece.printPiece(piece, pieceSymbol);
            } else if (action.equalsIgnoreCase("f")) {
                piece = flipBlock(piece);
                GamePiece.printPiece(piece, pieceSymbol);
            } else if (action.equalsIgnoreCase("p")) {
                int row;
                int column;

                System.out.println("Enter x and y coordinates on the board:");
                column = Integer.parseInt(Input.in.next());
                row = 13 - Integer.parseInt(Input.in.next()); //Row taken away from 13 due to reversed indexing of the array.

                if (!Board.isValidMove(piece, board, row, column, this)) {
                    System.out.println("Move not valid.");
                    return newBlokusBoard;
                }

              // Position of parts of pieces based on offset from reference point
                 for (int i = 0; i < piece.length; i += 2) {
                     newBlokusBoard[row + piece[i]][column + piece[i+1]] = pieceSymbol;
                 }

                 playerPieceCollection.getPieces().remove(pieceName);
                 score.updatePoints(piece, this);
                 firstTurn = false;
                 placingPiece = true;
          } else {
                System.out.println("Invalid input. Either r, f or p should be input.");
          }
        }

        return newBlokusBoard;
    }

    public char[][] placePiece(String pieceName, char[][] board, int tileRow, int tileColumn) {
        //Starting positions (represented by 2D array index, not board index) are (4,4) and (9,9).
        char[][] newBlokusBoard = new char[board.length][];
        //Cloned board permits modification without making changes to original board (security measure).
        //To completely copy a 2D array you need to clone row by row
        for (int i = 0; i < board.length; i++) {
            newBlokusBoard[i] = board[i].clone();
        }

        int[] piece = playerPieceCollection.getPieces().get(pieceName);
        if (piece == null) {
            return null;
        }
        if (!Board.isValidMove(piece, board, tileRow, tileColumn, this)) {
            return null;
        }

        // Position of parts of pieces based on offset from reference point
        for (int i = 0; i < piece.length; i += 2) {
            newBlokusBoard[tileRow + piece[i]][tileColumn + piece[i+1]] = pieceSymbol;
        }

        playerPieceCollection.getPieces().remove(pieceName);
        score.updatePoints(piece, this);
        firstTurn = false;
        return newBlokusBoard;
    }

    private boolean pieceFound(int[] piece) {
        return piece != null;
    }

    //Getters and/or Setters below this point
    public String getName() {
        return this.name;
    }

    public char getSymbol() {
        return this.pieceSymbol;
    }

    public GamePieceCollection getPlayerPieceCollection() {
        return playerPieceCollection;
    }

    public boolean getFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    public Score getScore() {
        return this.score;
    }

    public boolean isCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

}
