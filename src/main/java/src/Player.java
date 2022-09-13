/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src;

public interface Player {
    //Ensures standard methods will be implemented for both src.HumanPlayer and (later) AI

    String chooseBlock(char [][] board);

    int[] rotateBlock(int[] piece);

    int[] flipBlock(int[] piece);

    char[][] placePiece(String pieceName, char[][] board);

    GamePieceCollection getPlayerPieceCollection();

    char getSymbol();

    boolean getFirstTurn();

    String getName();

    Score getScore();

    boolean isCurrentTurn();

    void setCurrentTurn(boolean turn);
}
