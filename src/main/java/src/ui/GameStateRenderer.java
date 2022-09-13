/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src.ui;

import src.GamePieceCollection;

public interface GameStateRenderer {

  void printPlayerSummary(String name, GamePieceCollection piecesRemaining, char symbol, int score);

  void printBoard(char[][] gameBoard);

}
