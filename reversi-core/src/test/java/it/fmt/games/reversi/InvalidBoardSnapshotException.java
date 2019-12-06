package it.fmt.games.reversi;

public class InvalidBoardSnapshotException extends RuntimeException {
    public InvalidBoardSnapshotException(String msg) {
        super(msg);
    }
}
