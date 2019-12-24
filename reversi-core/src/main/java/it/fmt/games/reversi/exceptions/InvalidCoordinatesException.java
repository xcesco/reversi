package it.fmt.games.reversi.exceptions;

public class InvalidCoordinatesException extends RuntimeException {

    public InvalidCoordinatesException() {
        super("Invalid coordinates");
    }

}
