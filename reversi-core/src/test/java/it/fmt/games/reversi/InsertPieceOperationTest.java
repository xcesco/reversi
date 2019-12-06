package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static it.fmt.games.reversi.BoardReader.readBoards;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class InsertPieceOperationTest {

    @Test
    void execute() throws Exception {
        Board[] snapshots = readBoards("insert_piece01");

        checkMove(snapshots, Piece.PLAYER_1, "c4", 1);
        checkMove(snapshots, Piece.PLAYER_2, "c5", 2);
        checkMove(snapshots, Piece.PLAYER_1, "c6", 3);
    }

    private void checkMove(Board[] snapshots, Piece piece, String coords, int boardIndex) {
        Coordinates move = Coordinates.of(coords);
        EnemyPiecesToCaptureFinder finder = new EnemyPiecesToCaptureFinder(snapshots[0], move, piece);
        List<Coordinates> positionToInvert = finder.find();
        InsertPieceOperation insertPieceOperation = new InsertPieceOperation(snapshots[0], move, piece, positionToInvert);
        Board result = insertPieceOperation.apply();

        assertThat(result, equalTo(snapshots[boardIndex]));
    }
}