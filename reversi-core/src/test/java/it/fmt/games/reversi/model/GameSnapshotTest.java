package it.fmt.games.reversi.model;

import it.fmt.games.reversi.Player1;
import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static it.fmt.games.reversi.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameSnapshotTest {

    @Test
    public void testGameSnapshotBuilder() {
        assertThrows(InvalidInsertOperationException.class, () -> {
            new GameSnapshotBuilder().setActivePiece(Piece.EMPTY);
        });
    }

    @Test
    public void checkGameSnapshot() {
        int player1Score=20;
        int player2Score=10;
        String availMoveP1="a1";
        String availMoveP2="a2";

        GameSnapshot gameSnapshot = createGameSnapshot(player1Score, player2Score, availMoveP1, availMoveP2);

        Score aspectedScore = new Score(player1Score, player2Score);
        Player aspectedCurrentPlayer = new Player1();
        AvailableMoves aspectedMoves = new AvailableMoves(Arrays.asList(of(availMoveP1)), Arrays.asList(of(availMoveP2)));
        Board aspectedBoard = new Board();
        GameStatus aspectedStatus = GameStatus.DRAW;

        assertThat(gameSnapshot.getAvailableMoves(), equalTo(aspectedMoves));
        assertThat(gameSnapshot.getScore(), equalTo(aspectedScore));
        assertThat(gameSnapshot.getLastMove(), nullValue());
        assertThat(gameSnapshot.getActivePiece(), equalTo(aspectedCurrentPlayer.getPiece()));
        assertThat(gameSnapshot.getBoard(), equalTo(aspectedBoard));
        assertThat(gameSnapshot.getStatus(), equalTo(aspectedStatus));
    }

    private GameSnapshot createGameSnapshot(int player1Score, int player2Score, String availMoveP1, String availMoveP2) {
        Score score = new Score(player1Score, player2Score);
        Player currentPlayer = new Player1();
        AvailableMoves moves = new AvailableMoves(Arrays.asList(of(availMoveP1)), Arrays.asList(of(availMoveP2)));
        Board board = new Board();
        GameStatus status = GameStatus.DRAW;
        return new GameSnapshot(score, null, currentPlayer.getPiece(), moves, board, status);
    }




}