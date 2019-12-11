package it.fmt.games.reversi;

import java.util.List;
import java.util.stream.IntStream;

public class BoardDrawer {
    private Board board;
    private Piece player = Piece.PLAYER_1;
    private List<Coordinates> playerMoves;
    static final String PREFIX = "\t";
    static final String ROW_SEPARATOR = "  +-----+-----+-----+-----+-----+-----+-----+-----+";

    public BoardDrawer(Board board) {
        this.board = board;
        AvailableMovesFinder moves = new AvailableMovesFinder(this.board, player);
        this.playerMoves = moves.findMoves();
    }

    public void drawBoard() {

        System.out.print(PREFIX +"  ");
        IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                        System.out.print(String.format("   %s  ", (char) ('A' + col))));
        System.out.println("\n" + PREFIX + ROW_SEPARATOR);
        IntStream.range(0, Board.BOARD_SIZE).forEach(row -> {
                System.out.print(String.format(PREFIX + "%s |", row + 1));
                IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                        System.out.print(String.format("  %s  |", drawAvailableMovesOnBoard(Coordinates.of(row, col)))));
                System.out.println("\n" + PREFIX + ROW_SEPARATOR);
        });
    }

    public String drawAvailableMovesOnBoard(Coordinates coords) {
        return board.getCellContent(coords) == Piece.EMPTY ? (isAvailableMove(coords) ? "?" : " ")
                 : board.getCellContent(coords) == Piece.PLAYER_1 ? "O" : "X";
    }

    public boolean isAvailableMove(Coordinates coordinates) {
        return playerMoves.indexOf(coordinates)!=-1;
    }

    public void drawAvailableMove() {
        System.out.print("\n" + PREFIX + "Available moves for player" + (player == Piece.PLAYER_1 ? "1 (pieces O)" : "2 (pieces X)") + ":\n" + PREFIX);
        playerMoves.forEach(moves -> System.out.print(String.format(moves + " ")));
    }
}
