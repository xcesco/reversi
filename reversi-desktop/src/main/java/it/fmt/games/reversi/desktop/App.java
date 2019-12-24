package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.desktop.pages.GamePage;
import it.fmt.games.reversi.desktop.pages.StartPage;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class App extends JFrame {

    public static void main(String[] args) {
        App app = new App();
        app.setVisible(true);
    }

    public App() {
        BufferedImage logoImage = ImageResources.getInstance().getLogoImage();
        setIconImage(logoImage);

        setTitle("FMT-Reversi");
        setSize(GamePage.WIDTH, GamePage.HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SwingUtilities.invokeLater(()-> getContentPane().add(new StartPage(this)));
    }




}