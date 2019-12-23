package it.fmt.games.reversi.console.drawers;

import it.fmt.games.reversi.model.GameStatus;
import it.fmt.games.reversi.model.Score;

import static it.fmt.games.reversi.console.drawers.TextDrawer.*;

public class EndGameDrawer {

    public static void drawEndGame(GameStatus status, Score score) {
        println("");
        switch (status) {
            case DRAW:
                println("  !!!!!!!!!!!!!!!!!!!!!");
                println("  !!!!!!! DRAW !!!!!!!!");
                println(String.format("  !! (O) %d : %d (X) !!", score.getPlayer1Score(), score.getPlayer2Score()));
                println("  !!!!!!!!!!!!!!!!!!!!!");
                break;
            case PLAYER1_WIN:
                println("  !!!!!!!!!!!!!!!!!!!!!!!!!");
                println("  !!!!! PLAYER_1 WINS !!!!!");
                println(String.format("  !!!! (O) %d : %d (X) !!!!", score.getPlayer1Score(), score.getPlayer2Score()));
                println("  !!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
            case PLAYER2_WIN:
                println("  !!!!!!!!!!!!!!!!!!!!!!!!!");
                println("  !!!!! PLAYER_2 WINS !!!!!");
                println(String.format("  !!!! (O) %d : %d (X) !!!!", score.getPlayer1Score(), score.getPlayer2Score()));
                println("  !!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
        }
    }
}
