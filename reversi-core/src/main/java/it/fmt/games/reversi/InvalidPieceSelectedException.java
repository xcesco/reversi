package it.fmt.games.reversi;

public class InvalidPieceSelectedException extends RuntimeException {
    public InvalidPieceSelectedException() {
        super("Invalid piece selected");
    }
}
