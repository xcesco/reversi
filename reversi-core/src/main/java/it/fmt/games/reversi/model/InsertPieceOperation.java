package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class InsertPieceOperation {
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

    public static Board insertMove(Board board, Piece piece, Coordinates... coordinates) {
        return insertMove(board, piece, Arrays.asList(coordinates));
    }

}
