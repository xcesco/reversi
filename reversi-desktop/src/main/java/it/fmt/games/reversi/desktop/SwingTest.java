package it.fmt.games.reversi.desktop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class SwingTest extends JFrame {
    private final int P1_VS_P2=1;
    private final int P1_VS_CPU=2;
    private final int CPU_VS_P2=3;
    private final int CPU_VS_CPU=4;

    public SwingTest() {
        initUI();
    }

    public static void main(String[] args) {
        SwingTest ex = new SwingTest();
        ex.setVisible(true);

    }

    public final void initUI() {
        var gameLogo = new ImageIcon("src/main/resources/logo.png");
        setIconImage(gameLogo.getImage());
        setTitle("FMT-Reversi");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton player_vs_playerButton = new JButton("Player VS Player");
        JButton player_vs_cpuButton = new JButton("Player VS CPU");
        JButton cpu_vs_player = new JButton("CPU VS Player");
        JButton cpu_vs_cpuButton = new JButton("CPU VS CPU");
        player_vs_playerButton.setBounds(200, 300, 200, 50);
        player_vs_cpuButton.setBounds(200, 400, 200, 50);
        cpu_vs_player.setBounds(200, 500, 200, 50);
        cpu_vs_cpuButton.setBounds(200, 600, 200, 50);
        getContentPane().setLayout(null);
        getContentPane().add(player_vs_playerButton);
        getContentPane().add(player_vs_cpuButton);
        getContentPane().add(cpu_vs_player);
        getContentPane().add(cpu_vs_cpuButton);



        player_vs_playerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initGame(P1_VS_P2);
            }
        });
        player_vs_cpuButton.addActionListener(new ActionListener() {
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
        cpu_vs_cpuButton.addActionListener(new ActionListener() {
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