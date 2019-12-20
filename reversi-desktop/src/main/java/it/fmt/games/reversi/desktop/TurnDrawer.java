package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.Piece;

import java.awt.*;

import static it.fmt.games.reversi.desktop.GameCanvas.*;
import static it.fmt.games.reversi.desktop.GameCanvas.resize;
import static it.fmt.games.reversi.model.Board.BOARD_SIZE;

public class TurnDrawer implements Drawer {

    @Override
    public void draw (GameSnapshot gameSnapshot, Graphics g) {
        if (gameSnapshot.getActivePiece() == Piece.PLAYER_1) {
            g.setColor(Color.RED);
            g.fillOval(CELL_SIZE * BOARD_SIZE + resize(121), BASE_Y * 2 + resize(20),
                    CELL_SIZE - resize(50),
                    CELL_SIZE - resize(50));
        } else {
            g.setColor(Color.RED);
            g.fillOval(CELL_SIZE * BOARD_SIZE + resize(121), BASE_Y * 4 + resize(20),
                    CELL_SIZE - resize(50),
                    CELL_SIZE - resize(50));
        }
    }

}
