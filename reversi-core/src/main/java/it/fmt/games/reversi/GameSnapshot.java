package it.fmt.games.reversi;

public class GameSnapshot {
    private final Score score;
    private final Piece activePiece;
    private final AvailableMoves availableMoves;
    private final Board board;
    private final GameStatus status;

    public GameSnapshot(Score score, Piece activePiece, AvailableMoves availableMoves, Board board, GameStatus status) {
        this.score = score;
        this.activePiece = activePiece;
        this.availableMoves = availableMoves;
        this.board = board;
        this.status = status;
    }

    public Score getScore() {
        return score;
    }

    public Piece getActivePiece() {
        return activePiece;
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
