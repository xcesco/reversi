package it.fmt.games.reversi.desktop.drawers;

import it.fmt.games.reversi.desktop.pages.GamePage;
import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.GameSnapshot;

import java.awt.*;
import java.util.stream.IntStream;

import static it.fmt.games.reversi.desktop.pages.GamePage.*;
import static it.fmt.games.reversi.model.Board.BOARD_SIZE;

public class LabelsDrawer implements Drawer {

     @Override
    public void draw(GameSnapshot gameSnapshot, Graphics g) {
        String[] Label = {"A", "B", "C", "D", "E", "F", "G", "H"};
        g.setFont(new Font("Arial", Font.PLAIN, calculateSize(24)));
        g.setColor(Color.black);
        IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                g.drawString(Label[col], BASE_X + col * CELL_SIZE + calculateSize(30),
                        GamePage.BASE_Y - calculateSize(15)));
        IntStream.range(0, Board.BOARD_SIZE).forEach((row -> g.drawString(String.valueOf(row + 1),
                BASE_X - calculateSize(25),
                GamePage.BASE_Y + row * CELL_SIZE + calculateSize(45))));

        IntStream.range(0, Board.BOARD_SIZE).forEach(col -> g.drawString(Label[col],
                BASE_X + col * CELL_SIZE + calculateSize(30),
                GamePage.BASE_Y + CELL_SIZE * BOARD_SIZE + calculateSize(30)));
        IntStream.range(0, Board.BOARD_SIZE).forEach((row -> g.drawString(String.valueOf(row + 1),
                BASE_X + CELL_SIZE * BOARD_SIZE + calculateSize(10),
                GamePage.BASE_Y + row * CELL_SIZE + calculateSize(45))));
    }
}


