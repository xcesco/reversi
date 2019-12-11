package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreCalculatorTest {

    @Test
    void noScore() {
        Board board = new Board();

        ScoreCalculator scoreCalculator = new ScoreCalculator(board);
        Score score = scoreCalculator.execute();

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

    @Test
    void onePiecePlayer1() {
        Board board = new Board();
        board.setCell(Coordinates.of("a1"), Piece.PLAYER_1);

        ScoreCalculator scoreCalculator = new ScoreCalculator(board);
        Score score = scoreCalculator.execute();

        assertThat(score.getPlayer1Score(), equalTo(1));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

    @Test
    void onePiecePlayer2() {
        Board board = new Board();
        board.setCell(Coordinates.of("d5"), Piece.PLAYER_2);

        ScoreCalculator scoreCalculator = new ScoreCalculator(board);
        Score score = scoreCalculator.execute();

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(1));
    }

    @Test
    void readPieces() throws Exception {
        Board board = BoardReader.read("boardScoreTest1");

        ScoreCalculator scoreCalculator = new ScoreCalculator(board);
        Score score = scoreCalculator.execute();

        assertThat(score.getPlayer1Score(), equalTo(1));
        assertThat(score.getPlayer2Score(), equalTo(3));
    }

    @Test
    void readConsecutiveAndAlternatePieces() throws Exception {
        Board board = BoardReader.read("boardScoreTest2");

        ScoreCalculator scoreCalculator = new ScoreCalculator(board);
        Score score = scoreCalculator.execute();

        assertThat(score.getPlayer1Score(), equalTo(8));
        assertThat(score.getPlayer2Score(), equalTo(10));
    }

    @Test
    void invalidCoordinates() {
        Board board = new Board();

        assertThrows(RuntimeException.class, () -> {
            board.setCell(Coordinates.of("a9"), Piece.PLAYER_2);
        });
    }
}