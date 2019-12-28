package it.fmt.games.reversi.console.drawers;

import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.model.PlayerMove;

import static it.fmt.games.reversi.console.drawers.TextDrawer.*;

public abstract class PlayerMoveDrawer {

    private PlayerMoveDrawer() {
    }

    public static void drawPlayerMove(PlayerMove move) {
        if (move != null) {
            println("  PREVIOUS TURN:");
            println(2,String.format("- %s moved on %s", (move.getPiece() == Piece.PLAYER_1 ? TextDrawer.player1AsString :
                    TextDrawer.player2AsString), move.getMoveCoords()));
        }
    }
}
