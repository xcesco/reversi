package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static it.fmt.games.reversi.model.BoardReader.readBoards;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertPieceOperationTest {

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
            InsertPieceOperation.insertMove(board, null, Coordinates.of("c4"));
        });
    }

    private void checkMove(Board[] snapshots, Piece piece, String coords, int boardIndex) {
        Coordinates move = Coordinates.of(coords);
        EnemyPiecesHunter finder = new EnemyPiecesHunter(snapshots[boardIndex - 1], move, piece);
        List<Coordinates> positionsToInsert = finder.find();
        positionsToInsert.add(move);

        Board result = InsertPieceOperation.insertMove(snapshots[boardIndex - 1], piece, positionsToInsert);

        assertThat(result, equalTo(snapshots[boardIndex]));
    }
}