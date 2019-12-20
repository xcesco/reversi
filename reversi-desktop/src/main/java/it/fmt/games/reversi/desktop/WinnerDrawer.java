package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.model.GameSnapshot;

import java.awt.*;

import static it.fmt.games.reversi.desktop.GameCanvas.*;

public class WinnerDrawer implements Drawer {
    private String winner;

    public WinnerDrawer(String winner) {
        this.winner = winner;
    }

    @Override
    public void draw(GameSnapshot gameSnapshot, Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(2 * BASE_X, 2 * BASE_Y, 500, 250);
        g.setColor(new Color(255, 153, 0));
        g.setFont(new Font("Arial", Font.BOLD, resize(48)));
        g.drawString("Congratulations!!", resize(125), resize(300));
        g.drawString(winner, resize(120), resize(390));
    }
}
