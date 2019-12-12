package it.fmt.games.reversi.model;

import it.fmt.games.reversi.DecisionHandler;
import it.fmt.games.reversi.model.Coordinates;

import java.util.List;

public class SimpleDecisionHandler implements DecisionHandler {

    @Override
    public Coordinates compute(List<Coordinates> availableMoves) {
        return availableMoves.get(0);
    }
}