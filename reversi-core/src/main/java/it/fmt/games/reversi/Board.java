package it.fmt.games.reversi;

public class Board {
    private static final int BOARD_SIZE = 8;
    private Cell[][] board;

    public void setCell(Coordinates c, Piece p) {
        board[c.getRow()][c.getColumn()] = new Cell(c, p);
    }
    public Cell getCell(Coordinates c){
        return board[c.getRow()][c.getColumn()];
    }

    public Board() {
        board = new Cell[BOARD_SIZE][];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = new Cell[BOARD_SIZE];
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = new Cell(new Coordinates(i, j), Piece.EMPTY);
        }
    }

}


