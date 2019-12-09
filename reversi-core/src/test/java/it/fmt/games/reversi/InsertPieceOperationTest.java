package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static it.fmt.games.reversi.BoardReader.readBoards;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertPieceOperationTest {

    @Test
    void execute() throws Exception {
        Board[] snapshots = readBoards("insert_piece01");

        checkMove(snapshots, Piece.PLAYER_1, "c4", 1);
        checkMove(snapshots, Piece.PLAYER_2, "c5", 2);
        checkMove(snapshots, Piece.PLAYER_1, "c6", 3);
    }

    @Test
    void insertEmpty() throws Exception {
        Board board = new Board();
        assertThrows(InvalidInsertOperationException.class, () -> {
            InsertPieceOperation insertPieceOperation = new InsertPieceOperation(board, null);
            insertPieceOperation.insert(Coordinates.of("c4"));
        });

    }

    private void checkMove(Board[] snapshots, Piece piece, String coords, int boardIndex) {
        Coordinates move = Coordinates.of(coords);
        EnemyPiecesToCaptureFinder finder = new EnemyPiecesToCaptureFinder(snapshots[boardIndex - 1], move, piece);
        List<Coordinates> positionsToInvert = finder.find();

        InsertPieceOperation insertPieceOperation = new InsertPieceOperation(snapshots[boardIndex - 1], piece);
        Board result = insertPieceOperation
                .insert(move)
                .insert(positionsToInvert)
                .getBoard();

        assertThat(result, equalTo(snapshots[boardIndex]));
    }
}