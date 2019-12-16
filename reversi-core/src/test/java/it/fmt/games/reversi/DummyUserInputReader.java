package it.fmt.games.reversi;

import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Player;

import java.util.List;

public class DummyUserInputReader implements UserInputReader {
    @Override
    public Coordinates readInputFor(Player currentPlayer, List<Coordinates> availableMoves) {
        return availableMoves.get(0);
    }
}
