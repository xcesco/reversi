package it.fmt.games.reversi.model;

import it.fmt.games.reversi.DecisionHandler;
import it.fmt.games.reversi.exceptions.HumanPlayerNeedUserInputException;

import java.util.List;

public abstract class Player {
    private final Piece piece;
    private final DecisionHandler decisionHandler;

    public Player(Piece piece, DecisionHandler decisionHandler) {
        this.piece = piece;
        this.decisionHandler = decisionHandler;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isHumanPlayer() {
        return this.decisionHandler == null;
    }

    public Coordinates computeNextMove(List<Coordinates> availableMoves) {
        if (isHumanPlayer()) {
            throw new HumanPlayerNeedUserInputException(getPiece());
        }
        return this.decisionHandler.compute(availableMoves);
    }
}
