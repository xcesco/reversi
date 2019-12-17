package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Piece;

import java.util.List;
import java.util.stream.IntStream;

public class BoardDrawer extends Drawer{
    private Board board;
    private List<Coordinates> availableMoves;

    public BoardDrawer(Board board, List<Coordinates> availableMoves) {
        this.board = board;
        this.availableMoves = availableMoves;
    }

    public void drawBoard() {
        draw("  ");
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

    private String getSymbol(Coordinates coordinates) {
        return board.getCellContent(coordinates) == Piece.EMPTY ?
                getMovesOnBoard(coordinates) : getPlayerSymbol(coordinates);
    }

    public String getMovesOnBoard(Coordinates coordinates) {
        return isAvailableMove(coordinates) ? "?" : " ";
    }


    public boolean isAvailableMove(Coordinates coordinates) {
        return availableMoves.indexOf(coordinates)!=-1;
    }

    private String getPlayerSymbol(Coordinates coordinates) {
        return board.getCellContent(coordinates) == Piece.PLAYER_1 ? "O" : "X";
    }
}
