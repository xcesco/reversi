package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidPieceSelectedException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AvailableMovesFinder {
    protected final Piece currentPlayer;
    protected final Piece otherPlayer;
    protected final Board board;


    public AvailableMovesFinder(Board board, Piece piece) {
        this.currentPlayer = piece;
        this.otherPlayer = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;
        this.board = board;
        if (piece == Piece.EMPTY) throw (new InvalidPieceSelectedException());
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

    private boolean hasPiecesToInvertAlongDirection(Coordinates initialCoordinates, Direction direction) {
        int piecesToReverte = (int) findEnemyPiecesAlongDirection(initialCoordinates, direction).count();
        return piecesToReverte > 0 &&
                board.isCellContentEqualsTo(initialCoordinates.translate(direction, piecesToReverte + 1), this.currentPlayer);
    }

    private Stream<Coordinates> findEnemyPiecesAlongDirection(Coordinates coordinates, Direction direction) {

        return Stream.iterate(coordinates.translate(direction),
                coords -> board.isCellContentEqualsTo(coords, this.otherPlayer),
                coords -> coords.translate(direction));
    }

}
