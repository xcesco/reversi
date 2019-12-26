package it.fmt.games.reversi;

import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Player;

import java.util.List;

import static it.fmt.games.reversi.model.Coordinates.of;

public class DummyBadUserInputReader implements UserInputReader {
    private int counter = 0;

    @Override
    public Coordinates readInputFor(Player currentPlayer, List<Coordinates> availableMoves) {
        if (counter++ % 2 == 0) return of(-1, -1);
        return availableMoves.get(0);
    }
}
