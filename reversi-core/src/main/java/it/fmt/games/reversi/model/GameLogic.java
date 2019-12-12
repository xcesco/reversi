package it.fmt.games.reversi.model;

import it.fmt.games.reversi.UserInputReader;

import java.util.Arrays;
import java.util.List;

import static it.fmt.games.reversi.model.AvailableMovesFinder.findMoves;
import static it.fmt.games.reversi.model.Coordinates.of;
import static it.fmt.games.reversi.model.InsertPieceOperation.insertMove;
import static it.fmt.games.reversi.model.ScoreCalculator.computeScore;

public class GameLogic {
    private final GameSnapshotBuilder gameSnapshotBuilder;
    private final UserInputReader userInputReader;
    private Board board;
    private Player currentPlayer;
    private Player otherPlayer;

    public GameLogic(Player player1, Player player2, UserInputReader userInputReader) {
        this.board = new Board();
        this.gameSnapshotBuilder = new GameSnapshotBuilder();
        this.currentPlayer = player1;
        this.otherPlayer = player2;
        this.userInputReader = userInputReader;
    }

    private void insertNewMoveAndCapturedPieces(Piece piece, List<Coordinates> insertedPieceCoords) {
        board = insertMove(board, piece, insertedPieceCoords);
    }

    private void insertNewMoveAndCapturedPieces(PlayerMove playerMove) {
        board = insertMove(board, playerMove);
    }

    public AvailableMoves initialize() {
        insertNewMoveAndCapturedPieces(Piece.PLAYER_1, Arrays.asList(of("e4"), of("d5")));
        insertNewMoveAndCapturedPieces(Piece.PLAYER_2, Arrays.asList(of("d4"), of("e5")));

        gameSnapshotBuilder.setActivePiece(currentPlayer.getPiece()).setScore(computeScore(board)).setBoard(board.copy());
        return findMovesForPlayers();
    }

    public AvailableMoves findMovesForPlayers() {
        AvailableMoves availableMoves = new AvailableMoves(findMoves(board, currentPlayer.getPiece()), findMoves(board, otherPlayer.getPiece()));

        gameSnapshotBuilder.setAvailableMoves(availableMoves);
        return availableMoves;
    }

    public void insertSelectedMove(Coordinates moveCoords) {
        Piece currentPiece = currentPlayer.getPiece();
        PlayerMove playerMove = new PlayerMove(currentPiece, moveCoords, EnemyPiecesHunter.find(board, moveCoords, currentPiece));
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

    public Coordinates readActivePlayeMove(List<Coordinates> availableMoves) {
        if (currentPlayer.isHumanPlayer()) {
            return userInputReader.readInputFor(currentPlayer, availableMoves);
        } else {
            return currentPlayer.computeNextMove(availableMoves);
        }
    }
}
