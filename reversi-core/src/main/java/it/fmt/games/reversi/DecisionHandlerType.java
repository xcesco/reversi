package it.fmt.games.reversi;

import it.fmt.games.reversi.model.RandomDecisionHandler;
import it.fmt.games.reversi.model.SimpleDecisionHandler;

public enum DecisionHandlerType {
    SIMPLE(new SimpleDecisionHandler()),
    RANDOM(new RandomDecisionHandler());

    private final DecisionHandler decisionHandler;

    DecisionHandlerType(DecisionHandler decisionHandler) {
        this.decisionHandler = decisionHandler;
    }

    public DecisionHandler createHandler() {
        return decisionHandler;
    }

}
