/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import src.BlokusGame;
import src.Board;
import src.GameControl;
import src.HumanPlayer;
import src.ui.text.TextUI;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int firstTurnPlayer = 0; // Will stay at 0 if -X or -O are not included as command line arguments
        boolean graphical = false;
        Board blokusBoard = new Board();

        for (String arg: args) { // Every command line argument is checked
            if (firstTurnPlayer == 0 && arg.equalsIgnoreCase("-x")) {
                firstTurnPlayer = 1;
            }
            if (firstTurnPlayer == 0 && arg.equalsIgnoreCase("-o")) {
                firstTurnPlayer = 2;
            }
            if (arg.equalsIgnoreCase("-gui")) {
                graphical = true;
            }
        }
        if (firstTurnPlayer == 0) {
            firstTurnPlayer = Board.chooseRandomPlayer();
        }
        if (graphical) {
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setTitle("BlokSus");
            config.setWindowedMode(1024, 812);
            config.setResizable(false);
            BlokusGame blokusGame = new BlokusGame(firstTurnPlayer); // Handles switching of screens in libGDX thread
            GameControl gameControl = new GameControl(blokusBoard, blokusGame);
            Thread gameThread = new Thread(gameControl);
            gameThread.start(); // Thread that contains game logic is run concurrently with libgdx application
            new Lwjgl3Application(blokusGame, config);  // New app (libgdx thread) created. Window pops up when it is run
            if (gameControl.isRunning()) {
                gameControl.stop();
            }
        } else { // Uses Text Interface if graphical is not specified
            TextUI ui = new TextUI();
            HumanPlayer player1 = new HumanPlayer(1);
            HumanPlayer player2 = new HumanPlayer(2);
            if (firstTurnPlayer == 1) {
                player1.setCurrentTurn(true);
            } else {
                player2.setCurrentTurn(true);
            }
            int turnSkipCount = 0;
            while (true) {
                ui.printBoard(blokusBoard.getGameBoard());
                ui.printPlayerSummary(player1.getName(), player1.getPlayerPieceCollection(), player1.getSymbol(), player1.getScore().getPoints());
                ui.printPlayerSummary(player2.getName(), player2.getPlayerPieceCollection(), player2.getSymbol(), player2.getScore().getPoints());
                if (player1.isCurrentTurn()) {
                    if (Board.atLeastOneValidMove(blokusBoard.getGameBoard(), player1)) {
                        blokusBoard.setGameBoard(player1.updatePieceOnBoard(blokusBoard.getGameBoard()));
                        turnSkipCount = 0;
                    } else {
                        turnSkipCount++;
                    }
                }
                if (player2.isCurrentTurn()) {
                    if (Board.atLeastOneValidMove(blokusBoard.getGameBoard(), player2)) {
                        blokusBoard.setGameBoard(player2.updatePieceOnBoard(blokusBoard.getGameBoard()));
                        turnSkipCount = 0;
                    } else {
                        turnSkipCount++;
                    }
                }
                if (turnSkipCount == 2) { // Both player's turned being skipped in a row implies that there are no valid moves left for both players
                    break;
                }
                player1.setCurrentTurn(!player1.isCurrentTurn());
                player2.setCurrentTurn(!player2.isCurrentTurn());
            }
        }
    }

}
