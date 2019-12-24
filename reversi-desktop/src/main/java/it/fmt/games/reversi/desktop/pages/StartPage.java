package it.fmt.games.reversi.desktop.pages;

import it.fmt.games.reversi.desktop.ImageResources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

public class StartPage extends JPanel {
    private final JFrame jFrame;
    private GridBagConstraints gbc = new GridBagConstraints();
    private static final Color lightYellow = new Color(220, 220, 190);

    public StartPage(JFrame container) {
        this.jFrame=container;
        Dimension btnDim = new Dimension(250, 40);
        BufferedImage logoImage = ImageResources.getInstance().getLogoImage();

        setLayout(new GridBagLayout());

        JLabel picLabel = new JLabel(new ImageIcon(logoImage));
        setGbc(0, 1, 1, 1);
        add(picLabel, gbc);

        JLabel mode = new JLabel("Please select mode: ");
        mode.setFont(new Font("Verdana", Font.BOLD, 24));
        setGbc(0, 2, 1, 1);
        add(mode, gbc);

        int P1_VS_P2 = 1;
        buildButton(btnDim, "PLAYER vs PLAYER", P1_VS_P2, 3);
        int P1_VS_CPU = 2;
        buildButton(btnDim, "PLAYER vs CPU", P1_VS_CPU, 4);
        int CPU_VS_P2 = 3;
        buildButton(btnDim, "CPU vs PLAYER", CPU_VS_P2, 5);
        int CPU_VS_CPU = 4;
        buildButton(btnDim, "CPU vs CPU", CPU_VS_CPU,6);

        setBackground(lightYellow);
        setVisible(true);
    }

    private void buildButton(Dimension btnDim, String text, int gameType, int position) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.PLAIN, 18));
        button.setFocusable(false);
        button.setPreferredSize(btnDim);
        setGbc(0, position, 1, 1);
        add(button, gbc);
        button.addActionListener(actionEvent -> onButtonClick(gameType));
    }

    private void setGbc(int gridx, int gridy, int weightx, int weighty) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }

    private void onButtonClick(int gameType) {
            SwingUtilities.invokeLater(()-> {
                jFrame.getContentPane().removeAll();
                jFrame.getContentPane().add(new GamePage(jFrame, gameType));
                jFrame.validate();
            });
    }
}
