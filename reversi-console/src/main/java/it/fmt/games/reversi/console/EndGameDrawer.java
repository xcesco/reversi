package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.GameStatus;
import it.fmt.games.reversi.model.Score;

public class EndGameDrawer extends Drawer {
    private GameSnapshot game;

    public EndGameDrawer(GameSnapshot game) {
        this.game = game;
    }

    public void drawEndGame() {
        GameStatus status = game.getStatus();
        Score score = game.getScore();
        switch (status) {
            case DRAW:
                draw("Draw");
                break;
            case PLAYER1_WIN:
                draw(String.format("Player 2 wins: player1 pieces: %d, player2 pieces: %d", score.getPlayer1Score(), score.getPlayer2Score()));
                break;
            case PLAYER2_WIN:
                draw(String.format("Player 1 wins: player1 pieces: %d, player2 pieces: %d", score.getPlayer1Score(), score.getPlayer2Score()));
                break;
        }
    }
}
