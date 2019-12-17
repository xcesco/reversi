package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.Score;

public class ScoreDrawer extends Drawer {
    private Score score;

    public ScoreDrawer(Score score) {
        this.score = score;
    }

    public void drawScore() {
        draw(String.format("Player1 score: %d, Player2 score: %d", score.getPlayer1Score(), score.getPlayer2Score()));
//        System.out.println(String.format(PREFIX + "Player1 score: %d, Player2 score: %d", score.getPlayer1Score(), score.getPlayer2Score()));
    }
}
