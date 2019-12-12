package it.fmt.games.reversi.exceptions;

import it.fmt.games.reversi.model.Piece;

public class HumanPlayerNeedUserInputException extends RuntimeException {
    public HumanPlayerNeedUserInputException(Piece player) {
        super(String.format("Player %s need user input to decide next move", player));
    }
}
