package it.fmt.games.reversi.model;

import java.util.Arrays;
import java.util.List;

import static it.fmt.games.reversi.model.AvailableMovesFinder.findMoves;
import static it.fmt.games.reversi.model.Coordinates.of;
import static it.fmt.games.reversi.model.InsertPieceOperation.insertMove;
import static it.fmt.games.reversi.model.ScoreCalculator.computeScore;

public class GameLogic {
    private Board board;
    private Player currentPlayer;
    private Player otherPlayer;
    private final GameSnapshotBuilder gameSnapshotBuilder;

    public GameLogic() {
        this.board = new Board();
        this.gameSnapshotBuilder = new GameSnapshotBuilder();
    }

    private void insertNewMoveAndCapturedPieces(Piece piece, List<Coordinates> insertedPieceCoords) {
        board = insertMove(board, piece, insertedPieceCoords);
    }

    private void insertNewMoveAndCapturedPieces(PlayerMove playerMove) {
        board = insertMove(board, playerMove);
    }

    public AvailableMoves initialize() {
        currentPlayer = new Player1();
        otherPlayer = new Player2();
        insertNewMoveAndCapturedPieces(Piece.PLAYER_1, Arrays.asList(of("e4"), of("d5")));
        insertNewMoveAndCapturedPieces(Piece.PLAYER_2, Arrays.asList(of("d4"), of("e5")));

        gameSnapshotBuilder.clearLastMove().setActivePiece(currentPlayer.getPiece()).setScore(computeScore(board)).setBoard(board.copy());
        return findMovesForPlayers();
    }

    public AvailableMoves findMovesForPlayers() {
        AvailableMoves availableMoves = new AvailableMoves(findMoves(board, currentPlayer.getPiece()), findMoves(board, otherPlayer.getPiece()));

        gameSnapshotBuilder.setAvailableMoves(availableMoves);
        return availableMoves;
    }

    public void insertSelectedMove(Coordinates newMoveCoords) {
        Piece piece = currentPlayer.getPiece();
        PlayerMove playerMove = new PlayerMove(piece, newMoveCoords, EnemyPiecesHunter.find(board, newMoveCoords, piece));
        insertNewMoveAndCapturedPieces(playerMove);

        gameSnapshotBuilder.setLastMove(playerMove).setBoard(board.copy()).setScore(computeScore(board));
    }

    public void switchPlayer() {
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;

        gameSnapshotBuilder.setActivePiece(currentPlayer.getPiece());
    }

    public GameSnapshot getGameSnapshot() {
        return gameSnapshotBuilder.build();
    }
}
