package it.fmt.games.reversi.model.cpu;

import it.fmt.games.reversi.DecisionHandler;
import it.fmt.games.reversi.model.Coordinates;

import java.security.SecureRandom;
import java.util.List;

public class RandomDecisionHandler implements DecisionHandler {

    @Override
    public Coordinates compute(List<Coordinates> availableMoves) {
        if (availableMoves.size() == 1) return availableMoves.get(0);
        return availableMoves.get(new SecureRandom().nextInt(availableMoves.size() - 1));
    }
}