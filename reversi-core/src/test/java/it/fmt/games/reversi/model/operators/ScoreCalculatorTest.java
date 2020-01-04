package it.fmt.games.reversi.model.operators;

import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.model.Score;
import it.fmt.games.reversi.support.BoardReader;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreCalculatorTest {

    @Test
    public void noScore() {
        Board board = new Board();

        Score score = ScoreCalculator.computeScore(board);

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

    @Test
    public void onePiecePlayer1() {
        Board board = new Board();
        board.setCell(Coordinates.of("a1"), Piece.PLAYER_1);

        Score score = ScoreCalculator.computeScore(board);

        assertThat(score.getPlayer1Score(), equalTo(1));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

    @Test
    public void onePiecePlayer2() {
        Board board = new Board();
        board.setCell(Coordinates.of("d5"), Piece.PLAYER_2);

        Score score = ScoreCalculator.computeScore(board);

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(1));
    }

    @Test
    public void readPieces() throws Exception {
        Board board = BoardReader.read("boardScoreTest1");

        Score score = ScoreCalculator.computeScore(board);

        assertThat(score.getPlayer1Score(), equalTo(1));
        assertThat(score.getPlayer2Score(), equalTo(3));
    }

    @Test
    public void readConsecutiveAndAlternatePieces() throws Exception {
        Board board = BoardReader.read("boardScoreTest2");

        Score score = ScoreCalculator.computeScore(board);

        assertThat(score.getPlayer1Score(), equalTo(8));
        assertThat(score.getPlayer2Score(), equalTo(10));
    }

    @Test
    public void invalidCoordinates() {
        Board board = new Board();

        assertThrows(RuntimeException.class, () -> {
            board.setCell(Coordinates.of("a9"), Piece.PLAYER_2);
        });
    }
}