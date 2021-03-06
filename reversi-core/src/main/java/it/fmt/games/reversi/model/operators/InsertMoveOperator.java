package it.fmt.games.reversi.model.operators;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;
import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.model.PlayerMove;

import java.util.ArrayList;
import java.util.List;

public abstract class InsertMoveOperator {

    private InsertMoveOperator() {
    }

    public static Board insertMove(Board board, PlayerMove move) {
        List<Coordinates> coordinateToInsert = new ArrayList<>(move.getCapturedEnemyPiecesCoords());
        coordinateToInsert.add(move.getMoveCoords());
        return insertMove(board, move.getPiece(), coordinateToInsert);
    }

    public static Board insertMove(Board board, Piece piece, List<Coordinates> coordinates) {
        if (piece == null || piece == Piece.EMPTY) throw new InvalidInsertOperationException();
        Board boardResult = board.copy();
        coordinates.forEach(position -> boardResult.setCell(position, piece));
        return boardResult;
    }
}
