package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.Piece;

import java.awt.*;

import static it.fmt.games.reversi.desktop.App.*;
import static it.fmt.games.reversi.desktop.App.resize;

public class DrawTurn extends DrawObject {
    private GameSnapshot gameSnapshot;
    private Graphics g;

    public DrawTurn(GameSnapshot gameSnapshot, Graphics g) {
        this.gameSnapshot = gameSnapshot;
        this.g = g;
    }

    @Override
    public void DraW() {
        if (gameSnapshot.getActivePiece() == Piece.PLAYER_1) {
            g.setColor(Color.RED);
            g.fillOval(CELL_SIZE * 8 + resize(121), BASE_Y * 2 + resize(20),
                    CELL_SIZE - resize(50),
                    CELL_SIZE - resize(50));
        } else {
            g.setColor(Color.RED);
            g.fillOval(CELL_SIZE * 8 + resize(121), BASE_Y * 4 + resize(20),
                    CELL_SIZE - resize(50),
                    CELL_SIZE - resize(50));
        }
    }

}
