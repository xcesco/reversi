package it.fmt.games.reversi.model;

import it.fmt.games.reversi.UserInputReader;

import java.util.List;

public class DummyUserInputReader implements UserInputReader {
    @Override
    public Coordinates readInputFor(Player currentPlayer, List<Coordinates> availableMoves) {
        return availableMoves.get(0);
    }
}
