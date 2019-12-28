package it.fmt.games.reversi.console.drawers;

import it.fmt.games.reversi.model.GameStatus;
import it.fmt.games.reversi.model.Score;

import static it.fmt.games.reversi.console.drawers.TextDrawer.*;

public class EndGameDrawer {

    public static void drawEndGame(GameStatus status, Score score) {
        println("");
        switch (status) {
            case DRAW:
                println(3,"!!!!!!!!!!!!!!!!!!!!!");
                println(3,"!!!!!!! DRAW !!!!!!!!");
                println(3,String.format("!! (O) %d : %d (X) !!", score.getPlayer1Score(), score.getPlayer2Score()));
                println(3,"!!!!!!!!!!!!!!!!!!!!!");
                break;
            case PLAYER1_WIN:
                println(3,"!!!!!!!!!!!!!!!!!!!!!!!!!");
                if (player1AsString == "CPU_1")
                    println(3,"!!!!!! "+player1AsString+"  WINS !!!!!!");
                else
                    println(3,"!!!!! "+player1AsString+" WINS !!!!!");
                println(3,String.format("!!!! (O) %d : %d (X) !!!!", score.getPlayer1Score(), score.getPlayer2Score()));
                println(3,"!!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
            case PLAYER2_WIN:
                println(3,"!!!!!!!!!!!!!!!!!!!!!!!!!");
                if (player2AsString == "CPU_2")
                    println(3,"!!!!!! "+player2AsString+"  WINS !!!!!!");
                else
                    println(3,"!!!!! "+player2AsString+" WINS !!!!!");
                println(3,String.format("!!!! (O) %d : %d (X) !!!!", score.getPlayer1Score(), score.getPlayer2Score()));
                println(3,"!!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
        }
    }
}
