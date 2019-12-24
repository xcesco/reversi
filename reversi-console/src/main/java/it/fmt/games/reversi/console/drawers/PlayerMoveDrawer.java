package it.fmt.games.reversi.console.drawers;

import it.fmt.games.reversi.model.PlayerMove;

public abstract class PlayerMoveDrawer extends TextDrawer {

    private PlayerMoveDrawer() {
    }

    public static void drawPlayerMove(PlayerMove move) {
        if (move != null) {
            println(String.format("  %s moved on %s", move.getPiece(), move.getMoveCoords()));
        }
    }
}
