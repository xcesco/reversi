package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;

import java.util.List;

public class GameSnapshotBuilder {

    public GameSnapshotBuilder setActivePiece(Piece activePiece) {
        if (activePiece==Piece.EMPTY) throw new InvalidInsertOperationException();
        this.activePiece = activePiece;
        return this;
    }

    public GameSnapshotBuilder setAvailableMoves(AvailableMoves availableMoves) {
        this.availableMoves = availableMoves;
        return this;
    }

    public GameSnapshotBuilder setScore(Score score) {
        this.score = score;
        return this;
    }

    public GameSnapshotBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }

    public GameSnapshotBuilder setLastMoveAndCapturedPieces(Coordinates insertedCoord, List<Coordinates> lastCapturedCoords) {
        this.lastMove=insertedCoord;
        this.lastCapturedCoords=lastCapturedCoords;
        return this;
    }

    public GameSnapshotBuilder clearLastMoveAndCapturedPieces() {
        this.lastMove=null;
        this.lastCapturedCoords=null;
        return this;
    }

    private Piece activePiece;
    private AvailableMoves availableMoves;
    private Score score;
    private Board board;
    private Coordinates lastMove;
    private List<Coordinates> lastCapturedCoords;

    public GameSnapshot build() {
        return new GameSnapshot(score, activePiece, availableMoves, board, GameStatusFactory.create(availableMoves, score));
    }

}