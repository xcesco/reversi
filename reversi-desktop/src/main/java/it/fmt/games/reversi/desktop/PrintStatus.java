package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.model.GameStatus;

public class PrintStatus {

    public String getWinner() {
        return SetWinner();
    }

    private GameStatus status;


    public PrintStatus(GameStatus status) {
        this.status = status;
    }

    private String SetWinner() {
        String winner = "";
        switch (status) {
            case PLAYER1_WIN:
                winner = "BLACK is Winner";
                break;
            case PLAYER2_WIN:
                winner = "WHITE is Winner";
                break;
            case DRAW:
            default:
                winner = "Draw";
                break;
        }
        return winner;
    }

}
