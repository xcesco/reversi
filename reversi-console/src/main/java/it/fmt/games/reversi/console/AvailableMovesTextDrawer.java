package it.fmt.games.reversi.console;

import it.fmt.games.reversi.model.Coordinates;

import java.util.List;

public abstract class AvailableMovesTextDrawer extends TextDrawer {

    private AvailableMovesTextDrawer() {

    }

    public static void drawAvailableMoves(List<Coordinates> availableMoves) {
        if (availableMoves.size() > 0) {
            print(PREFIX+"- Available moves (?):");
            availableMoves.forEach(item -> System.out.print(" " + item));
            println("");
        } else {
            println(PREFIX+"- No available moves! (Switch turn)");
        }
    }
}
