package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.Piece;

import java.awt.*;

import static it.fmt.games.reversi.desktop.App.*;

public class DrawScore {
    public  DrawScore(GameSnapshot gameSnapshot, Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, resize(36)));
        //Score
        g.drawString(" : " + gameSnapshot.getScore().getPlayer1Score(),
                CELL_SIZE*8+resize(155), BASE_Y*2+resize(40));
        g.drawString(" : " + gameSnapshot.getScore().getPlayer2Score(),
                CELL_SIZE*8+resize(155), BASE_Y*4+resize(40));

        //Black Piece
        g.fillOval(CELL_SIZE*8 +resize(100), BASE_Y*2,
                CELL_SIZE - resize(10),
                CELL_SIZE - resize(10));
        //White piece
        g.setColor(Color.white);
        g.fillOval(CELL_SIZE*8 +resize(100), BASE_Y*4,
                CELL_SIZE - resize(10),
                CELL_SIZE - resize(10));

    }

}
