package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.PlayerMove;

public abstract class PlayerMoveTextDrawer extends TextDrawer {

    private PlayerMoveTextDrawer() {
    }

    public static void drawPlayerMove(PlayerMove move) {
        if (move != null) {
            println(String.format("  %s moves on %s", move.getPiece(), move.getMoveCoords()));
        }
    }
}
