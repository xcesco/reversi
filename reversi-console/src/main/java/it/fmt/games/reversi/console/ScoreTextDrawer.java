package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.Score;

import static it.fmt.games.reversi.console.TextDrawer.NEW_LINE;
import static it.fmt.games.reversi.console.TextDrawer.print;

public abstract class ScoreTextDrawer {

    private ScoreTextDrawer() {

    }

    public static void drawScore(Score score) {
        print(String.format("Score PLAYER_1 (O) : PLAYER_2 (X) ---> %d : %d"+NEW_LINE, score.getPlayer1Score(), score.getPlayer2Score()));
//        System.out.println(String.format(PREFIX + "Player1 score: %d, Player2 score: %d", score.getPlayer1Score(), score.getPlayer2Score()));
    }
}
