package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.Coordinates;

import java.util.List;

public abstract class AvailableMovesTextDrawer extends TextDrawer {

    private AvailableMovesTextDrawer() {

    }

    public static void drawAvailableMoves(List<Coordinates> availableMoves) {
        print("- Available moves (•):");
        availableMoves.forEach(item -> System.out.print(" " + item));
        print(NEW_LINE);
    }
}
