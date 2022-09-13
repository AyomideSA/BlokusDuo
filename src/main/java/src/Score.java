/*
 * Team Name: BlokSus
 * Member Names/Student Numbers: Ayomide Sola-Ayodele (20338061)
 *                               Boris Sandoval (20437374)
 *                               Wiktoria Szczepaniak (20461424)
 */

package src;

public class Score implements Comparable<Score> {

    private int points;

    public Score() {
        points = 0;
    }

    public void updatePoints(int[] gamepiece, Player player) {
        int pointsToBeAdded = gamepiece.length / 2;
        // Extra points allocated according to Blokus duos rules
        if (player.getPlayerPieceCollection().getPieces().isEmpty()) { // "If a player places all 21 pieces on the board, they earn +15 points"
            pointsToBeAdded += 15;
            if (gamepiece[0] == 0 && gamepiece[1] == 0) { // "They receive an additional 5 bonus points if the last piece placed on the board was the smallest piece"
                pointsToBeAdded += 5;
            }
        }
        points = points + pointsToBeAdded;
    }

    public int getPoints() {
        return this.points;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.points, o.points);
    }

}
