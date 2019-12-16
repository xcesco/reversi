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
    public static final int CELL_SIZE = 70;
    public static final int BASE_X = 35;
    public static final int BASE_Y = 105;
    public static final int OFFSET = 25;
    private static final boolean SHOW_LABELS = true;
    public static final Color darkGreen = new Color(0, 120, 0);
    public static final Color brown = new Color(153,102, 0);
    public static String winner = "";
    public GameSnapshot gameSnapshot;
    private boolean started = false;
    private Rectangle[][] boxes;


    private Reversi reversi;
    private GameLogicThread gameLogic;


    public static void main(String[] args) {
        JFrame win = new JFrame("Reversi");
        win.setSize(650, 900);
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
                        .forEach(col -> boxes[row][col] = new Rectangle(BASE_X + col * 70, BASE_Y + row * 70, 70, 70)));
    }

    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, 800, 900);
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font("Arial", Font.BOLD, 48));

        if (!started) {
            g.drawString("REVERSI", 250- OFFSET, 300);
            g.drawString("Click to Play", 215- OFFSET, 400);
        } else if (this.gameSnapshot != null) {
            // board

            g.setColor(brown);
            g.fillRect(BASE_X-10,BASE_Y-10,580,580);

            g.setColor(darkGreen);
            g.fillRect(BASE_X,BASE_Y,560,560);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("REVERSI", 240, 50);
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
                    g.fillOval(BASE_X + item.getCoordinates().getRow() * CELL_SIZE + 5, BASE_Y + item.getCoordinates().getColumn() * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                }

            });
            //Legal Moves
            this.gameSnapshot.getAvailableMoves().getMovesActivePlayer().forEach(item -> {
                g.setColor(Color.gray);
                g.fillOval(BASE_X + item.getRow() * CELL_SIZE + 30, BASE_Y + item.getColumn() * CELL_SIZE + 30, CELL_SIZE - 60, CELL_SIZE - 60);
            });

            //Score
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString(" : " + gameSnapshot.getScore().getPlayer2Score(), 100, 740+OFFSET);
            g.drawString(" : " + gameSnapshot.getScore().getPlayer1Score(), 520, 740+OFFSET);


            g.fillOval(35, 700+OFFSET, CELL_SIZE - 10, CELL_SIZE - 10);
            g.setColor(Color.white);
            g.fillOval(455, 700+OFFSET, CELL_SIZE - 10, CELL_SIZE - 10);

            // labels
            if (SHOW_LABELS) {
                String[] C = {"A", "B", "C", "D", "E", "F", "G", "H"};
                g.setFont(new Font("Arial", Font.PLAIN, 24));
                g.setColor(Color.black);
                IntStream.range(0, Board.BOARD_SIZE).forEach(col -> g.drawString(C[col], BASE_X + col * 70 + 30, BASE_Y - 15));
                IntStream.range(0, Board.BOARD_SIZE).forEach((row -> g.drawString(String.valueOf(row + 1), BASE_X - 25,  BASE_Y + row * 70+ 45)));
                IntStream.range(0, Board.BOARD_SIZE).forEach(col -> g.drawString(C[col], BASE_X + col * 70 + 30, BASE_Y+610 - 15));
                IntStream.range(0, Board.BOARD_SIZE).forEach((row -> g.drawString(String.valueOf(row + 1), BASE_X + 600 - 25,  BASE_Y + row * 70+ 45)));

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
                g.fillRect(0, 0, 800, 900);
                g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                g.drawString(winner, 150-OFFSET, 300);

                g.setColor(Color.BLACK);
                g.fillOval(145, 360, CELL_SIZE - 10, CELL_SIZE - 10);

                g.setColor(Color.white);
                g.fillOval(400, 360, CELL_SIZE - 10, CELL_SIZE - 10);

                g.setColor(Color.gray);
                g.setFont(new Font("Arial", Font.BOLD, 36));
                g.drawString("" + gameSnapshot.getScore().getPlayer2Score(), 155, 400);
                g.drawString("" + gameSnapshot.getScore().getPlayer1Score(), 410, 400);
            }

        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (!started) {

            //reversi = new Reversi(this, this, PlayerFactory.createRoboPlayer1(new DesktopDecisionHandler()), PlayerFactory.createRoboPlayer2(new DesktopDecisionHandler()));

            gameLogic = new GameLogicThread(this, PlayerFactory.createUserPlayer1(), PlayerFactory.createUserPlayer2(), this);
            //gameLogic = new GameLogicThread(this, PlayerFactory.createUserPlayer1(), PlayerFactory.createRoboPlayer2(), this);
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
