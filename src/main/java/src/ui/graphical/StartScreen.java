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
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import src.BlokusGame;
import java.io.File;
import java.io.PipedOutputStream;
import java.io.PrintStream;

public class StartScreen extends ScreenAdapter {

  public static PipedOutputStream pipedOut = new PipedOutputStream();
  private TextButton startButton;
  private TextButton confirmationButton;
  private Label playerTurn;
  private Window startConfirmation;
  private Stage stage; // Things like buttons, windows, and text buttons are all "actors" on the stage. The stage is where we put all of them together
  Skin skin; // Basically used to give our actors of our stage a nice look. While the buttons may exist we need to give them a suit to wear
  private TextField inputPlayer1;
  private TextField inputPlayer2;
  private Label instructionsPlayer1;
  private Label instructionsPlayer2;
  private String player1Name;
  private String player2Name;
  private final BlokusGame blokusGame;
  public static boolean inputRan = false;

  public StartScreen(BlokusGame blokusGame) {
    this.blokusGame = blokusGame;
  }

  @Override
  public void show () {
    PrintStream myOut = new PrintStream(pipedOut);
    skin = new Skin(Gdx.files.internal("assets" + File.separator + "plainJames" + File.separator + "plain-james-ui.json"));

    inputPlayer1 = new TextField("", skin);
    inputPlayer2 = new TextField("", skin);
    inputPlayer1.setPosition(500, 500);
    inputPlayer2.setPosition(500, 400);

    startConfirmation = new Window("Attention", skin);
    startConfirmation.setPosition(300,350);

    confirmationButton = new TextButton("ok", skin);
    startConfirmation.setWidth(400);
    startConfirmation.setHeight(200);

    startButton = new TextButton("Start Game!", skin);
    startButton.addListener(new ChangeListener() { // Detects click input from user and calls changed() when button is clicked
      @Override
      public void changed(ChangeListener.ChangeEvent event, Actor actor) {
        player1Name = inputPlayer1.getText();
        player2Name = inputPlayer2.getText();
        if (player1Name.isEmpty()) {
          player1Name = "1";
        }
        myOut.println(player1Name);
        if (player2Name.isEmpty()) {
          player2Name = "2";
        }
        myOut.println(player2Name);
        StartScreen.inputRan = true; // Needed so game control thread knows that it is ok to stop looking for input for creating players

        startButton.remove();
        inputPlayer1.remove();
        inputPlayer2.remove();
        instructionsPlayer1.remove();
        instructionsPlayer2.remove();
        stage.addActor(startConfirmation);

        if (blokusGame.getFirstTurnPlayer() == 1) {
          playerTurn = new Label(player1Name + " goes first!", skin);
        } else {
          playerTurn = new Label(player2Name + " goes first!" , skin);
        }

        startConfirmation.add(playerTurn).pad(10, 0, 0, 0).row();
        startConfirmation.add(confirmationButton).pad(20, 0, 20, 0).row();

        confirmationButton.addListener(new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            blokusGame.setScreen(new GameScreen(blokusGame));
          }
        });
      }
    });
    startButton.setY(300);
    startButton.setX(450);

    instructionsPlayer1 = new Label("Enter your name player one (X)", skin);
    instructionsPlayer2 = new Label("Enter your name player two (O)", skin);
    instructionsPlayer1.setPosition(150, 510);
    instructionsPlayer2.setPosition(150, 410);

    stage = new Stage();
    // All interactive components of screen are put into out stage
    stage.addActor(startButton);
    stage.addActor(inputPlayer1);
    stage.addActor(inputPlayer2);
    stage.addActor(instructionsPlayer1);
    stage.addActor(instructionsPlayer2);
    Gdx.input.setInputProcessor(stage); // Makes it so that the components (actors) on our stage can get input from user
  }

  @Override
  public void render (float delta) {
    Gdx.gl.glClearColor((float) (1.0),(float) (154.0/255.0), (float) (0.0/255.0), 1); // Sets colour of screen
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw(); // Reveals all actors
  }

  @Override
  public void dispose () {
    stage.dispose();
    Gdx.input.setInputProcessor(null);
    Gdx.app.exit();
  }

}