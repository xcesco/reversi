package it.fmt.games.reversi.model;

import java.util.stream.IntStream;

public class BoardDrawer {
    private Board board;
    private final String PREFIX = "\t";
    static final String ROW_SEPARATOR = "  +-----+-----+-----+-----+-----+-----+-----+-----+";

    public BoardDrawer(Board board) {
        this.board = board;
    }

    public void drawBoard() {
        System.out.print(PREFIX +"  ");
        IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                System.out.print(String.format("   %s  ", (char) ('A' + col))));
        System.out.println("\n" + PREFIX + ROW_SEPARATOR);
        IntStream.range(0, Board.BOARD_SIZE).forEach(row -> {
            System.out.print(String.format(PREFIX + "%s |", row + 1));
            IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                    System.out.print(String.format("  %s  |", getSymbol(Coordinates.of(row, col)))));
            System.out.println("\n" + PREFIX + ROW_SEPARATOR);
        });
    }

    public String getSymbol(Coordinates coords) {
        return board.getCellContent(coords) == Piece.EMPTY ? " "
                : board.getCellContent(coords) == Piece.PLAYER_1 ? "O" : "X";
    }
}
