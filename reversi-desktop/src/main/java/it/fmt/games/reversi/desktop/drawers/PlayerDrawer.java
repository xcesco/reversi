package it.fmt.games.reversi.desktop.drawers;

import it.fmt.games.reversi.model.GameSnapshot;

import java.awt.*;

import static it.fmt.games.reversi.desktop.pages.GamePage.*;
import static it.fmt.games.reversi.model.Board.BOARD_SIZE;

public class PlayerDrawer implements Drawer {
    private final String player1string;
    private final String player2string;


    public PlayerDrawer(String player1string, String player2string) {
        this.player1string = player1string;
        this.player2string = player2string;
    }

    @Override
    public void draw(GameSnapshot gameSnapshot, Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, calculateSize(32)));
        g.drawString(player1string,
                CELL_SIZE * BOARD_SIZE + calculateSize(120),
                BASE_Y * 2 -calculateSize(10));
        g.drawString(player2string,
                CELL_SIZE * BOARD_SIZE + calculateSize(120),
                BASE_Y * 4 -calculateSize(10));
    }
}
