/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src.ui.graphical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import java.io.PrintStream;

public class MyEventHandler extends InputAdapter {

    GameScreen gameScreen;
    String selectedPiece;
    GraphicalGamepieceSet graphicalGamepieceSet;
    PrintStream myOut = new PrintStream(StartScreen.pipedOut);
    public static boolean moveMade;

    public MyEventHandler(GameScreen gameScreen, GraphicalGamepieceSet graphicalGamepieceSet) {
        this.gameScreen = gameScreen;
        this.selectedPiece = "";
        this.graphicalGamepieceSet = graphicalGamepieceSet;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean eventHandled = false;
        if (button == Input.Buttons.LEFT) {
            Vector3 coord = unprojectScreenCoordinates(Gdx.input.getX(),Gdx.input.getY());
            selectedPiece = graphicalGamepieceSet.isHit(coord.x, coord.y);
            if (!selectedPiece.isEmpty()) {
                gameScreen.setBannerText(GameScreen.FLIP_OR_ROTATE_MESSAGE);
                eventHandled = true;
            }
        }
        return eventHandled;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        boolean eventHandled = false;
        if (!selectedPiece.isEmpty()) {
            Vector3 worldCoords = unprojectScreenCoordinates(Gdx.input.getX(),Gdx.input.getY());
            double tileX = Math.floor((double) worldCoords.x / 32);
            double tileY = Math.floor((double) worldCoords.y / 32);
            myOut.println(selectedPiece.substring(1) + "," + tileX + "," + tileY);
            moveMade = true;
            selectedPiece = "";
            gameScreen.setBannerText(GameScreen.CLICK_AND_DRAG_MESSAGE);
            eventHandled = true;
        }
        return eventHandled;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        boolean eventHandled = false;
        if (!selectedPiece.isEmpty()) {
            Vector3 coord = unprojectScreenCoordinates(screenX,screenY);
            graphicalGamepieceSet.setPosition(coord.x,coord.y, selectedPiece);
            eventHandled = true;
        }
        return eventHandled;
    }

    Vector3 unprojectScreenCoordinates(int x, int y) {
        Vector3 screenCoordinates = new Vector3(x, y,0);
        Vector3 worldCoordinates = gameScreen.getCamera().unproject(screenCoordinates);
        return worldCoordinates;
    }

    @Override
    public boolean keyDown (int keycode) {
        boolean eventHandled = false;
        if (!selectedPiece.isEmpty()) {
            switch (keycode) {
                case Input.Keys.F:
                    graphicalGamepieceSet.flipAlongY(selectedPiece.substring(1));
                    eventHandled = true;
                    break;
                case Input.Keys.R:
                    graphicalGamepieceSet.rotateRight(selectedPiece.substring(1));
                    eventHandled = true;
                    break;
            }
        }
        return eventHandled;
    }

}
