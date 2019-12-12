package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidPieceSelectedException;

import java.util.stream.Stream;

public abstract class AbstractPieceOperation {
    protected final Piece piece;
    protected final Piece enemyPiece;
    protected final Board board;

    public AbstractPieceOperation(Board board, Piece piece) {
        if (piece == null || piece == Piece.EMPTY) throw (new InvalidPieceSelectedException());
        this.piece = piece;
        this.enemyPiece = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;
        this.board = board;
    }

    protected Stream<Coordinates> findEnemyPiecesAlongDirection(Coordinates coordinates, Direction direction) {
        // done in this way for JDK1.8 compatibility
        Stream.Builder<Coordinates> builder = Stream.builder();
        Coordinates current = coordinates.translate(direction);

        while (board.isCellContentEqualsTo(current, this.enemyPiece)) {
            builder.add(current);
            current = current.translate(direction);
        }
        return builder.build();
    }

    protected boolean isAnyPieceToInvert(Coordinates initialCoordinates, Direction direction) {
        int piecesToReverte = (int) findEnemyPiecesAlongDirection(initialCoordinates, direction).count();
        return piecesToReverte > 0 &&
                board.isCellContentEqualsTo(initialCoordinates.translate(direction, piecesToReverte + 1), piece);
    }
}
