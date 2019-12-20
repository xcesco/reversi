package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.GameSnapshot;

import java.awt.*;
import java.util.stream.IntStream;

import static it.fmt.games.reversi.desktop.GameCanvas.*;

public class LabelsDrawer implements Drawer {

     @Override
    public void draw(GameSnapshot gameSnapshot, Graphics g) {
        String[] Label = {"A", "B", "C", "D", "E", "F", "G", "H"};
        g.setFont(new Font("Arial", Font.PLAIN, resize(24)));
        g.setColor(Color.black);
        IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                g.drawString(Label[col], BASE_X + col * CELL_SIZE + resize(30),
                        GameCanvas.BASE_Y - resize(15)));
        IntStream.range(0, Board.BOARD_SIZE).forEach((row -> g.drawString(String.valueOf(row + 1),
                BASE_X - resize(25),
                GameCanvas.BASE_Y + row * CELL_SIZE + resize(45))));

        IntStream.range(0, Board.BOARD_SIZE).forEach(col -> g.drawString(Label[col],
                BASE_X + col * CELL_SIZE + resize(30),
                GameCanvas.BASE_Y + CELL_SIZE * 8 + resize(30)));
        IntStream.range(0, Board.BOARD_SIZE).forEach((row -> g.drawString(String.valueOf(row + 1),
                BASE_X + CELL_SIZE * 8 + resize(10),
                GameCanvas.BASE_Y + row * CELL_SIZE + resize(45))));
    }
}


