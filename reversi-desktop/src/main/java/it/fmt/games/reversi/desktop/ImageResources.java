package it.fmt.games.reversi.desktop;

import java.awt.image.BufferedImage;
import java.util.Objects;

import static it.fmt.games.reversi.desktop.pages.GamePage.CELL_SIZE;
import static it.fmt.games.reversi.model.Board.BOARD_SIZE;

public class ImageResources {
    private static ImageResources instance;
    private BufferedImage boardImage;
    private BufferedImage blackPieceImage;
    private BufferedImage whitePieceImage;
    private BufferedImage hintsPieceImage;
    private BufferedImage player1WinnerImage;
    private BufferedImage player2WinnerImage;
    private BufferedImage drawImage;

    public static ImageResources getInstance() {
        if (instance==null) {
            instance=new ImageResources();
        }
        return instance;
    }
    private BufferedImage logoImage;

    public BufferedImage getLogoImage() {
        if (logoImage == null) {
            logoImage = ImageReader.resize(ImageReader.readImage("logo.png"), 250, 250);
        }
        return logoImage;
    }

    public BufferedImage getBoardImage() {
        if (boardImage==null) {
            boardImage = ImageReader.resize(Objects.requireNonNull(ImageReader.readImage("board_2048.jpg")), CELL_SIZE*BOARD_SIZE
                    , CELL_SIZE*BOARD_SIZE);
        }
        return boardImage;
    }

    public BufferedImage getBlackPieceImage() {
        if (blackPieceImage==null) {
            blackPieceImage = ImageReader.resize(Objects.requireNonNull(ImageReader.readImage("black_256.png")), CELL_SIZE, CELL_SIZE);
        }
        return blackPieceImage;
    }

    public BufferedImage getWhitePieceImage() {
        if (whitePieceImage==null) {
            whitePieceImage=ImageReader.resize(Objects.requireNonNull(ImageReader.readImage("white_256.png")), CELL_SIZE, CELL_SIZE);
        }
        return whitePieceImage;
    }

    public BufferedImage getHintImage() {
        if (hintsPieceImage==null) {
            hintsPieceImage = ImageReader.resize(Objects.requireNonNull(ImageReader.readImage("hint_256.png")), CELL_SIZE, CELL_SIZE);
        }
        return hintsPieceImage;
    }

    public BufferedImage getWinnerPlayer1() {
        if (player1WinnerImage==null) {
            player1WinnerImage = ImageReader.resize(Objects.requireNonNull(ImageReader.readImage("win_1_512.png")), CELL_SIZE*4, CELL_SIZE*4);
        }
        return player1WinnerImage;
    }

    public BufferedImage getWinnerPlayer2() {
        if (player2WinnerImage==null) {
            player2WinnerImage = ImageReader.resize(Objects.requireNonNull(ImageReader.readImage("win_2_512.png")), CELL_SIZE*4, CELL_SIZE*4);
        }
        return player2WinnerImage;
    }

    public BufferedImage getDraw() {
        if (drawImage==null) {
            drawImage = ImageReader.resize(Objects.requireNonNull(ImageReader.readImage("win_draw512.png")), CELL_SIZE*4, CELL_SIZE*4);
        }
        return drawImage;
    }
}
