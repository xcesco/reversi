package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.GameStatus;
import it.fmt.games.reversi.model.Score;

import static it.fmt.games.reversi.console.TextDrawer.*;

public class EndGameTextDrawer {

    public static void drawEndGame(GameStatus status, Score score) {
        print(NEW_LINE);
        switch (status) {
            case DRAW:
                print("!!!!!!!!!!!!!!!!!!!!!"+NEW_LINE);
                print("!!!!!!! DRAW !!!!!!!!"+NEW_LINE);
                print(String.format("!! (O) %d : %d (X) !!"+NEW_LINE, score.getPlayer1Score(), score.getPlayer2Score()));
                print("!!!!!!!!!!!!!!!!!!!!!"+NEW_LINE);
                break;
            case PLAYER1_WIN:
                print("!!!!!!!!!!!!!!!!!!!!!!!!!"+NEW_LINE);
                print("!!!!! PLAYER_1 WINS !!!!!"+NEW_LINE);
                print(String.format("!!!! (O) %d : %d (X) !!!!"+NEW_LINE, score.getPlayer1Score(), score.getPlayer2Score()));
                print("!!!!!!!!!!!!!!!!!!!!!!!!!"+NEW_LINE);
                break;
            case PLAYER2_WIN:
                print("!!!!!!!!!!!!!!!!!!!!!!!!!"+NEW_LINE);
                print("!!!!! PLAYER_2 WINS !!!!!"+NEW_LINE);
                print(String.format("!!!! (O) %d : %d (X) !!!!"+NEW_LINE, score.getPlayer1Score(), score.getPlayer2Score()));
                print("!!!!!!!!!!!!!!!!!!!!!!!!!"+NEW_LINE);
                break;
        }
    }
}
