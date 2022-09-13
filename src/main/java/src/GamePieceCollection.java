/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

public class GamePieceCollection {
    //This class will hold an alterable collection of game pieces.
    //an instance of each collection will be created for both players.

    private Hashtable<String, int[]> pieces = new Hashtable<>();

    public GamePieceCollection() {
        //All game pieces from class Gamepieces inserted into hashtable.
        //Hashtable will help with keeping track of pieces and any alterations.
        //*REFER TO GAMEPIECES CLASS
        
        pieces.put("I1", GamePiece.I1.clone());
        pieces.put("I2", GamePiece.I2.clone());
        pieces.put("I3", GamePiece.I3.clone());
        pieces.put("I4",GamePiece.I4.clone());
        pieces.put("I5", GamePiece.I5.clone());
        pieces.put("V3", GamePiece.V3.clone());
        pieces.put("L4", GamePiece.L4.clone());
        pieces.put("Z4",GamePiece.Z4.clone());
        pieces.put("O4", GamePiece.O4.clone());
        pieces.put("L5", GamePiece.L5.clone());
        pieces.put("T5", GamePiece.T5.clone());
        pieces.put("V5",GamePiece.V5.clone());
        pieces.put("N", GamePiece.N.clone());
        pieces.put("Z5", GamePiece.Z5.clone());
        pieces.put("T4", GamePiece.T4.clone());
        pieces.put("P",GamePiece.P.clone());
        pieces.put("W", GamePiece.W.clone());
        pieces.put("U", GamePiece.U.clone());
        pieces.put("F", GamePiece.F.clone());
        pieces.put("X",GamePiece.X.clone());
        pieces.put("Y",GamePiece.Y.clone());
    }

    public Hashtable<String, int[]> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        //Prints keys in hashtable (pieces left).
        StringBuilder pieces_left = new StringBuilder();
        Enumeration<String> piece_keys = pieces.keys();
        //Method keys() returns an enumeration. Had to refer to documentation.

        while(piece_keys.hasMoreElements()) {
            pieces_left.append(piece_keys.nextElement()).append(" ");
        }
        return pieces_left.toString().trim();
    }

    // Method overridden for use in testing
    @Override
    public boolean equals(Object o) {
        GamePieceCollection collection;

        if (o instanceof GamePieceCollection) {
            collection = (GamePieceCollection) o;
        } else {
            return false;
        }

        Enumeration<String> piece_keys = pieces.keys();
        String currentKey;

        while (piece_keys.hasMoreElements()) {
            currentKey = piece_keys.nextElement();
            if (!Arrays.equals(this.getPieces().get(currentKey), collection.pieces.get(currentKey))) {
                return false;
            }
        }

        return true;
    }

}
