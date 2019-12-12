package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidPieceSelectedException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnemyPiecesHunter {
    private final Coordinates searchOrigin;
    private final Piece piece;
    private final Board board;
    private final Piece enemyPiece;

    public EnemyPiecesHunter(Board board, Coordinates coords, Piece piece) {
        if (piece == Piece.EMPTY || piece == null) throw (new InvalidPieceSelectedException());
        this.piece = piece;
        this.board = board;
        this.searchOrigin = coords;
        this.enemyPiece = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;
    }

    public static List<Coordinates> find(Board board, Coordinates newMoveCoords, Piece piece) {
        EnemyPiecesHunter hunter = new EnemyPiecesHunter(board, newMoveCoords, piece);
        return hunter.find();
    }

    public List<Coordinates> find() {
        return Stream.of(Direction.values()).filter(this::isAnyPieceToInvertAlongDirection)
                .map(this::rotateEnemyPieces).flatMap(x -> x).sorted()
                .collect(Collectors.toList());
    }

    private boolean isAnyPieceToInvertAlongDirection(Direction direction) {
        return isAnyPieceToInvert(searchOrigin, direction);
    }

    private Stream<Coordinates> rotateEnemyPieces(Direction direction) {
        return findEnemyPiecesAlongDirection(searchOrigin, direction);
    }

    private boolean isAnyPieceToInvert(Coordinates initialCoordinates, Direction direction) {
        int piecesToReverte = (int) findEnemyPiecesAlongDirection(initialCoordinates, direction).count();
        return piecesToReverte > 0 &&
                board.isCellContentEqualsTo(initialCoordinates.translate(direction, piecesToReverte + 1), piece);
    }

    private Stream<Coordinates> findEnemyPiecesAlongDirection(Coordinates coordinates, Direction direction) {
        return Stream.iterate(coordinates.translate(direction),
                coords -> board.isCellContentEqualsTo(coords, enemyPiece),
                coords -> coords.translate(direction));
    }
}