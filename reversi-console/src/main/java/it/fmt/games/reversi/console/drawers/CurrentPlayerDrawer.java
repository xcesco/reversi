package it.fmt.games.reversi.console.drawers;

import it.fmt.games.reversi.model.Piece;

public class CurrentPlayerDrawer extends TextDrawer {
    private CurrentPlayerDrawer() {

    }

    public static void drawCurrentPlayer(Piece activePiece) {
        println("  "+activePiece+" "+(activePiece == Piece.PLAYER_1 ? "(O)" : "(X)")+" TURN:");
    }
}
