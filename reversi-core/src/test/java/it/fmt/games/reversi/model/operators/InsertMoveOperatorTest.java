package it.fmt.games.reversi.model.operators;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;
import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.support.BoardReader;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static it.fmt.games.reversi.model.Coordinates.of;
import static it.fmt.games.reversi.model.operators.InsertMoveOperator.*;
import static it.fmt.games.reversi.support.BoardReader.readBoards;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertMoveOperatorTest {

    @Test
    public void insert() throws Exception {
        Board[] snapshots = readBoards("insert_piece01");

        checkMove(snapshots, Piece.PLAYER_1, "c4", 1);
        checkMove(snapshots, Piece.PLAYER_2, "c5", 2);
        checkMove(snapshots, Piece.PLAYER_1, "c6", 3);
    }

    @Test
    public void insertEmpty() {
        Board board = new Board();
        assertThrows(InvalidInsertOperationException.class, () -> insertMove(board, null, Collections.emptyList()));
        assertThrows(InvalidInsertOperationException.class, () -> insertMove(board, Piece.EMPTY, Collections.emptyList()));
    }

    @Test
    public void noAvailableMoves() throws Exception {
        Board[] boards = BoardReader.readBoards("insert_piece02");

        List<Coordinates> availableMoves = AvailableMovesFinder.findMoves(boards[0], Piece.PLAYER_1);
        assertThat(availableMoves.size(), equalTo(0));
    }

    @Test
    public void insertOnlyInOneDirection() throws Exception {
        Board[] boards = BoardReader.readBoards("insert_piece02");

        checkRightInsertion(boards, 1);
        checkRightInsertion(boards, 3);
    }

    private void checkRightInsertion(Board[] boards, int startIndex) {
        int finalIndex=startIndex+1;
        List<Coordinates> availableMoves = AvailableMovesFinder.findMoves(boards[startIndex], Piece.PLAYER_1);
        assertThat(availableMoves.size(), equalTo(1));
        assertEquals(availableMoves, Collections.singletonList(of("e4")));

        List<Coordinates> coords = EnemyPiecesHunter.find(boards[startIndex], of("e4"), Piece.PLAYER_1);
        coords.add(of("e4"));
        Board result = insertMove(boards[startIndex], Piece.PLAYER_1, coords);
        assertEquals(result, boards[finalIndex]);
    }

    private void checkMove(Board[] snapshots, Piece piece, String coords, int boardIndex) {
        Coordinates move = Coordinates.of(coords);
        List<Coordinates> positionsToInsert = EnemyPiecesHunter.find(snapshots[boardIndex - 1], move, piece);
        positionsToInsert.add(move);

        Board result = insertMove(snapshots[boardIndex - 1], piece, positionsToInsert);
        assertThat(result, equalTo(snapshots[boardIndex]));
    }
}