package it.fmt.games.reversi;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AvailableMovesFinder {
    protected final Piece piece;
    protected final Board board;

    public AvailableMovesFinder(Piece piece, Board board) {
        this.piece = piece;
        this.board = board;
    }

    public List<Coordinates> findMoves() {
        return getAvailableCoordinates(this::onlyValidCell);
    }

    private List<Coordinates> getAvailableCoordinates(Predicate<Cell> isCellValid) {
        return board.getCellStream()
                .parallel()
                .filter(isCellValid)
                .map(Cell::getCoordinates)
                .collect(Collectors.toList());
    }

    private boolean onlyValidCell(Cell cell) {
        return board.isCellContentEqualsTo(cell.getCoordinates(), Piece.EMPTY) &&
                Stream.of(Direction.values()).anyMatch(direction -> hasPiecesToInvertAlongDirection(cell.getCoordinates(), direction));
    }

    protected boolean hasPiecesToInvertAlongDirection(Coordinates initialCoordinates, Direction direction) {
        int piecesToReverte = (int) findEnemyPiecesAlongDirection(initialCoordinates, direction).count();
        return piecesToReverte > 0 &&
                board.isCellContentEqualsTo(initialCoordinates.translate(direction, piecesToReverte + 1), piece);
    }

    protected Stream<Coordinates> findEnemyPiecesAlongDirection(Coordinates coordinates, Direction direction) {
        Piece enemyPlayer = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;

        return Stream.iterate(coordinates.translate(direction),
                coords -> board.isCellContentEqualsTo(coords, enemyPlayer),
                coords -> coords.translate(direction));
    }

}
