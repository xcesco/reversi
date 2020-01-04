package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;

import java.util.List;

public class PlayerMove {
    private final Piece piece;
    private final Coordinates moveCoords;
    private final List<Coordinates> capturedEnemyPiecesCoords;

    public PlayerMove(Piece piece, Coordinates moveCoords, List<Coordinates> capturedEnemyPiecesCoords) {
        if (piece == null) throw new InvalidInsertOperationException();
        this.piece = piece;
        this.moveCoords = moveCoords;
        this.capturedEnemyPiecesCoords = capturedEnemyPiecesCoords;
    }

    public Piece getPiece() {
        return piece;
    }

    public Coordinates getMoveCoords() {
        return moveCoords;
    }

    public List<Coordinates> getCapturedEnemyPiecesCoords() {
        return capturedEnemyPiecesCoords;
    }
}
