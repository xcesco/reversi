package it.fmt.games.reversi.desktop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        var image = ImageIO.read(new File(path));
        setIconImage(image);


        setTitle("FMT-Reversi");
        setSize(600, 800);



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

        JLabel picLabel = new JLabel(new ImageIcon(image));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.weightx=1;
        gbc.weighty=1;
        container.add(picLabel,gbc);

        JLabel mode = new JLabel("Please select mode: ");
        gbc.gridx=0;
        gbc.gridy=2;
//        gbc.weightx=1;
//        gbc.weighty=1;
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


        /*Container container = getContentPane();
        container.setLayout(new FlowLayout());

        JPanel r = new JPanel(new BorderLayout());
        try {
            r.add(new JLabel(new ImageIcon(ImageIO.read(new File("./temp.png")))));
        }catch (IOException e) {
            e.getMessage().toString();
        }
        Jbutton j = new JButton("Recycle Item");
        r.add(j, BorderLayout.SOUTH);
        container.add(r);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,500);
        setVisible(true);*/



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

}