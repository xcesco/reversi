package it.fmt.games.reversi.model;

import it.fmt.games.reversi.DecisionHandler;
import it.fmt.games.reversi.model.Coordinates;

import java.util.List;
import java.util.Random;

public class RandomDecisionHandler implements DecisionHandler {

    @Override
    public Coordinates compute(List<Coordinates> availableMoves) {
        if (availableMoves.size() == 1) return availableMoves.get(0);
        return availableMoves.get(new Random().nextInt(availableMoves.size() - 1));
    }
}