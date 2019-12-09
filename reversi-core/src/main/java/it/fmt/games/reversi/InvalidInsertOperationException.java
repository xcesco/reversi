package it.fmt.games.reversi;

public class InvalidInsertOperationException extends RuntimeException {
    public InvalidInsertOperationException() {
        super("Can not insert null piece");
    }
}
