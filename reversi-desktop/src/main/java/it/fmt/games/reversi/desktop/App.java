package it.fmt.games.reversi.desktop;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class App extends JFrame {
    private static final Color lightYellow = new Color(220, 220, 190);
    private final int P1_VS_P2 = 1;
    private final int P1_VS_CPU = 2;
    private final int CPU_VS_P2 = 3;
    private final int CPU_VS_CPU = 4;
    private BufferedImage image;
    private GridBagConstraints gbc = new GridBagConstraints();

    public static void main(String[] args) throws IOException {
        App ex = new App();
        ex.setVisible(true);
    }

    public App() throws IOException {
        Dimension btnDim = new Dimension(200, 30);
        image = new ImageReader().readImage("logo.png");
        setIconImage(image);
        BufferedImage resized = new ImageReader().resize(image, 250, 250);
        setTitle("FMT-Reversi");
        setSize(300, 500);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        JLabel title = new JLabel("Welcome to FMT-Reversi");
        setGbc(0, 1, 1, 1);
        gbc.anchor = GridBagConstraints.PAGE_START;
        container.add(title, gbc);

        JLabel picLabel = new JLabel(new ImageIcon(resized));
        setGbc(0, 1, 1, 1);
        container.add(picLabel, gbc);

        JLabel mode = new JLabel("Please select mode: ");
        setGbc(0, 2, 1, 1);
        container.add(mode, gbc);

        JButton playerVsPlayer = new JButton("Player VS Player");
        playerVsPlayer.setPreferredSize(btnDim);
        setGbc(0, 3, 1, 1);
        container.add(playerVsPlayer, gbc);

        JButton playerVsCcpu = new JButton("Player VS CPU");
        playerVsCcpu.setPreferredSize(btnDim);
        setGbc(0, 4, 1, 1);
        container.add(playerVsCcpu, gbc);

        JButton cpuVsPlayer = new JButton("CPU VS Player");
        cpuVsPlayer.setPreferredSize(btnDim);
        setGbc(0, 5, 1, 1);
        container.add(cpuVsPlayer, gbc);

        JButton cpuVsCpu = new JButton("CPU VS CPU");
        cpuVsCpu.setPreferredSize(btnDim);
        setGbc(0, 6, 1, 1);
        container.add(cpuVsCpu, gbc);

        container.setBackground(lightYellow);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        playerVsPlayer.addActionListener(actionEvent -> initGame(P1_VS_P2));
        playerVsCcpu.addActionListener(actionEvent -> initGame(P1_VS_CPU));
        cpuVsPlayer.addActionListener(actionEvent -> initGame(CPU_VS_P2));
        cpuVsCpu.addActionListener(actionEvent -> initGame(CPU_VS_CPU));
    }

    private void initGame(int gameType) {
        setVisible(false);
        dispose();
        JFrame frame = new JFrame("FMT-Reversi");
        frame.setSize(GameCanvas.WIDTH, GameCanvas.HEIGHT);
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GameCanvas(gameType));
        frame.setVisible(true);
    }

    private void setGbc(int gridx, int gridy, int weightx, int weighty) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }
}