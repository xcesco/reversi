package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.DecisionHandlerType;
import it.fmt.games.reversi.GameRenderer;
import it.fmt.games.reversi.PlayerFactory;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.GameSnapshot;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

public class GameCanvas extends Canvas implements MouseListener, GameRenderer {
    public static double RESIZE = 1;
    public static final int CELL_SIZE = resize(70);
    public static final int BASE_X = resize(35);
    public static final int BASE_Y = resize(105);
    private static final boolean SHOW_LABELS = true;
    public static final Color darkGreen = new Color(0, 120, 0);
    public static final Color brown = new Color(153, 102, 0);
    public static final Color lightYellow = new Color(220, 220, 190);
    public static final int WIDTH = resize(900);
    public static final int HEIGHT = resize(768);
    public static String winner = "";
    public GameSnapshot gameSnapshot;


    private GameLogicThread gameLogic;


//    public static void main(String[] args) {
//        JFrame frame = new JFrame("FMT-Reversi");
//        frame.setSize(WIDTH, HEIGHT);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.add(new GameCanvas(1));
//        frame.setVisible(true);
//
//    }

    public GameCanvas(int game) {
        selectPlayer(game);
        gameLogic.start();
        addMouseListener(this);
//        boxes = new Rectangle[8][8];
    }

    private void selectPlayer(int game) {
        switch (game) {
            case 1:
                gameLogic = new GameLogicThread(this, PlayerFactory.createHumanPlayer1(), PlayerFactory.createHumanPlayer2(), this);
                break;
            case 2:
                gameLogic = new GameLogicThread(this, PlayerFactory.createHumanPlayer1(), PlayerFactory.createRoboPlayer2(DecisionHandlerType.RANDOM), this);
                break;
            case 3:
                gameLogic = new GameLogicThread(this, PlayerFactory.createRoboPlayer1(DecisionHandlerType.RANDOM), PlayerFactory.createHumanPlayer2(), this);
                break;
            case 4:
                gameLogic = new GameLogicThread(this, PlayerFactory.createRoboPlayer1(DecisionHandlerType.RANDOM), PlayerFactory.createRoboPlayer2(DecisionHandlerType.RANDOM), this);
                break;
            default:
                break;
        }
    }

    public void paint(Graphics g) {
        TurnDrawer drawTurn = new TurnDrawer();
        LabelsDrawer drawLabels = new LabelsDrawer();
        ScoreDrawer drawScore = new ScoreDrawer();
        AvailableMovesDrawer drawAvailableMoves = new AvailableMovesDrawer();
        BoardDrawer drawBoard = new BoardDrawer();

        g.setColor(lightYellow);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);

        g.setFont(new Font("Arial", Font.BOLD, resize(48)));

        if (this.gameSnapshot != null) {

            drawBoard.draw(gameSnapshot,g);
            drawAvailableMoves.draw(gameSnapshot, g);
            //Score
            drawScore.draw(gameSnapshot, g);
            //Turn
            drawTurn.draw(gameSnapshot, g);
            // labels
            if (SHOW_LABELS) {
                drawLabels.draw(gameSnapshot, g);
            }

            if (gameSnapshot.getStatus().isGameOver()) {
                winner = new PrintStatus(gameSnapshot.getStatus()).getWinner();

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Print Winner
                WinnerDrawer printWinner = new WinnerDrawer(winner);
                printWinner.draw(gameSnapshot, g);
            }
        }
    }


    public static int resize(int i) {
        return (int) (i / RESIZE);
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
