package it.fmt.games.reversi.desktop.drawers;

import it.fmt.games.reversi.desktop.ImageReader;
import it.fmt.games.reversi.desktop.ImageResources;
import it.fmt.games.reversi.model.GameSnapshot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static it.fmt.games.reversi.desktop.pages.GamePage.*;
import static it.fmt.games.reversi.model.Board.BOARD_SIZE;

public class ScoreDrawer implements Drawer {
    private static final int OFFSET = 55;
    private final BufferedImage blackImage;
    private final BufferedImage whiteImage;
    private final Font fontArial;

    public ScoreDrawer() {
        blackImage = ImageResources.getInstance().getBlackPieceImage();
        whiteImage = ImageResources.getInstance().getWhitePieceImage();
        fontArial = new Font("Arial", Font.BOLD, calculateSize(36));
    }

    @Override
    public void draw(GameSnapshot gameSnapshot, Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(fontArial);

        g.drawString(" : " + gameSnapshot.getScore().getPlayer1Score(),
                CELL_SIZE * BOARD_SIZE + calculateSize(155 + OFFSET),
                BASE_Y * 2 + calculateSize(40));
        g.drawString(" : " + gameSnapshot.getScore().getPlayer2Score(),
                CELL_SIZE * BOARD_SIZE + calculateSize(155 + OFFSET),
                BASE_Y * 4 + calculateSize(40));

        g.drawImage(blackImage,
                CELL_SIZE * BOARD_SIZE + calculateSize(100 + OFFSET),
                BASE_Y * 2, CELL_SIZE, CELL_SIZE, null);
        g.drawImage(whiteImage,
                CELL_SIZE * BOARD_SIZE + calculateSize(100 + OFFSET),
                BASE_Y * 4, CELL_SIZE, CELL_SIZE, null);
    }
}
