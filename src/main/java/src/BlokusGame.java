/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import src.ui.graphical.StartScreen;

public class BlokusGame extends Game {

  StartScreen startScreen;
  private final int firstTurnPlayer;
  static boolean running = false;

  public BlokusGame(int firstTurnPlayer) {
    this.firstTurnPlayer = firstTurnPlayer;
    this.startScreen = new StartScreen(this);
  }

  public void postRunnable(Runnable r) {
    Gdx.app.postRunnable(r);
  }

  @Override
  public void create() {
    running = true; // Needed so game control thread knows that it is ok to start running game logic
    setScreen(startScreen);
  }

  public int getFirstTurnPlayer() {
    return firstTurnPlayer;
  }

}
