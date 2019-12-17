package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.PlayerMove;

import java.util.stream.Collectors;

public class PlayerMoveDrawer extends Drawer {

    private final PlayerMove move;

    public PlayerMoveDrawer(PlayerMove move) {
        this.move = move;
    }

    public void drawPlayerMove() {
        if (move != null) {
            draw(String.format("%s moves on %s", move.getPiece(), move.getMoveCoords()));
//            System.out.println(String.format(PREFIX + "%s moves on %s", move.getPiece(), move.getMoveCoords()));
            String result = move.getCapturedEnemyPiecesCoords().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "{", "}"));
            draw(String.format("inverted pieces on (#%s): %s", move.getCapturedEnemyPiecesCoords().size(), result));
//            System.out.println(String.format(PREFIX + "inverted pieces on (#%s): %s", move.getCapturedEnemyPiecesCoords().size(), result));
        }
    }
}
