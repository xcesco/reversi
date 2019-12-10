package it.fmt.games.reversi;

import java.util.List;
import java.util.stream.IntStream;

public class BoardDrawer {
    private Board board;
    private List<Coordinates> playerMoves;
    private final String prefix = "\t";
    static final String ROW_SEPARATOR = "  +-----+-----+-----+-----+-----+-----+-----+-----+";

    public BoardDrawer(Board board) {
        this.board = board;
        AvailableMovesFinder moves = new AvailableMovesFinder(this.board, Piece.PLAYER_1);
        this.playerMoves = moves.findMoves();
    }

    public void drawBoard() {

        System.out.print(prefix+"  ");
        IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                        System.out.print(String.format("   %s  ", (char) ('A' + col))));
        System.out.println("\n" + prefix + ROW_SEPARATOR);
        IntStream.range(0, Board.BOARD_SIZE).forEach(row ->
                {
                    System.out.print(String.format(prefix + "%s |", row + 1));
                    IntStream.range(0, Board.BOARD_SIZE).forEach(col -> {
                        System.out.print(String.format("  %s  |", drawAvailableMoves(Coordinates.of(row, col))));
                        }
                    );
                    System.out.println("\n" + prefix + ROW_SEPARATOR);
                }
        );
    }

    public String drawAvailableMoves(Coordinates coords) {
        return board.getCellContent(coords).ordinal() == 0 ? (isAvailableMove(coords) ? "*" : " ")
                 : board.getCellContent(coords).ordinal() == 1 ? "O" : "X";
    }

    public boolean isAvailableMove(Coordinates coordinates) {
        for (Coordinates coord : playerMoves) {
            if (coord.equals(coordinates)) {
                return true;
            }
        }
        return false;
    }
}
