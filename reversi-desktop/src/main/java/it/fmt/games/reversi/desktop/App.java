package it.fmt.games.reversi.desktop;

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
    public static double RESIZE = 1.3;
    public static final int CELL_SIZE = (int) (70 / RESIZE);
    public static final int BASE_X = (int) (35 / RESIZE);
    public static final int BASE_Y = (int) (105 / RESIZE);
    public static final int OFFSET = (int) (25 / RESIZE);
    private static final boolean SHOW_LABELS = true;
    public static final Color darkGreen = new Color(0, 120, 0);
    public static final Color brown = new Color(153, 102, 0);
    public static final int WIDTH = (int) (650 / RESIZE);
    public static final int HEIGHT = (int) (900 / RESIZE);
    public static String winner = "";
    public GameSnapshot gameSnapshot;
    private boolean started = false;
    private Rectangle[][] boxes;


    private Reversi reversi;
    private GameLogicThread gameLogic;


    public static void main(String[] args) {
        JFrame win = new JFrame("Reversi");
        win.setSize(WIDTH, HEIGHT);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setResizable(false);
        win.add(new App());
        win.setVisible(true);
    }

    public App() {
        addMouseListener(this);
        boxes = new Rectangle[8][8];
        IntStream.range(0, Board.BOARD_SIZE)
                .forEach(row -> IntStream.range(0, Board.BOARD_SIZE)
                        .forEach(col -> boxes[row][col] = new Rectangle(BASE_X + col * CELL_SIZE, BASE_Y + row * CELL_SIZE, CELL_SIZE, CELL_SIZE)));
    }

    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font("Arial", Font.BOLD, (int) (48 / RESIZE)));

        if (!started) {
            g.drawString("REVERSI", (int) (225 / RESIZE), (int) (300 / RESIZE));
            g.drawString("Click to Play", (int) (190 / RESIZE), (int) (400 / RESIZE));
        } else if (this.gameSnapshot != null) {
            // board
            g.setColor(brown);
            g.fillRect(BASE_X - (int)(10/RESIZE),
                    BASE_Y - (int)(10/RESIZE),
                    CELL_SIZE * 8 + (int) (20 / RESIZE),
                    CELL_SIZE * 8 + (int) (20 / RESIZE));
            g.setColor(darkGreen);
            g.fillRect(BASE_X, BASE_Y, CELL_SIZE * 8, CELL_SIZE * 8);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, (int) (48 / RESIZE)));
            g.drawString("REVERSI", (int) (240 / RESIZE), (int) (50 / RESIZE));
            IntStream.range(0, Board.BOARD_SIZE)
                    .forEach(row -> IntStream.range(0, Board.BOARD_SIZE)
                            .forEach(col -> g2.draw(boxes[row][col])));

            this.gameSnapshot.getBoard().getCellStream().forEach(item -> {
                if (item.getPiece() != Piece.EMPTY) {
                    if ((item.getPiece() == Piece.PLAYER_1)) {
                        g.setColor(Color.white);
                    } else {
                        g.setColor(Color.black);
                    }
                    g.fillOval(BASE_X + item.getCoordinates().getRow() * CELL_SIZE + (int) (5 / RESIZE),
                            BASE_Y + item.getCoordinates().getColumn() * CELL_SIZE + (int) (5 / RESIZE),
                            CELL_SIZE - (int) (10 / RESIZE),
                            CELL_SIZE - (int) (10 / RESIZE));
                }

            });
            //Legal Moves
            this.gameSnapshot.getAvailableMoves().getMovesActivePlayer().forEach(item -> {
                g.setColor(Color.gray);
                g.fillOval(BASE_X + item.getRow() * CELL_SIZE + (int) (30 / RESIZE),
                        BASE_Y + item.getColumn() * CELL_SIZE + (int) (30 / RESIZE),
                        CELL_SIZE - (int) (60 / RESIZE),
                        CELL_SIZE - (int) (60 / RESIZE));
            });

            //Score
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, (int) (36 / RESIZE)));
            g.drawString(" : " + gameSnapshot.getScore().getPlayer2Score(),
                    (int) (100 / RESIZE), (int) (765 / RESIZE));
            g.drawString(" : " + gameSnapshot.getScore().getPlayer1Score(),
                    (int) (520 / RESIZE), (int) (765 / RESIZE));


            g.fillOval((int) (35 / RESIZE), (int) (725 / RESIZE),
                    CELL_SIZE - (int) (10 / RESIZE),
                    CELL_SIZE - (int) (10 / RESIZE));
            g.setColor(Color.white);
            g.fillOval((int) (455 / RESIZE), (int) (725 / RESIZE),
                    CELL_SIZE - (int) (10 / RESIZE),
                    CELL_SIZE - (int) (10 / RESIZE));

            // labels
            if (SHOW_LABELS) {
                String[] Label = {"A", "B", "C", "D", "E", "F", "G", "H"};
                g.setFont(new Font("Arial", Font.PLAIN, (int) (24 / RESIZE)));
                g.setColor(Color.black);
                IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                        g.drawString(Label[col], BASE_X + col * CELL_SIZE + (int) (30 / RESIZE),
                                BASE_Y - (int) (15 / RESIZE)));
                IntStream.range(0, Board.BOARD_SIZE).forEach((row -> g.drawString(String.valueOf(row + 1),
                        BASE_X - (int) (25 / RESIZE),
                        BASE_Y + row * CELL_SIZE + (int) (45 / RESIZE))));

                IntStream.range(0, Board.BOARD_SIZE).forEach(col -> g.drawString(Label[col],
                        BASE_X + col * CELL_SIZE + (int) (30 / RESIZE),
                        BASE_Y + CELL_SIZE*8 + (int) (30 / RESIZE)));
                IntStream.range(0, Board.BOARD_SIZE).forEach((row -> g.drawString(String.valueOf(row + 1),
                        BASE_X + CELL_SIZE*8 + (int) (10 / RESIZE),
                        BASE_Y + row * CELL_SIZE + (int) (45 / RESIZE))));

            }

            if (gameSnapshot.getStatus().isGameOver()) {
                switch (gameSnapshot.getStatus()) {
                    case PLAYER1_WIN:
                        winner = "WHITE is Winner";
                        break;
                    case PLAYER2_WIN:
                        winner = "BLACK is Winner";
                        break;
                    case DRAW:
                    default:
                        winner = "Draw";
                        break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g.setColor(Color.lightGray);
                g.fillRect(0, 0, WIDTH, HEIGHT);
                g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, (int) (48 / RESIZE)));
                g.drawString(winner, (int) (125 / RESIZE), (int) (300 / RESIZE));

                g.setColor(Color.BLACK);
                g.fillOval((int) (145 / RESIZE), (int) (360 / RESIZE),
                        CELL_SIZE - (int) (10 / RESIZE),
                        CELL_SIZE - (int) (10 / RESIZE));

                g.setColor(Color.white);
                g.fillOval((int) (400 / RESIZE), (int) (360 / RESIZE),
                        CELL_SIZE - (int) (10 / RESIZE),
                        CELL_SIZE - (int) (10 / RESIZE));

                g.setColor(Color.gray);
                g.setFont(new Font("Arial", Font.BOLD, (int) (36 / RESIZE)));
                g.drawString("" + gameSnapshot.getScore().getPlayer2Score(),
                        (int) (155 / RESIZE), (int) (400 / RESIZE));
                g.drawString("" + gameSnapshot.getScore().getPlayer1Score(),
                        (int) (410 / RESIZE), (int) (400 / RESIZE));
            }

        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (!started) {

            //reversi = new Reversi(this, this, PlayerFactory.createRoboPlayer1(new DesktopDecisionHandler()), PlayerFactory.createRoboPlayer2(new DesktopDecisionHandler()));

            //gameLogic = new GameLogicThread(this, PlayerFactory.createUserPlayer1(), PlayerFactory.createUserPlayer2(), this);
            gameLogic = new GameLogicThread(this, PlayerFactory.createHumanPlayer1(), PlayerFactory.createRoboPlayer2(), this);
            //gameLogic = new GameLogicThread(this, PlayerFactory.createRoboPlayer1(), PlayerFactory.createRoboPlayer2(), this);
            gameLogic.start();
            started = true;
            repaint();
        } else {
            int col = (e.getY() - BASE_Y) / CELL_SIZE;
            int row = (e.getX() - BASE_X) / CELL_SIZE;
            Coordinates coordinates = Coordinates.of(row, col);

            synchronized (gameLogic.acceptedMove) {
                gameLogic.acceptedMove.setCoordinates(coordinates);
                gameLogic.acceptedMove.notifyAll();
            }
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
