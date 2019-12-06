package it.fmt.games.reversi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnemyPiecesToInvertFinder {
    private final Coordinates searchOrigin;
    protected final Piece piece;
    protected final Board board;

    public EnemyPiecesToInvertFinder(Board board, Coordinates coords, Piece piece) {
        this.piece = piece;
        this.board = board;
        this.searchOrigin = coords;
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
        return findEnemyPiecesAlongDirection(searchOrigin, direction).peek(item -> board.setCell(item, piece));
    }

    private boolean isAnyPieceToInvert(Coordinates initialCoordinates, Direction direction) {
        int piecesToReverte = (int) findEnemyPiecesAlongDirection(initialCoordinates, direction).count();
        return piecesToReverte > 0 &&
                board.isCellContentEqualsTo(initialCoordinates.translate(direction, piecesToReverte + 1), piece);
    }

    private Stream<Coordinates> findEnemyPiecesAlongDirection(Coordinates coordinates, Direction direction) {
        Piece enemyPlayer = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;

        return Stream.iterate(coordinates.translate(direction),
                coords -> board.isCellContentEqualsTo(coords, enemyPlayer),
                coords -> coords.translate(direction));
    }
}