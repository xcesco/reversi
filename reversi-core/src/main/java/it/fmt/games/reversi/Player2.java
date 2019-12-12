package it.fmt.games.reversi;

import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.model.Player;

public class Player2 extends Player {
    public Player2() {
        this(null);
    }

    public Player2(DecisionHandler decisionHandler) {
        super(Piece.PLAYER_2, decisionHandler);
    }

}
