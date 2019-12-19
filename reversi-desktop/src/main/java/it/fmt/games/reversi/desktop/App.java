package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.DecisionHandlerType;
import it.fmt.games.reversi.GameRenderer;
import it.fmt.games.reversi.PlayerFactory;
import it.fmt.games.reversi.Reversi;
import it.fmt.games.reversi.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class App extends Canvas implements MouseListener, GameRenderer {
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
    private boolean started = false;
    private Rectangle[][] boxes;

    private Reversi reversi;
    private GameLogicThread gameLogic;


    public static void main(String[] args) {
        JFrame frame = new JFrame("FMT-Reversi");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new App(1));
        frame.setVisible(true);

    }

    public App(int game) {
        selectPlayer(game);
        gameLogic.start();
        addMouseListener(this);
        boxes = new Rectangle[8][8];
    }

    private void selectPlayer(int game) {
        switch (game){
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
        DrawTurn drawTurn = new DrawTurn(gameSnapshot, g);
        DrawLabels drawLabels = new DrawLabels(g);
        DrawScore drawScore = new DrawScore(gameSnapshot, g);
        DrawAvailableMoves drawAvailableMoves = new DrawAvailableMoves(gameSnapshot, g);

        IntStream.range(0, Board.BOARD_SIZE)
                .forEach(row -> IntStream.range(0, Board.BOARD_SIZE)
                        .forEach(col -> boxes[row][col] = new Rectangle(BASE_X + col * CELL_SIZE, BASE_Y + row * CELL_SIZE, CELL_SIZE, CELL_SIZE)));

        g.setColor(lightYellow);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font("Arial", Font.BOLD, resize(48)));

//        if (!started) {
//            g.drawString("REVERSI", resize(300), resize(300));
//            g.drawString("Click to Play", resize(270), resize(400));
//        } else if (this.gameSnapshot != null) {
         if (this.gameSnapshot != null){
            // board
            g.setColor(brown);
            g.fillRect(BASE_X - resize(10),
                    BASE_Y - resize(10),
                    CELL_SIZE * 8 + resize(20),
                    CELL_SIZE * 8 + resize(20));
            g.setColor(darkGreen);
            g.fillRect(BASE_X, BASE_Y, CELL_SIZE * 8, CELL_SIZE * 8);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, resize(48)));
            g.drawString("REVERSI", resize(300), resize(50));
            IntStream.range(0, Board.BOARD_SIZE)
                    .forEach(row -> IntStream.range(0, Board.BOARD_SIZE)
                            .forEach(col -> g2.draw(boxes[row][col])));

            gameSnapshot.getBoard().getCellStream().forEach(item -> {
                if (item.getPiece() != Piece.EMPTY) {
                    if ((item.getPiece() == Piece.PLAYER_2)) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    g.fillOval(BASE_X + item.getCoordinates().getRow() * CELL_SIZE + resize(5),
                            BASE_Y + item.getCoordinates().getColumn() * CELL_SIZE + resize(5),
                            CELL_SIZE - resize(10),
                            CELL_SIZE - resize(10));
                }

            });
            //Legal Moves
            drawAvailableMoves.DraW();
            //Score
            drawScore.DraW();
            //Turn
            drawTurn.DraW();
            // labels
            if (SHOW_LABELS) {
                drawLabels.DraW();
            }

            if (gameSnapshot.getStatus().isGameOver()) {
                winner = new PrintStatus(gameSnapshot.getStatus()).getWinner();

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Print Winner
                PrintWinner printWinner = new PrintWinner(gameSnapshot, g, winner);
                printWinner.DraW();

            }

        }
    }


    public static int resize(int i) {
        return (int) (i / RESIZE);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
//        if (!started) {
////            reversi = new Reversi(this, this, PlayerFactory.createRoboPlayer1(new DesktopDecisionHandler()), PlayerFactory.createRoboPlayer2(new DesktopDecisionHandler()));
//
////----------------------> Human vs Human
//            gameLogic = new GameLogicThread(this, PlayerFactory.createHumanPlayer1(), PlayerFactory.createHumanPlayer2(), this);
////----------------------> Human vs CPU
////            gameLogic = new GameLogicThread(this, PlayerFactory.createHumanPlayer1(), PlayerFactory.createRoboPlayer2(), this);
////----------------------> CPU vs CPU
////            gameLogic = new GameLogicThread(this, PlayerFactory.createRoboPlayer1(), PlayerFactory.createRoboPlayer2(), this);
//
//            gameLogic.start();
//            started = true;
//            repaint();
//        } else {
            repaint();
            int col = (e.getY() - BASE_Y) / CELL_SIZE;
            int row = (e.getX() - BASE_X) / CELL_SIZE;
            //System.out.println(String.format("%s",e.getPoint()));
            Coordinates coordinates = Coordinates.of(row, col);

            synchronized (gameLogic.acceptedMove) {
                gameLogic.acceptedMove.setCoordinates(coordinates);
                gameLogic.acceptedMove.notifyAll();
            }
//        }

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
