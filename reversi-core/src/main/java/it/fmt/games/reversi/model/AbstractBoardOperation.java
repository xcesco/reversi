package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidPieceSelectedException;

import java.util.stream.Stream;

public abstract class AbstractBoardOperation {
    protected final Piece piece;
    protected final Piece enemyPiece;
    protected final Board board;

    public AbstractBoardOperation(Board board, Piece piece) {
        if (piece == Piece.EMPTY) throw (new InvalidPieceSelectedException());
        this.piece = piece;
        this.enemyPiece = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;
        this.board = board;
    }

    protected Stream<Coordinates> findEnemyPiecesAlongDirection(Coordinates coordinates, Direction direction) {
        return Stream.iterate(coordinates.translate(direction),
                coords -> board.isCellContentEqualsTo(coords, this.enemyPiece),
                coords -> coords.translate(direction));
    }

    protected boolean isAnyPieceToInvert(Coordinates initialCoordinates, Direction direction) {
        int piecesToReverte = (int) findEnemyPiecesAlongDirection(initialCoordinates, direction).count();
        return piecesToReverte > 0 &&
                board.isCellContentEqualsTo(initialCoordinates.translate(direction, piecesToReverte + 1), piece);
    }
}
