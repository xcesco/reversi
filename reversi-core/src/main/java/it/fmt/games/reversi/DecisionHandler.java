package it.fmt.games.reversi;

import it.fmt.games.reversi.model.Coordinates;

import java.util.List;

public interface DecisionHandler {
    Coordinates compute(List<Coordinates> availableMoves);
}
