package it.fmt.games.reversi.model;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AvailableMovesFinder extends AbstractPieceOperation {

    public AvailableMovesFinder(Board board, Piece piece) {
        super(board, piece);
    }

    public static List<Coordinates> findMoves(Board board, Piece piece) {
        AvailableMovesFinder finder = new AvailableMovesFinder(board, piece);
        return finder.findMoves();
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
                Stream.of(Direction.values()).anyMatch(direction -> isAnyPieceToInvert(cell.getCoordinates(), direction));
    }


}
