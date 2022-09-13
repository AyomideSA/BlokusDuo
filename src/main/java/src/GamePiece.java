/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src;

public class GamePiece {
    //This class will store all the available game pieces

    // Pieces in Format {rowOffset, columnOffset, rowOffset, columnOffset} where the offset is in relation to the
    // reference point of each piece (which has a row and column offset of 0,0)
    // -1 in terms of the rowOffset means you go up a row, +1 means you go down a row
    // Similarly, -1 in terms of the columnOffset means that you go back a row and +1 means that you go up a row
    // Offsets in relation to a 13x13 2D array, NOT the game board i.e. from top to bottom rows are numbered
    // 0,1,2.....13. That's why -1 for the rowOffset means that you go up a row

    //***MIGHT CHANGE THIS INTO AN ARRAY OF PIECES

    public static final int[] I1 = {0,0};
    public static final int[] I2 = {0,0,-1,0};
    public static final int[] I3 = {0,0,-1,0,-2,0};
    public static final int[] I4 = {0,0,-1,0,-2,0,-3,0};
    public static final int[] I5 = {0,0,-1,0,-2,0,-3,0,-4,0};
    public static final int[] V3 = {0,0,-1,0,0,1};
    public static final int[] L4 = {0,0,-1,0,-2,0,0,1};
    public static final int[] Z4 = {0,0,0,-1,-1,0,-1,1};
    public static final int[] O4 = {0,0,-1,0,-1,1,0,1};
    public static final int[] Z5 = {0,0,0,1,0,-1,-1,1,1,-1};
    public static final int[] P = {0,0,0,1,1,0,1,1,2,0};
    public static final int[] L5 = {0,0,0,1,0,2,0,3,-1,0};
    public static final int[] T5 = {0,0,0,1,0,-1,-1,0,-2,0};
    public static final int[] V5 = {0,0,0,1,-1,0,-2,0,0,2};
    public static final int[] N = {0,0,1,0,1,-1,0,1,0,2};
    public static final int[] T4 = {0,0,0,1,0,-1,-1,0};
    public static final int[] X = {0,0,0,1,0,-1,-1,0,1,0};
    public static final int[] W = {0,0,0,-1,1,-1,-1,0,-1,1};
    public static final int[] U = {0,0,1,0,-1,0,1,1,-1,1};
    public static final int[] F = {0,0,1,0,-1,0,0,-1,-1,1};
    public static final int[] Y = {0,0,0,1,0,-1,-1,0,0,2};

    static void printPiece(int[] gamePiece, char playerSymbol) {
        StringBuilder pieceString = new StringBuilder();
        // Similarly to how we use offsets in the game piece arrays, -4 is the starting row index in terms of the middle of a 9x9 array and 5 is the last row index (
        // 5 - (-4) = 9, 9 columns
        // Similarly, -4 is the starting column index and 5 is the starting row index
        for (int rowOffset = -4; rowOffset < 5; rowOffset++) {  // Imagining a 9x9 array and starting from middle cell (4,4)
                                                                // Each line of Stringbuilder will represent a line in the imaginary 9x9 array
            for (int columnOffset = -4; columnOffset < 5; columnOffset++) {
                if (isInPiece(rowOffset, columnOffset, gamePiece)) {
                    pieceString.append(playerSymbol).append(" ");
                }
                else {
                    pieceString.append("  ");
                }
            }
            pieceString.append("\n"); // Goes onto next "row" of our imaginary 9x9 array
        }

        String[] pieceStringByRows = pieceString.toString().split("\n"); // Each row of our array, including rows full of blank space stored in an array

        for (String pieceRow : pieceStringByRows) {
            if (pieceRow.contains(Character.toString(playerSymbol))) { // Will only return false when a row is just blank space, thus removing any useless blank space from output
                System.out.println(pieceRow);
            }
        }
    }

    private static boolean isInPiece(int rowOffset, int columnOffset, int[] piece) {
        for (int i = 0; i < piece.length;) {
            if (piece[i] == rowOffset && piece[i+1] == columnOffset) {
                return true;
            }
            i += 2;
        }
        return false;
    }

}

