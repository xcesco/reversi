package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.model.GameSnapshot;

import java.awt.*;

import static it.fmt.games.reversi.desktop.App.*;

public class DrawAvailableMoves extends DrawObject {
    private GameSnapshot gameSnapshot;
    private Graphics g;

    public DrawAvailableMoves(GameSnapshot gameSnapshot, Graphics g) {
        this.gameSnapshot = gameSnapshot;
        this.g = g;
    }

    @Override
    public void DraW(){
        gameSnapshot.getAvailableMoves().getMovesActivePlayer().forEach(item -> {
            g.setColor(Color.gray);
            g.fillOval(BASE_X + item.getRow() * CELL_SIZE + resize(30),
                    BASE_Y + item.getColumn() * CELL_SIZE + resize(30),
                    CELL_SIZE - resize(60),
                    CELL_SIZE - resize(60));
        });

    }
}


