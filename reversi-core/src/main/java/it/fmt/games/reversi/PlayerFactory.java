package it.fmt.games.reversi;

public abstract class PlayerFactory {
    private PlayerFactory() {

    }
    public static Player2 createHumanPlayer2() {
        return new Player2();
    }

    public static Player1 createHumanPlayer1() {
        return new Player1();
    }

    public static Player2 createCpuPlayer2() {
        return createCpuPlayer2(DecisionHandlerType.SIMPLE);
    }

    public static Player2 createCpuPlayer2(DecisionHandlerType type) {
        return new Player2(type.getDecisionHandler());
    }

    public static Player2 createCpuPlayer2(DecisionHandler handler) {
        return new Player2(handler);
    }

    public static Player1 createCpuPlayer1() {
        return createCpuPlayer1(DecisionHandlerType.SIMPLE);
    }

    public static Player1 createCpuPlayer1(DecisionHandlerType type) {
        return new Player1(type.getDecisionHandler());
    }

    public static Player1 createCpuPlayer1(DecisionHandler handler) {
        return new Player1(handler);
    }
}
