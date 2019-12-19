package it.fmt.games.reversi.desktop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class SwingTest extends JFrame {
    private static final Color lightYellow = new Color(220, 220, 190);
    private final int P1_VS_P2=1;
    private final int P1_VS_CPU=2;
    private final int CPU_VS_P2=3;
    private final int CPU_VS_CPU=4;

    public SwingTest() throws IOException {
        initUI();
    }

    public static void main(String[] args) throws IOException {
        SwingTest ex = new SwingTest();
        ex.setVisible(true);

    }

    public final void initUI() throws IOException {
        Dimension btnDim=new Dimension(200,30);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = Objects.requireNonNull(classLoader.getResource("logo.png")).getPath();
        BufferedImage image = ImageIO.read(new File(path));
        setIconImage(image);
        BufferedImage resized = resize(image, 250, 250);

        setTitle("FMT-Reversi");
        setSize(300, 500);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel title = new JLabel("Welcome to FMT-Reversi");
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        container.add(title,gbc);

        JLabel picLabel = new JLabel(new ImageIcon(resized));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.weightx=1;
        gbc.weighty=1;
        container.add(picLabel,gbc);

        JLabel mode = new JLabel("Please select mode: ");
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.weightx=1;
        gbc.weighty=1;
        container.add(mode,gbc);

        JButton player_vs_player = new JButton("Player VS Player");
        player_vs_player.setPreferredSize(btnDim);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.weightx=1;
        gbc.weighty=1;
        container.add(player_vs_player,gbc);

        JButton player_vs_cpu = new JButton("Player VS CPU");
        player_vs_cpu.setPreferredSize(btnDim);
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.weightx=1;
        gbc.weighty=1;
        container.add(player_vs_cpu,gbc);

        JButton cpu_vs_player = new JButton("CPU VS Player");
        cpu_vs_player.setPreferredSize(btnDim);
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.weightx=1;
        gbc.weighty=1;
        container.add(cpu_vs_player,gbc);

        JButton cpu_vs_cpu = new JButton("CPU VS CPU");
        cpu_vs_cpu.setPreferredSize(btnDim);
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.weightx=1;
        gbc.weighty=1;
        container.add(cpu_vs_cpu,gbc);

        container.setBackground(lightYellow);

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        player_vs_player.addActionListener(actionEvent -> initGame(P1_VS_P2));
        player_vs_cpu.addActionListener(actionEvent -> initGame(P1_VS_CPU));
        cpu_vs_player.addActionListener(actionEvent -> initGame(CPU_VS_P2));
        cpu_vs_cpu.addActionListener(actionEvent -> initGame(CPU_VS_CPU));

    }
private void initGame(int gameType){
    setVisible(false);
    dispose();
    JFrame frame = new JFrame("FMT-Reversi");
    frame.setSize(App.WIDTH, App.HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.add(new App(gameType));
    frame.setVisible(true);

}
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}