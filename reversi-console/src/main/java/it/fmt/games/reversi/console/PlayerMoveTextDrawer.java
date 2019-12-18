package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.PlayerMove;

import java.util.stream.Collectors;

public abstract class PlayerMoveTextDrawer extends TextDrawer {

    private PlayerMoveTextDrawer() {

    }

    public static void drawPlayerMove(PlayerMove move) {
        if (move != null) {
            print(String.format("%s moves on %s"+NEW_LINE, move.getPiece(), move.getMoveCoords()));
//            String result = move.getCapturedEnemyPiecesCoords().stream()
//                    .map(String::valueOf)
//                    .collect(Collectors.joining(", ", "{", "}"));
//            print(String.format("inverted pieces on (#%s): %s", move.getCapturedEnemyPiecesCoords().size(), result));
        }
    }
}
