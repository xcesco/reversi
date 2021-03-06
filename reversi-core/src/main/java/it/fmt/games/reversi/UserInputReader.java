package it.fmt.games.reversi;

import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.Player;

import java.util.List;

public interface UserInputReader {
    Coordinates readInputFor(Player currentPlayer, List<Coordinates> availableMoves);
}
