package it.fmt.games.reversi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.fmt.games.reversi.Coordinates.of;

public class GameLogic {
    private Board board;
    private Player currentPlayer;
    private Player otherPlayer;
    private final GameSnapshotBuilder gameSnapshotBuilder;

    public GameLogic() {
        this.board = new Board();
        this.gameSnapshotBuilder = new GameSnapshotBuilder();
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    private void insertNewMoveAndCapturedPieces(Piece piece, List<Coordinates> insertedPieceCoords) {
        InsertPieceOperation insertPieceOperation = new InsertPieceOperation(board, piece);
        board = insertPieceOperation.insert(insertedPieceCoords);
    }

    public void initialize() {
        insertNewMoveAndCapturedPieces(Piece.PLAYER_1, Arrays.asList(of("e4"), of("d5")));
        insertNewMoveAndCapturedPieces(Piece.PLAYER_2, Arrays.asList(of("d4"), of("e5")));

        currentPlayer = new Player1();
        otherPlayer = new Player2();

        gameSnapshotBuilder
                .clearLastMoveAndCapturedPieces()
                .setActivePiece(currentPlayer.getPiece())
                .setScore(new ScoreCalculator(board).execute())
                .setBoard(board.copy());
    }

    public AvailableMoves findMovesForPlayers() {
        AvailableMovesFinder finderCurrentPlayer = new AvailableMovesFinder(board, currentPlayer.getPiece());
        AvailableMovesFinder finderOtherPlayer = new AvailableMovesFinder(board, otherPlayer.getPiece());
        List<Coordinates> list1 = finderCurrentPlayer.findMoves();
        AvailableMoves availableMoves = new AvailableMoves(list1, finderOtherPlayer.findMoves());

        gameSnapshotBuilder
                .setAvailableMoves(availableMoves);

        return availableMoves;
    }

    public void insertSelectedMove(Coordinates newMoveCoords) {
        Piece piece = currentPlayer.getPiece();
        EnemyPiecesToCaptureFinder enemyPiecesFinder = new EnemyPiecesToCaptureFinder(board, newMoveCoords, piece);
        List<Coordinates> capturedEnemyPiecesCoords = enemyPiecesFinder.find();
        List<Coordinates> piecesToInsertCoords = new ArrayList<>(capturedEnemyPiecesCoords);
        piecesToInsertCoords.add(newMoveCoords);
        insertNewMoveAndCapturedPieces(piece, piecesToInsertCoords);

        ScoreCalculator scoreCalculator = new ScoreCalculator(board);
        Score score = scoreCalculator.execute();

        gameSnapshotBuilder.setLastMoveAndCapturedPieces(newMoveCoords, capturedEnemyPiecesCoords)
                .setBoard(board.copy())
                .setScore(score);
    }

    public void switchPlayer() {
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;

        gameSnapshotBuilder.clearLastMoveAndCapturedPieces().setActivePiece(currentPlayer.getPiece());
    }

    public GameSnapshot getGameSnapshot() {
        return gameSnapshotBuilder.build();
    }
}
