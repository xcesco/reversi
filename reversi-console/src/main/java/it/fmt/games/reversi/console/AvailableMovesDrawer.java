package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Piece;

import java.util.List;
import java.util.stream.IntStream;

public class AvailableMovesDrawer extends Drawer{
    private List<Coordinates> availableMoves;

    public AvailableMovesDrawer(List<Coordinates> availableMoves) {
        this.availableMoves = availableMoves;
    }

    public void drawAvailableMoves() {
        draw("Available moves:");
        availableMoves.forEach(item -> System.out.print(" " + item));
        draw(NEW_LINE);
    }
}
