package it.fmt.games.reversi.model.operators;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;
import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.model.operators.EnemyPiecesHunter;
import it.fmt.games.reversi.model.operators.InsertMoveOperator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static it.fmt.games.reversi.model.BoardReader.readBoards;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertMoveOperatorTest {

    @Test
    public void execute() throws Exception {
        Board[] snapshots = readBoards("insert_piece01");

        checkMove(snapshots, Piece.PLAYER_1, "c4", 1);
        checkMove(snapshots, Piece.PLAYER_2, "c5", 2);
        checkMove(snapshots, Piece.PLAYER_1, "c6", 3);
    }

    @Test
    public void insertEmpty() {
        Board board = new Board();
        assertThrows(InvalidInsertOperationException.class, () -> {
            InsertMoveOperator.insertMove(board, null, Collections.emptyList());
        });

        assertThrows(InvalidInsertOperationException.class, () -> {
            InsertMoveOperator.insertMove(board, Piece.EMPTY, Collections.emptyList());
        });
    }

    private void checkMove(Board[] snapshots, Piece piece, String coords, int boardIndex) {
        Coordinates move = Coordinates.of(coords);
        List<Coordinates> positionsToInsert = EnemyPiecesHunter.find(snapshots[boardIndex - 1], move, piece);
        positionsToInsert.add(move);

        Board result = InsertMoveOperator.insertMove(snapshots[boardIndex - 1], piece, positionsToInsert);

        assertThat(result, equalTo(snapshots[boardIndex]));
    }
}