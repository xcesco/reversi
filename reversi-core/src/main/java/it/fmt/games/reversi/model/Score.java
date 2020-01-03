package it.fmt.games.reversi.model;

public class Score {
    private final int player1Scores;
    private final int player2Scores;

    public Score(int player1Scores, int player2Scores) {
        this.player1Scores=player1Scores;
        this.player2Scores=player2Scores;
    }

    public int getPlayer1Score() {
        return player1Scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (player1Scores != score.player1Scores) return false;
        return player2Scores == score.player2Scores;
    }

    @Override
    public int hashCode() {
        int result = player1Scores;
        result = 31 * result + player2Scores;
        return result;
    }

    public int getPlayer2Score() {
        return player2Scores;
    }

}
