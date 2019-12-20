package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.Piece;

import java.awt.*;
import java.util.stream.IntStream;

import static it.fmt.games.reversi.desktop.GameCanvas.*;
import static it.fmt.games.reversi.model.Board.BOARD_SIZE;

public class BoardDrawer implements Drawer {
    private Rectangle[][] boxes;

    @Override
    public void draw(GameSnapshot gameSnapshot, Graphics g) {
        boxes = new Rectangle[BOARD_SIZE][BOARD_SIZE];
        IntStream.range(0, BOARD_SIZE)
                .forEach(row -> IntStream.range(0, BOARD_SIZE)
                        .forEach(col -> boxes[row][col] = new Rectangle(BASE_X + col * CELL_SIZE, BASE_Y + row * CELL_SIZE, CELL_SIZE, CELL_SIZE)));

        g.setColor(lightYellow);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font("Arial", Font.BOLD, resize(48)));

        if (gameSnapshot != null) {
            // board
            g.setColor(brown);
            g.fillRect(BASE_X - resize(10),
                    BASE_Y - resize(10),
                    CELL_SIZE * BOARD_SIZE + resize(20),
                    CELL_SIZE * BOARD_SIZE + resize(20));
            g.setColor(darkGreen);
            g.fillRect(BASE_X, BASE_Y, CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, resize(48)));
            g.drawString("REVERSI", resize(300), resize(50));
            IntStream.range(0, BOARD_SIZE)
                    .forEach(row -> IntStream.range(0, BOARD_SIZE)
                            .forEach(col -> g2.draw(boxes[row][col])));

            gameSnapshot.getBoard().getCellStream().forEach(item -> {
                if (item.getPiece() != Piece.EMPTY) {
                    if ((item.getPiece() == Piece.PLAYER_2)) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    g.fillOval(BASE_X + item.getCoordinates().getRow() * CELL_SIZE + resize(5),
                            BASE_Y + item.getCoordinates().getColumn() * CELL_SIZE + resize(5),
                            CELL_SIZE - resize(10),
                            CELL_SIZE - resize(10));
                }
            });
        }
    }
}
