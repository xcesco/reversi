package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;

public class GameSnapshotBuilder {

    public GameSnapshotBuilder setActivePiece(Piece activePiece) {
        if (activePiece == Piece.EMPTY) throw new InvalidInsertOperationException();
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

    public GameSnapshotBuilder setLastMove(PlayerMove move) {
        this.lastMove = move;
        return this;
    }

    private Piece activePiece;
    private AvailableMoves availableMoves;
    private Score score;
    private Board board;
    private PlayerMove lastMove;

    public GameSnapshot build() {
        return new GameSnapshot(score, lastMove, activePiece, availableMoves, board, GameStatusFactory.create(availableMoves, score));
    }

}