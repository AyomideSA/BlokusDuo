/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src;

import src.ui.graphical.GameScreen;
import src.ui.graphical.MyEventHandler;
import src.ui.graphical.StartScreen;
import java.io.IOException;
import java.io.PipedInputStream;
import java.util.Scanner;

public class GameControl implements Runnable {

  public static Board board;
  public static HumanPlayer player1;
  public static HumanPlayer player2;
  public static boolean gameEnd = false;
  public static String result;
  private BlokusGame blokusGame;
  private PipedInputStream pipedIn;
  private static String[] move = new String[3];
  private char[][] newBoard;
  String pieceName;
  public static double x = 0;
  public static double y = 0;
  public static boolean playersCreated = false;

  public GameControl(Board board, BlokusGame blokusGame) throws IOException {
    pipedIn = new PipedInputStream(StartScreen.pipedOut);
    Input.in = new Scanner(pipedIn);
    GameControl.board = board;
    this.blokusGame = blokusGame;
  }

  @Override
  public void run() {
    while (!gameEnd) {
      try {
        Thread.sleep(500); // Sleep inside loop to ensure that game control thread doesn't get too ahead of libgdx thread
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (BlokusGame.running) {
        if (!playersCreated && StartScreen.inputRan) {
          blokusGame.postRunnable(new Runnable() {
            @Override
            public void run() {
              player1 = new HumanPlayer( 1, Input.in.next());
              player2 = new HumanPlayer( 2, Input.in.next());
              if (blokusGame.getFirstTurnPlayer() == 1) {
                player1.setCurrentTurn(true);
              } else {
                player2.setCurrentTurn(true);
              }
              playersCreated = true;
            }
          });
        } else if (GameScreen.running) {
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          blokusGame.postRunnable(new Runnable() {
            @Override
            public void run() {
              if (!Board.atLeastOneValidMove(board.getGameBoard(), player1) && !Board.atLeastOneValidMove(board.getGameBoard(), player2)) {
                if (player1.getScore().compareTo(player2.getScore()) == 0) {
                  result = "Game has ended in a draw!";
                } else if (player1.getScore().compareTo(player2.getScore()) < 0) {
                  result = "Congratulations " + player2.getName() + "! You are the winner";
                } else  {
                  result = "Congratulations " + player1.getName() + "! You are the winner";
                }
                stop();
              } else if (MyEventHandler.moveMade) {
                move = Input.in.next().split(",");
                if (player1.isCurrentTurn()) {
                  pieceName = move[0];
                  x = Double.parseDouble(move[1]);
                  y = Double.parseDouble(move[2]);
                  if (Board.atLeastOneValidMove(board.getGameBoard(), player1)) {
                    newBoard = player1.placePiece(pieceName, board.getGameBoard(), 18 - (int) y, (int) x - 9);
                    if (newBoard != null) {
                      board.setGameBoard(newBoard);
                      player1.setCurrentTurn(!player1.isCurrentTurn());
                      player2.setCurrentTurn(true);
                    }
                  } else {
                    player1.setCurrentTurn(!player1.isCurrentTurn());
                    player2.setCurrentTurn(true);
                  }
                  MyEventHandler.moveMade = false;
                } else {
                  pieceName = move[0];
                  x = Double.parseDouble(move[1]);
                  y = Double.parseDouble(move[2]);
                  if (Board.atLeastOneValidMove(board.getGameBoard(), player2)) {
                    newBoard = player2.placePiece(pieceName, board.getGameBoard(), 18 - (int) y, (int) x - 9);
                    if (newBoard != null) {
                      board.setGameBoard(newBoard);
                      player2.setCurrentTurn(!player2.isCurrentTurn());
                      player1.setCurrentTurn(true);
                    }
                  } else {
                    player2.setCurrentTurn(!player2.isCurrentTurn());
                    player1.setCurrentTurn(true);
                  }
                  MyEventHandler.moveMade = false;
                }
              }
            }
          });
        }
      }
    }
  }

  public void stop() {
    gameEnd = true;
  }

  public boolean isRunning() {
    return !gameEnd;
  }

}

