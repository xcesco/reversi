package it.fmt.games.reversi.desktop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest extends JFrame {
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

        JButton player_vs_playerButton = new JButton("Player VS Player");
        JButton player_vs_cpuButton = new JButton("Player VS CPU");
        JButton cpu_vs_cpuButton = new JButton("CPU VS CPU");
        player_vs_playerButton.setBounds(200, 400, 200, 50);
        player_vs_cpuButton.setBounds(200, 500, 200, 50);
        cpu_vs_cpuButton.setBounds(200, 600, 200, 50);
        getContentPane().setLayout(null);
        getContentPane().add(player_vs_playerButton);
        getContentPane().add(player_vs_cpuButton);
        getContentPane().add(cpu_vs_cpuButton);



        player_vs_playerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                getContentPane().removeAll();
                getContentPane().revalidate();
                getContentPane().repaint();

                JFrame frame = new JFrame("FMT-Reversi");
                frame.setSize(App.WIDTH, App.HEIGHT);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.add(new App(1));
                frame.setVisible(true);
            }
        });
        player_vs_cpuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new App(2);
            }
        });
        cpu_vs_cpuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new App(3);
            }
        });
        setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


}