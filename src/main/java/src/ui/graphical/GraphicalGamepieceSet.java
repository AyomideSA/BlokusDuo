/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src.ui.graphical;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Rectangle;
import src.GameControl;
import src.GamePieceCollection;
import src.Player;
import java.util.Enumeration;
import java.util.Hashtable;

public class GraphicalGamepieceSet {
    private final GamePieceCollection gamePieceCollection;
    private final TextureRegion square;
    private final Player player;
    private final Hashtable<String, Float> Xorigins = new Hashtable<>(); // world X coordinates of each game piece's origin
    private final Hashtable<String, Float> Yorigins = new Hashtable<>(); // world Y coordinates of each game piece's origin
    private final String playerPiecesId;

    public GraphicalGamepieceSet(TextureRegion square, MapObjects mapObjects) {
        this.square = square;
        if (isBlack(square)) {
            player = GameControl.player1;
            playerPiecesId = "0";
            for (MapObject mapObject : mapObjects) {
                if (isBlackPiece(mapObject.getName())) {
                    Xorigins.put(mapObject.getName(), (float) mapObject.getProperties().get("x"));
                    Yorigins.put(mapObject.getName(), (float) mapObject.getProperties().get("y"));
                }
            }
        } else {
            player = GameControl.player2;
            playerPiecesId = "1";
            for (MapObject mapObject : mapObjects) {
                if (isWhitePiece(mapObject.getName())) {
                    Xorigins.put(mapObject.getName(), (float) mapObject.getProperties().get("x"));
                    Yorigins.put(mapObject.getName(), (float) mapObject.getProperties().get("y"));
                }
            }
        }
        gamePieceCollection = player.getPlayerPieceCollection();
    }

    public void draw(SpriteBatch batch) {
        int[] currentPieceXYCoordinates;
        String currentPiece;
        Enumeration<String> gamePieceCollectionKeys = gamePieceCollection.getPieces().keys();

        while (gamePieceCollectionKeys.hasMoreElements()) {
            currentPiece = gamePieceCollectionKeys.nextElement();
            currentPieceXYCoordinates = gamePieceCollection.getPieces().get(currentPiece);
            currentPiece = playerPiecesId + currentPiece;
            for (int i = 0; i < currentPieceXYCoordinates.length; i += 2) {
                batch.draw(square,
                    Xorigins.get(currentPiece) + square.getRegionWidth() * currentPieceXYCoordinates[i+1],
                    Yorigins.get(currentPiece) + square.getRegionHeight() * currentPieceXYCoordinates[i]*-1); // Y coordinates have to be flipped as, internally, board follows array indexing
            }
        }
    }

    public void flipAlongY(String pieceName) {
        Hashtable<String, int[]> pieces = gamePieceCollection.getPieces();
        pieces.put(pieceName, player.flipBlock(pieces.get(pieceName)));
    }

    public void rotateRight(String pieceName) {
        Hashtable<String, int[]> pieces = gamePieceCollection.getPieces();
        pieces.put(pieceName,player.rotateBlock(pieces.get(pieceName)));
    }

    public String isHit(float x, float y) {
        String hitPiece = "";
        int[] currentPieceXYCoordinates;
        Enumeration<String> gamePieceCollectionKeys = gamePieceCollection.getPieces().keys();
        String currentPiece;
        while (gamePieceCollectionKeys.hasMoreElements()) {
            currentPiece = gamePieceCollectionKeys.nextElement();
            currentPieceXYCoordinates = gamePieceCollection.getPieces().get(currentPiece);
            currentPiece = playerPiecesId + currentPiece;
            for (int i = 0; i < currentPieceXYCoordinates.length; i += 2) {
                Rectangle rectangle = new Rectangle(Xorigins.get(currentPiece) + currentPieceXYCoordinates[i + 1] * square.getRegionWidth(),
                    Yorigins.get(currentPiece) + (currentPieceXYCoordinates[i]*-1) * square.getRegionHeight(),
                    square.getRegionWidth(),
                    square.getRegionHeight());
                if (rectangle.contains(x, y)) {
                    hitPiece = currentPiece;
                }
            }
        }
        return hitPiece;
    }

    public void setPosition(float x, float y, String pieceName) {
        // set new position of the gamepiece, so that the pointer coordinates
        // are in the middle of the "origin" square ( Location(0,0) ).
        Xorigins.put(pieceName,  x - square.getRegionWidth() * 0.5f);
        Yorigins.put(pieceName, y - square.getRegionHeight() * 0.5f);
    }

    private boolean isBlack (TextureRegion square) {
        return square.equals(GameScreen.blackSquare);
    }

    private boolean isBlackPiece(String pieceName) {
        return pieceName.contains("0");
    }

    private boolean isWhitePiece(String pieceName) {
        return pieceName.contains("1");
    }

}
