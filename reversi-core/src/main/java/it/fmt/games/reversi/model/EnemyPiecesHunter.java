package it.fmt.games.reversi.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnemyPiecesHunter extends AbstractPieceOperation {
    private final Coordinates searchOrigin;

    public EnemyPiecesHunter(Board board, Coordinates coords, Piece piece) {
        super(board, piece);
        this.searchOrigin = coords;
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
}