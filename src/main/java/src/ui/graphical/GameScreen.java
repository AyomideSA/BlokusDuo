/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src.ui.graphical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import src.BlokusGame;
import src.Board;
import src.GameControl;
import src.GamePieceCollection;
import src.ui.GameStateRenderer;
import java.io.File;

public class GameScreen extends ScreenAdapter implements GameStateRenderer {

  private final BlokusGame blokusGame;
  private String bannerText;
  private SpriteBatch batch;
  private OrthographicCamera camera;
  private TiledMap tiledMap;
  public static TiledMapTileLayer tileLayer;
  public static boolean running = false;
  private TiledMapTileLayer.Cell whiteTile;
  private TiledMapTileLayer.Cell blackTile;
  private MyEventHandler eventHandlerBlack;
  private MyEventHandler eventHandlerWhite;
  private Skin skin;
  private TiledMapRenderer mapRenderer;
  public static TextureRegion blackSquare;
  public static TextureRegion whiteSquare;
  private float bannerX;
  private float bannerY;
  private BitmapFont helvetique;
  private GraphicalGamepieceSet graphicalGamepieceSetBlack;
  private GraphicalGamepieceSet graphicalGamepieceSetWhite;
  public final static String CLICK_AND_DRAG_MESSAGE = "Click and drag the gamepiece.";
  public final static String FLIP_OR_ROTATE_MESSAGE = "Press 'f' to flip, or 'r' to rotate the gamepiece.";
  public final static int COLUMN_OFFSET = 9;
  public final static int ROW_OFFSET = 18;

  public GameScreen(BlokusGame blokusGame) {
    running = true;
    this.blokusGame = blokusGame;
  }

  @Override
  public void show() {
    tiledMap = new TmxMapLoader().load("assets" + File.separator + "tiledMap" + File.separator + "play.tmx"); // Tiled map gotten from assets folder
    batch = new SpriteBatch();
    camera = new OrthographicCamera();

    TiledMapImageLayer imageLayer = (TiledMapImageLayer) tiledMap.getLayers().get(0);
    tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(1);
    MapLayer objectLayer = tiledMap.getLayers().get(2);
    MapObjects mapObjects = objectLayer.getObjects();
    mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    whiteTile = new TiledMapTileLayer.Cell();
    blackTile = new TiledMapTileLayer.Cell();
    whiteTile.setTile(tiledMap.getTileSets().getTileSet("tiles").getTile(4));
    blackTile.setTile(tiledMap.getTileSets().getTileSet("tiles").getTile(5));

    skin = new Skin(Gdx.files.internal("assets" + File.separator + "mySkin" + File.separator + "myskin.json"));
    blackSquare = skin.getRegion("game_square_black");
    whiteSquare = skin.getRegion("game_square_white");
    helvetique = skin.getFont("helvetique");
    bannerText = "";
    bannerX = 10.0f;
    bannerY = imageLayer.getTextureRegion().getRegionHeight() + helvetique.getCapHeight()*1.5f;

    graphicalGamepieceSetBlack = new GraphicalGamepieceSet(blackSquare, mapObjects);
    graphicalGamepieceSetWhite = new GraphicalGamepieceSet(whiteSquare, mapObjects);

    eventHandlerBlack = new MyEventHandler(this, graphicalGamepieceSetBlack);
    eventHandlerWhite = new MyEventHandler(this, graphicalGamepieceSetWhite);

    if (blokusGame.getFirstTurnPlayer() == 1) {
      Gdx.input.setInputProcessor(eventHandlerBlack);
      GameControl.player1.setCurrentTurn(true);
      setBannerText("Player " + GameControl.player1.getName() + " " + CLICK_AND_DRAG_MESSAGE);
    } else {
      Gdx.input.setInputProcessor(eventHandlerWhite);
      GameControl.player2.setCurrentTurn(true);
      setBannerText("Player " + GameControl.player2.getName() + " " + CLICK_AND_DRAG_MESSAGE);
    }
  }

  @Override
  public void render (float delta) {
    Gdx.gl.glClearColor((float) (1.0),(float) (154.0/255.0), (float) (0.0/255.0), 1); // Sets colour of screen
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    camera.update();
    printBoard(GameControl.board.getGameBoard());
    mapRenderer.setView(camera);
    mapRenderer.render();
    if (GameControl.gameEnd) {
      blokusGame.setScreen(new EndScreen(blokusGame));
    } else if (GameControl.player1.isCurrentTurn()) {
      Gdx.input.setInputProcessor(eventHandlerBlack);
      setBannerText("Player " + GameControl.player1.getName() + "(Black) " + CLICK_AND_DRAG_MESSAGE);
    } else {
      Gdx.input.setInputProcessor(eventHandlerWhite);
      setBannerText("Player " + GameControl.player2.getName() + "(White) " + CLICK_AND_DRAG_MESSAGE);
    }
    batch.begin();
    graphicalGamepieceSetBlack.draw(batch);
    graphicalGamepieceSetWhite.draw(batch);
    helvetique.draw(batch,bannerText,bannerX,bannerY);
    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    skin.dispose();
    tiledMap.dispose();
  }

  @Override
  public void printPlayerSummary(String name, GamePieceCollection piecesRemaining, char symbol, int score) {

  }

  @Override
  public void printBoard(char[][] gameBoard) {
    for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
      for (int j = 0; j < Board.BOARD_WIDTH; j++) {
        if (gameBoard[i][j] == 'X'){
          tileLayer.setCell(COLUMN_OFFSET+j, ROW_OFFSET-i, blackTile);
        } else if (gameBoard[i][j] == 'O') {
          tileLayer.setCell(COLUMN_OFFSET+j, ROW_OFFSET-i, whiteTile);
        }
      }
    }
  }

  public void setBannerText(String text) {
    bannerText = text;
  }

  public Camera getCamera() {
    return camera;
  }

}
