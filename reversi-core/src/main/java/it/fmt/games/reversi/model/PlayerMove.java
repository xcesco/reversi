package it.fmt.games.reversi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;

import java.util.List;

public class PlayerMove {
    private final Piece piece;
    private final Coordinates moveCoords;
    private final List<Coordinates> capturedEnemyPiecesCoords;

    @JsonCreator
    public PlayerMove(@JsonProperty("piece") Piece piece,
                      @JsonProperty("moveCoords") Coordinates moveCoords,
                      @JsonProperty("capturedEnemyPiecesCoords") List<Coordinates> capturedEnemyPiecesCoords) {
        if (piece==null || Piece.EMPTY==piece) throw new InvalidInsertOperationException();
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
