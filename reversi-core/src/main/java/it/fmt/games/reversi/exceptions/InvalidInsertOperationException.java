package it.fmt.games.reversi.exceptions;

public class InvalidInsertOperationException extends RuntimeException {
    public InvalidInsertOperationException() {
        super("Can not insert null piece");
    }
}
