package it.fmt.games.reversi.exceptions;

public class InvalidPieceSelectedException extends RuntimeException {
    public InvalidPieceSelectedException() {
        super("Invalid piece selected");
    }
}
