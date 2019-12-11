package it.fmt.games.reversi;

public abstract class GameStatusFactory {
    public static GameStatus create(AvailableMoves availableMoves, Score score) {
        if (availableMoves.isAnyAvailableMoves()) {
            return GameStatus.RUNNING;
        } else {
            int scoreDifference = score.getPlayer1Score() - score.getPlayer2Score();
            if (scoreDifference == 0) {
                return GameStatus.DRAW;
            } else if (scoreDifference > 0) {
                return GameStatus.PLAYER1_WIN;
            } else {
                return GameStatus.PLAYER2_WIN;
            }
        }
    }
}
