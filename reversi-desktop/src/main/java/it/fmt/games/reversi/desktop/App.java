package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.desktop.pages.GamePage;
import it.fmt.games.reversi.desktop.pages.StartPage;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class App extends JFrame {


    public static void main(String[] args) {
        App reversiGame = new App();
        reversiGame.setVisible(true);
    }

    public App() {
        BufferedImage logoImage = ImageReader.resize(Objects.requireNonNull(ImageReader.readImage("logo.png")), 250, 250);
        setIconImage(logoImage);

        setTitle("FMT-Reversi");
        setSize(GamePage.WIDTH, GamePage.HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SwingUtilities.invokeLater(()-> getContentPane().add(new StartPage(this)));
    }




}