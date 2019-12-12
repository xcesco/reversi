package it.fmt.games.reversi.exceptions;

import it.fmt.games.reversi.model.Piece;

public class InvalidPlayerDefinitionException extends RuntimeException {
    public InvalidPlayerDefinitionException(Piece player) {
        super(String.format("Player %s is invalid", player));
    }
}
