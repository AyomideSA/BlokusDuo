/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

import org.junit.jupiter.api.Test;
import src.*;
import static org.testng.Assert.*;
import java.util.Enumeration;

public class GamePieceCollectionTest {

    private String pieceKeysString = "Y X V5 W U V3 P N T5 T4 F Z5 Z4 I5 I4 L5 I3 L4 I2 O4 I1";
    private HumanPlayer testPlayer1 = new HumanPlayer(1, "Wiki");
    private static GamePieceCollection testPieces;

    @Test
    void getPieces() {
        GamePieceCollection player1Collection = testPlayer1.getPlayerPieceCollection();
        GamePieceCollection testPieces = new GamePieceCollection();
        // Player collection should have all pieces //
        assertEquals(testPieces, player1Collection);

        testPieces.getPieces().remove("Y");
        player1Collection.getPieces().remove("Y");
        // hashatble that has been updated should be returned //
        assertEquals(testPieces, player1Collection);

        testPieces.getPieces().remove("I1");
        assertNotEquals(testPieces, player1Collection);
    }

    @Test
    void testToString() {
        GamePieceCollection player1Collection = testPlayer1.getPlayerPieceCollection();
        Enumeration<String> piecesKeys = player1Collection.getPieces().keys();
        String currentKey;

        // Removes piece from hashtable each loop //
        while (piecesKeys.hasMoreElements()) {
            currentKey = piecesKeys.nextElement();
            // toString should be updated accordingly after each piece is removed
            assertEquals(pieceKeysString.trim(), player1Collection.toString());
            player1Collection.getPieces().remove(currentKey);
            pieceKeysString = pieceKeysString.substring(pieceKeysString.indexOf(currentKey)+2);
        }
    }

    @Test
    void equals() {
        GamePieceCollection player1Collection = testPlayer1.getPlayerPieceCollection();
        GamePieceCollection testPieces = new GamePieceCollection();

        assertEquals(player1Collection, testPieces);

        player1Collection.getPieces().remove("Y");
        assertNotEquals(player1Collection, testPieces);
    }

}