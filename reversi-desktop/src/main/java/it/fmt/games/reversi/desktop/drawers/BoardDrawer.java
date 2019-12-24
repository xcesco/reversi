package it.fmt.games.reversi.desktop.drawers;

import it.fmt.games.reversi.Player1;
import it.fmt.games.reversi.Player2;
import it.fmt.games.reversi.desktop.ImageResources;
import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.Piece;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.fmt.games.reversi.desktop.pages.GamePage.*;
import static it.fmt.games.reversi.model.Board.BOARD_SIZE;

public class BoardDrawer implements Drawer {
    private final BufferedImage boardImage;
    private final BufferedImage blackImage;
    private final BufferedImage whiteImage;
    private final Font fontArial;
    private final BufferedImage hintImage;
    private final Player1 player1;
    private final Player2 player2;

    public BoardDrawer(Player1 player1, Player2 player2) {
        boardImage = ImageResources.getInstance().getBoardImage();
        blackImage = ImageResources.getInstance().getBlackPieceImage();
        whiteImage = ImageResources.getInstance().getWhitePieceImage();
        hintImage = ImageResources.getInstance().getHintImage();
        fontArial = new Font("Arial", Font.BOLD, calculateSize(48));
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void draw(GameSnapshot gameSnapshot, Graphics g) {
        g.setColor(lightYellow);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        g.setFont(fontArial);

        if (gameSnapshot != null) {
            g.setColor(brown);
            g.fillRect(BASE_X - calculateSize(10),
                    BASE_Y - calculateSize(10),
                    CELL_SIZE * BOARD_SIZE + calculateSize(20),
                    CELL_SIZE * BOARD_SIZE + calculateSize(20));
            g.setColor(Color.BLACK);
            g.setFont(fontArial);
            g.drawString("REVERSI", calculateSize(300), calculateSize(50));
            g.drawImage(this.boardImage, BASE_X, BASE_Y, null);

            gameSnapshot.getBoard().getCellStream().forEach(item -> {
                if (item.getPiece() != Piece.EMPTY) {
                    if ((item.getPiece() == Piece.PLAYER_2)) {
                        g.drawImage(this.whiteImage,
                                BASE_X + item.getCoordinates().getRow() * CELL_SIZE,
                                BASE_Y + item.getCoordinates().getColumn() * CELL_SIZE,
                                null);
                    } else {
                        g.drawImage(this.blackImage,
                                BASE_X + item.getCoordinates().getRow() * CELL_SIZE,
                                BASE_Y + item.getCoordinates().getColumn() * CELL_SIZE,
                                null);
                    }
                }
            });

            if (showHints(gameSnapshot.getActivePiece())) {
                gameSnapshot.getAvailableMoves().getMovesActivePlayer().forEach(item -> {
                    g.drawImage(this.hintImage,
                            BASE_X + item.getRow() * CELL_SIZE,
                            BASE_Y + item.getColumn() * CELL_SIZE,
                            null);
                });
            }
        }
    }

    private boolean showHints(Piece activePiece) {
        if (player1.isHumanPlayer() && activePiece == player1.getPiece()) return true;
        if (player2.isHumanPlayer() && activePiece == player2.getPiece()) return true;

        return false;
    }
}
