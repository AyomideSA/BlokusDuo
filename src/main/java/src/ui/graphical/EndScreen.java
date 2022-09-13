/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src.ui.graphical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import src.BlokusGame;
import src.GameControl;
import java.io.File;

public class EndScreen extends ScreenAdapter {

  private BlokusGame blokusGame;
  private Stage stage;
  public static final float GAME_SUMMARY_WIDTH = 500;
  public static final float GAME_SUMMARY_HEIGHT = 200;

  public EndScreen(BlokusGame blokusGame) {
    this.blokusGame = blokusGame;
  }

  @Override
  public void show() {
    Skin skin = new Skin(Gdx.files.internal("assets" + File.separator + "plainJames" + File.separator + "plain-james-ui.json"));
    stage = new Stage();
    Window gameSummary = new Window("Summary", skin);
    Label summary = new Label("Player " + GameControl.player1.getName() + "(Black) Score: " + GameControl.player1.getScore().getPoints() + "\n\n" +
        "Player " + GameControl.player2.getName() + "(White) Score: " + GameControl.player2.getScore().getPoints() + "\n\n" +
        GameControl.result, skin);
    gameSummary.setWidth(GAME_SUMMARY_WIDTH);
    gameSummary.setHeight(GAME_SUMMARY_HEIGHT);
    gameSummary.addActor(summary);
    gameSummary.add(summary).pad(0, 0, 0, 0).row();
    gameSummary.setPosition(300,350);
    stage.addActor(gameSummary);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor((float) (1.0),(float) (154.0/255.0), (float) (0.0/255.0), 1); // Sets colour of screen
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw();
  }

  @Override
  public void dispose() {
    stage.dispose();
  }

}
