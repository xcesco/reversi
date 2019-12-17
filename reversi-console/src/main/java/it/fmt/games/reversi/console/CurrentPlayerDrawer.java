package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.Piece;

public class CurrentPlayerDrawer extends Drawer {
    private Piece activePiece;

    public CurrentPlayerDrawer(Piece activePlayer) {
        this.activePiece = activePlayer;
    }

    public void drawCurrentPlayer() {
        draw("Player: " + activePiece);
    }
}
