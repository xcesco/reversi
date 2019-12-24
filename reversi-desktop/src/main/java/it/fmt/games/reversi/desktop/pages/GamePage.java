package it.fmt.games.reversi.desktop.pages;

import it.fmt.games.reversi.GameRenderer;
import it.fmt.games.reversi.Player1;
import it.fmt.games.reversi.Player2;
import it.fmt.games.reversi.PlayerFactory;
import it.fmt.games.reversi.desktop.DesktopDecisionHandler;
import it.fmt.games.reversi.desktop.GameLogicThread;
import it.fmt.games.reversi.desktop.drawers.*;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.GameSnapshot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.abs;

public class GamePage extends Canvas implements MouseListener, GameRenderer {
    public static double RESIZE = 0.9;
    public static final int CELL_SIZE = calculateSize(70);
    public static final int BASE_X = calculateSize(35);
    public static final int BASE_Y = calculateSize(105);
    public static final Color brown = new Color(153, 102, 0);
    public static final Color lightYellow = new Color(220, 220, 190);
    public static final int WIDTH = calculateSize(900);
    public static final int HEIGHT = calculateSize(768);
    public static String winner = "";
    private final JFrame jFrame;
    private  TurnDrawer turnDrawer;
    private  LabelsDrawer labelsDrawer;
    private  ScoreDrawer scoreDrawer;
    private  BoardDrawer boardDrawer;
    private  Font arialFont;

    public GameSnapshot gameSnapshot;
    private GameLogicThread gameLogic;
    private Player1 player1;
    private Player2 player2;

    public GamePage(JFrame jFrame, int game) {
        this.jFrame = jFrame;
        preparePlayers(game);
        addMouseListener(this);

        prepareResources();

        runGame();
    }

    private void prepareResources() {
        arialFont=new Font("Verdana", Font.BOLD, calculateSize(48));
        turnDrawer = new TurnDrawer();
        labelsDrawer = new LabelsDrawer();
        scoreDrawer = new ScoreDrawer();
        boardDrawer = new BoardDrawer(player1, player2);
    }

    private void runGame() {
        gameLogic = new GameLogicThread(jFrame, this, player1, player2, this);
        gameLogic.start();
    }

    private void preparePlayers(int game) {
        switch (game) {
            case 1:
                player1 = PlayerFactory.createHumanPlayer1();
                player2 = PlayerFactory.createHumanPlayer2();
                break;
            case 2:
                player1 = PlayerFactory.createHumanPlayer1();
                player2 = PlayerFactory.createRoboPlayer2(new DesktopDecisionHandler());
                break;
            case 3:
                player1 = PlayerFactory.createRoboPlayer1(new DesktopDecisionHandler());
                player2 = PlayerFactory.createHumanPlayer2();
                break;
            case 4:
                player1 = PlayerFactory.createRoboPlayer1(new DesktopDecisionHandler());
                player2 = PlayerFactory.createRoboPlayer2(new DesktopDecisionHandler());
                break;
            default:
                break;
        }
    }

    public void paint(Graphics g) {
        g.setColor(lightYellow);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        g.setFont(arialFont);

        if (gameSnapshot != null) {
            boardDrawer.draw(gameSnapshot, g);
            scoreDrawer.draw(gameSnapshot, g);
            turnDrawer.draw(gameSnapshot, g);
            labelsDrawer.draw(gameSnapshot, g);
        }
    }

    public static int calculateSize(int i) {
        return (int) (i * RESIZE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
        int col = (e.getY() - BASE_Y) / CELL_SIZE;
        int row = (e.getX() - BASE_X) / CELL_SIZE;
        Coordinates coordinates = Coordinates.of(row, col);
        synchronized (gameLogic.acceptedMove) {
            gameLogic.acceptedMove.setCoordinates(coordinates);
            gameLogic.acceptedMove.notifyAll();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void render(GameSnapshot gameSnapshot) {
        this.gameSnapshot = gameSnapshot;
        repaint();
    }
}
