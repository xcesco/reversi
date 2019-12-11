package it.fmt.games.reversi;

public class GameSnapshot {
    private final Score score;
    private final Player currentPlayer;
    private final AvailableMoves availableMoves;
    private final Board board;
    private final GameStatus status;

    public GameSnapshot(Score score, Player currentPlayer, AvailableMoves availableMoves, Board board, GameStatus status) {
        this.score = score;
        this.currentPlayer = currentPlayer;
        this.availableMoves = availableMoves;
        this.board = board;
        this.status = status;
    }

    public Score getScore() {
        return score;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public AvailableMoves getAvailableMoves() {
        return availableMoves;
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }
}
