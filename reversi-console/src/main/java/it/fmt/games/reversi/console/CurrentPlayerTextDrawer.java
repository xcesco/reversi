package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.Piece;

public class CurrentPlayerTextDrawer extends TextDrawer {
    private CurrentPlayerTextDrawer() {

    }

    public static void drawCurrentPlayer(Piece activePiece) {
        print(activePiece+" "+(activePiece == Piece.PLAYER_1 ? "(O)" : "(X)")+" TURN:"+NEW_LINE);
    }
}
