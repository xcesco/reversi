package it.fmt.games.reversi.desktop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SwingTest extends JFrame {
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

        var image = ImageIO.read(new File("C:\\Users\\Teo_e\\IdeaProjects\\reversi\\reversi-desktop\\src\\main\\resources\\logo.png"));
        setIconImage(image);
        JLabel picLabel = new JLabel(new ImageIcon(image));
        getContentPane().add(picLabel);
        getContentPane().repaint();
        setTitle("FMT-Reversi");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton player_vs_player = new JButton("Player VS Player");
        JButton player_vs_cpu = new JButton("Player VS CPU");
        JButton cpu_vs_player = new JButton("CPU VS Player");
        JButton cpu_vs_cpu = new JButton("CPU VS CPU");

        player_vs_player.setBounds(200, 300, 200, 50);
        player_vs_cpu.setBounds(200, 400, 200, 50);
        cpu_vs_player.setBounds(200, 500, 200, 50);
        cpu_vs_cpu.setBounds(200, 600, 200, 50);

        getContentPane().setLayout(null);
        getContentPane().add(player_vs_player);
        getContentPane().add(player_vs_cpu);
        getContentPane().add(cpu_vs_player);
        getContentPane().add(cpu_vs_cpu);



        player_vs_player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initGame(P1_VS_P2);
            }
        });
        player_vs_cpu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initGame(P1_VS_CPU);
            }
        });
        cpu_vs_player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initGame(CPU_VS_P2);
            }
        });
        cpu_vs_cpu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initGame(CPU_VS_CPU);
            }
        });

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