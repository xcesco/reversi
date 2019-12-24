package it.fmt.games.reversi.desktop.pages;

import it.fmt.games.reversi.desktop.ImageResources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static it.fmt.games.reversi.desktop.pages.SwingUtils.buildButton;
import static it.fmt.games.reversi.desktop.pages.SwingUtils.setGbc;

public class StartPage extends JPanel {
    private final JFrame jFrame;
    private GridBagConstraints gbc = new GridBagConstraints();
    private static final Color lightYellow = new Color(220, 220, 190);

    public StartPage(JFrame container) {
        this.jFrame = container;
        BufferedImage logoImage = ImageResources.getInstance().getLogoImage();

        setLayout(new GridBagLayout());

        JLabel picLabel = new JLabel(new ImageIcon(logoImage));
        setGbc(gbc, 0, 1, 1, 1);
        add(picLabel, gbc);

        JLabel mode = new JLabel("Please select mode: ");
        mode.setFont(new Font("Verdana", Font.BOLD, 24));
        setGbc(gbc, 0, 2, 1, 1);
        add(mode, gbc);

        int P1_VS_P2 = 1;
        buildButton(this, gbc, "PLAYER vs PLAYER", 3, actionEvent -> onButtonClick(P1_VS_P2));
        int P1_VS_CPU = 2;
        buildButton(this, gbc, "PLAYER vs CPU", 4, actionEvent -> onButtonClick(P1_VS_CPU));
        int CPU_VS_P2 = 3;
        buildButton(this, gbc, "CPU vs PLAYER", 5, actionEvent -> onButtonClick(CPU_VS_P2));
        int CPU_VS_CPU = 4;
        buildButton(this, gbc, "CPU vs CPU", 6, actionEvent -> onButtonClick(CPU_VS_CPU));

        setBackground(lightYellow);
        setVisible(true);
    }


    private void onButtonClick(int gameType) {
        SwingUtilities.invokeLater(() -> {
            jFrame.getContentPane().removeAll();
            jFrame.getContentPane().add(new GamePage(jFrame, gameType));
            jFrame.validate();
        });
    }
}
