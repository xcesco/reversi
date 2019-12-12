package it.fmt.games.reversi.exceptions;

public class InvalidDecisionHandlerException extends RuntimeException {
    public InvalidDecisionHandlerException(String message) {
        super(String.format("Invalid decision handler: %s", message));
    }
}
