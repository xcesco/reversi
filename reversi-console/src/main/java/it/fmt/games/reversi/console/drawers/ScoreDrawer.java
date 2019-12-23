package it.fmt.games.reversi.console.drawers;

import it.fmt.games.reversi.model.Score;

import static it.fmt.games.reversi.console.drawers.TextDrawer.*;

public abstract class ScoreDrawer {

    private ScoreDrawer() {

    }

    public static void drawScore(Score score) {
        println("  SCORE:");
        println(String.format(PREFIX+"- PLAYER_1 (O) : %d", score.getPlayer1Score()));
        println(String.format(PREFIX+"- PLAYER_2 (X) : %d", score.getPlayer2Score()));
    }
}
