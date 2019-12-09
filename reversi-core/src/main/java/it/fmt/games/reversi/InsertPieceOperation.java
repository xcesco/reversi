package it.fmt.games.reversi;

import java.util.List;

public class InsertPieceOperation {
    private final Board board;
    private final Piece pieceToInsert;

    public InsertPieceOperation(Board board, Piece pieceToInsert) {
        if (pieceToInsert==null) throw new InvalidInsertOperationException();
        this.board = board.copy();
        this.pieceToInsert=pieceToInsert;
    }

    public InsertPieceOperation insert(Coordinates position) {
        board.setCell(position, pieceToInsert);
        return this;
    }

    public InsertPieceOperation insert(List<Coordinates> positions) {
        positions.forEach(position -> board.setCell(position, pieceToInsert));
        return this;
    }

    public Board getBoard() {
        return board;
    }
}
