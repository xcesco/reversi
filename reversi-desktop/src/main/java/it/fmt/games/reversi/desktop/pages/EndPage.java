package it.fmt.games.reversi.desktop.pages;

import it.fmt.games.reversi.desktop.ImageResources;
import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EndPage extends JPanel {
    private static final int PLAY_AGAIN = 1;
    private static final int EXIT = 2;
    private final JFrame jFrame;
    private GridBagConstraints gbc = new GridBagConstraints();
    private static final Color lightYellow = new Color(220, 220, 190);

    public EndPage(JFrame jFrame, GameSnapshot gameSnapshot) {
        this.jFrame=jFrame;
        Dimension buttonDim = new Dimension(300, 60);

        GameStatus gameStatus=gameSnapshot.getStatus();
        int player1Score=gameSnapshot.getScore().getPlayer1Score();
        int player2Score=gameSnapshot.getScore().getPlayer2Score();

        BufferedImage image;
        String message;
        switch (gameStatus) {
            case DRAW:
                image=ImageResources.getInstance().getDraw();
                message=String.format("Game is draw: %s - %s", player1Score, player2Score);
                break;
            case PLAYER1_WIN:
                image=ImageResources.getInstance().getWinnerPlayer1();
                message=String.format("BLACK wins: %s - %s", player1Score, player2Score);
                break;
            case PLAYER2_WIN:
                image=ImageResources.getInstance().getWinnerPlayer2();
                message=String.format("WHITE wins: %s - %s", player2Score, player1Score);
                break;
            default:
                image=null;
                message="Inconsistent status";
                break;

        }

        setLayout(new GridBagLayout());

        JLabel picLabel = new JLabel(new ImageIcon(image));
        setGbc(0, 1, 1, 1);
        add(picLabel, gbc);

        JLabel mode = new JLabel(message);
        mode.setFont(new Font("Verdana", Font.BOLD, 24));
        setGbc(0, 2, 1, 1);
        add(mode, gbc);

        buildButton(buttonDim, "PLAY AGAIN", PLAY_AGAIN, 3);
        buildButton(buttonDim, "EXIT", EXIT, 4);

        setBackground(lightYellow);
        setVisible(true);
    }

    private void buildButton(Dimension btnDim, String text, int operation, int position) {
        JButton button = new JButton(text);
        button.setPreferredSize(btnDim);
        button.setFont(new Font("Verdana", Font.PLAIN, 18));
        button.setFocusable(false);
        setGbc(0, position, 1, 1);
        add(button, gbc);
        button.addActionListener(actionEvent -> onButtonClick(operation));
    }

    private void setGbc(int gridx, int gridy, int weightx, int weighty) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }

    private void onButtonClick(int operation) {
        setVisible(false);
        jFrame.remove(this);
        if (operation==PLAY_AGAIN) {
            jFrame.getContentPane().add(new StartPage(jFrame));
        } else {
            System.exit(0);
        }
    }
}
