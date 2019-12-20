package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.Score;

import static it.fmt.games.reversi.console.TextDrawer.*;

public abstract class ScoreTextDrawer {

    private ScoreTextDrawer() {

    }

    public static void drawScore(Score score) {
        println("  SCORE:");
        println(String.format("  - PLAYER_1 (O) : %d", score.getPlayer1Score()));
        println(String.format("  - PLAYER_2 (X) : %d", score.getPlayer2Score()));
//        System.out.println(String.format(PREFIX + "Player1 score: %d, Player2 score: %d", score.getPlayer1Score(), score.getPlayer2Score()));
    }
}
