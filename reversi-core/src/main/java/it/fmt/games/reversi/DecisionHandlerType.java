package it.fmt.games.reversi;

import it.fmt.games.reversi.exceptions.InvalidDecisionHandlerException;
import it.fmt.games.reversi.model.RandomDecisionHandler;
import it.fmt.games.reversi.model.SimpleDecisionHandler;

import java.lang.reflect.InvocationTargetException;

public enum DecisionHandlerType {
    SIMPLE(SimpleDecisionHandler.class),
    RANDOM(RandomDecisionHandler.class);

    private final Class<? extends DecisionHandler> clazz;

    DecisionHandlerType(Class<? extends DecisionHandler> clazz) {
        this.clazz = clazz;
    }

    public DecisionHandler createHandler() {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new InvalidDecisionHandlerException(e.getMessage());
        }
    }

}
