package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;

import java.util.List;

public class InsertPieceOperation {
    private final Board board;
    private final Piece pieceToInsert;

    public InsertPieceOperation(Board board, Piece pieceToInsert) {
        if (pieceToInsert==null) throw new InvalidInsertOperationException();
        this.board = board.copy();
        this.pieceToInsert=pieceToInsert;
    }

    public Board insert(List<Coordinates> positions) {
        positions.forEach(position -> board.setCell(position, pieceToInsert));
        return board;
    }

}
