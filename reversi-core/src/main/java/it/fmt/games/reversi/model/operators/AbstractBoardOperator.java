package it.fmt.games.reversi.model.operators;

import it.fmt.games.reversi.exceptions.InvalidPieceSelectedException;
import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Direction;
import it.fmt.games.reversi.model.Piece;

import java.util.stream.Stream;

public abstract class AbstractBoardOperator {
    protected final Piece piece;
    protected final Piece enemyPiece;
    protected final Board board;

    public AbstractBoardOperator(Board board, Piece piece) {
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
        int piecesToInvert = (int) findEnemyPiecesAlongDirection(initialCoordinates, direction).count();
        return piecesToInvert > 0 &&
                board.isCellContentEqualsTo(initialCoordinates.translate(direction, piecesToInvert + 1), piece);
    }
}
