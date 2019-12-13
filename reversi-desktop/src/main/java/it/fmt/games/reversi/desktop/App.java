package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.stream.IntStream;

import static it.fmt.games.reversi.model.Coordinates.*;

public class App extends Canvas implements MouseListener {
    private boolean started = false;
    private Rectangle[][] boxes;
    private boolean showLabels = true;
    private Board board;

    public App(Board b) {
        this.board = b;
    }

    public static void main(String[] args) {
        JFrame win = new JFrame("Reversi");
        win.setSize(700, 800);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setResizable(false);
        win.add(new App());
        win.setVisible(true);
    }

    public App() {
        addMouseListener(this);
        boxes = new Rectangle[8][8];

        for (int r = 0; r < boxes.length; r++) {
            for (int c = 0; c < boxes[0].length; c++) {
                boxes[r][c] = new Rectangle(30 + c * 70, 100 + r * 70, 70, 70);
            }
        }

    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0, 0, 800, 900);
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font("Arial", Font.BOLD, 48));

        if (!started) {
            g.drawString("REVERSI", 305, 300);
            g.drawString("Click to Play", 275, 400);
        } else {
            // board
            for (int r = 0; r < boxes.length; r++) {
                for (int c = 0; c < boxes[0].length; c++) {
                    g2.draw(boxes[r][c]);
                }
            }


            // pieces
//            IntStream.range(0, Board.BOARD_SIZE).forEach(row -> {
//                        IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
//                        {
//                            if (board.getCellContent(of(row, col)) != Piece.EMPTY) {
//                                if ((board.getCellContent(of(row, col)) == Piece.PLAYER_1)) {
//                                    g.setColor(Color.white);
//                                } else {
//                                    g.setColor(Color.black);
//                                }
//                                Rectangle box = boxes[row][col];
//                                g.fillOval(box.x + 5, box.y + 5, box.width - 10, box.height - 10);
//                            }
//                        });
//                    });


                // labels
                if (showLabels) {
                    String[] C = {"A", "B", "C", "D", "E", "F", "G", "H"};
                    g.setFont(new Font("Arial", Font.PLAIN, 24));
                    g.setColor(Color.black);
                    for (int c = 0; c < boxes.length; c++) {
                        g.drawString(C[c], boxes[0][c].x + 30, boxes[0][c].y - 5);
                    }
                    for (int r = 0; r < boxes[0].length; r++) {
                        g.drawString(String.valueOf(r + 1), boxes[r][0].x - 20, boxes[r][0].y + 45);
                    }
                }

            }
        }


        @Override
        public void mouseClicked (MouseEvent e){

        }

        @Override
        public void mousePressed (MouseEvent e){

        }

        @Override
        public void mouseReleased (MouseEvent e){
            int x = e.getX();
            int y = e.getY();


            if (!started) {
                started = true;
                repaint();
                return;
            }

        }

        @Override
        public void mouseEntered (MouseEvent e){

        }

        @Override
        public void mouseExited (MouseEvent e){

        }
    }



