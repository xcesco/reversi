package it.fmt.games.reversi;

import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.model.Player;

public class Player1 extends Player {
    public Player1() {
        this(null);
    }

    public Player1(DecisionHandler decisionHandler) {
        super(Piece.PLAYER_1, decisionHandler);
    }
}
