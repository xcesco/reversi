package it.fmt.games.reversi.console.drawers;

import it.fmt.games.reversi.model.Piece;

import static it.fmt.games.reversi.console.drawers.TextDrawer.println;

public class CurrentPlayerDrawer {
    private CurrentPlayerDrawer() {
    }

    public static void drawCurrentPlayer(Piece activePiece) {
        println("  "+(activePiece == Piece.PLAYER_1 ? TextDrawer.player1AsString+" (O)"
                : TextDrawer.player2AsString+" (X)")+" TURN:");
    }
}
