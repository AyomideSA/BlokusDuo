/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src.ui.text;

import src.Board;
import src.GamePieceCollection;
import src.ui.GameStateRenderer;

public class TextUI implements GameStateRenderer {

  @Override
  public void printPlayerSummary(String name, GamePieceCollection piecesRemaining, char symbol, int score) {
    System.out.println("Player " + name + " (" + symbol + ") gamepieces " + piecesRemaining + "\n" +
        "Score: " + score);
  }

  @Override
  public void printBoard(char[][] gameBoard) {
      //Prints Skeleton of board.
      System.out.println("\n");
      System.out.println("BLOKUS DUOS\n\n");
      System.out.println("  \t---------------------------------------------------------");

      for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
        //Indexing rows.
        int b=13-i;
        System.out.print(b);
        System.out.print("\t| ");
        for (int j = 0; j < Board.BOARD_WIDTH; j++) {
          System.out.print(gameBoard[i][j] + " | ");
        }
        System.out.println();
        System.out.println("  \t---------------------------------------------------------");
      }

      System.out.print("   ");

      //Indexing columns
      for (int i = 0; i < Board.BOARD_WIDTH; i++) {
        //Formatting needed numbers >=10 are centred below column
        if (i == 10) {
          System.out.printf("%5s", i);
        } else {
          //Formatting needed so digits <10 are centred below columns
          System.out.printf("%4s", i);
        }
      }
      System.out.println("\n\n");
    }

}
