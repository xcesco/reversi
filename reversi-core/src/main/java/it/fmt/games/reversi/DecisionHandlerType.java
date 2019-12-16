package it.fmt.games.reversi;

import it.fmt.games.reversi.model.cpu.RandomDecisionHandler;
import it.fmt.games.reversi.model.cpu.SimpleDecisionHandler;

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
