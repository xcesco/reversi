package it.fmt.games.reversi.desktop.pages;

import it.fmt.games.reversi.desktop.ImageResources;
import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static it.fmt.games.reversi.desktop.pages.SwingUtils.buildButton;
import static it.fmt.games.reversi.desktop.pages.SwingUtils.setGbc;

public class EndPage extends JPanel {
    private static final int PLAY_AGAIN = 1;
    private static final int EXIT = 2;
    private final JFrame jFrame;
    private GridBagConstraints gbc = new GridBagConstraints();
    private static final Color lightYellow = new Color(220, 220, 190);

    public EndPage(JFrame jFrame, GameSnapshot gameSnapshot) {
        this.jFrame = jFrame;

        GameStatus gameStatus = gameSnapshot.getStatus();
        int player1Score = gameSnapshot.getScore().getPlayer1Score();
        int player2Score = gameSnapshot.getScore().getPlayer2Score();

        BufferedImage image;
        String message;
        switch (gameStatus) {
            case DRAW:
                image = ImageResources.getInstance().getDraw();
                message = String.format("Game is draw: %s - %s", player1Score, player2Score);
                break;
            case PLAYER1_WIN:
                image = ImageResources.getInstance().getWinnerPlayer1();
                message = String.format("BLACK wins: %s - %s", player1Score, player2Score);
                break;
            case PLAYER2_WIN:
                image = ImageResources.getInstance().getWinnerPlayer2();
                message = String.format("WHITE wins: %s - %s", player2Score, player1Score);
                break;
            default:
                image = null;
                message = "Inconsistent status";
                break;

        }

        setLayout(new GridBagLayout());

        JLabel picLabel = new JLabel(new ImageIcon(image));
        setGbc(gbc, 0, 1, 1, 1);
        add(picLabel, gbc);

        JLabel mode = new JLabel(message);
        mode.setFont(new Font("Verdana", Font.BOLD, 24));
        setGbc(gbc, 0, 2, 1, 1);
        add(mode, gbc);

        buildButton(this, gbc, "PLAY AGAIN", 3, actionEvent -> onButtonClick(PLAY_AGAIN));
        buildButton(this, gbc, "EXIT", 4, actionEvent -> onButtonClick(EXIT));

        setBackground(lightYellow);
        setVisible(true);
    }

    private void onButtonClick(int operation) {
        setVisible(false);
        jFrame.remove(this);
        if (operation == PLAY_AGAIN) {
            jFrame.getContentPane().add(new StartPage(jFrame));
        } else {
            System.exit(0);
        }
    }
}
