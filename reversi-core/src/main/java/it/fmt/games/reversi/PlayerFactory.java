package it.fmt.games.reversi;

public abstract class PlayerFactory {
    private PlayerFactory() {

    }
    public static Player2 createUserPlayer2() {
        return new Player2();
    }

    public static Player1 createUserPlayer1() {
        return new Player1();
    }

    public static Player2 createRoboPlayer2() {
        return createRoboPlayer2(DecisionHandlerType.SIMPLE);
    }

    public static Player2 createRoboPlayer2(DecisionHandlerType type) {
        return new Player2(type.createHandler());
    }

    public static Player2 createRoboPlayer2(DecisionHandler handler) {
        return new Player2(handler);
    }

    public static Player1 createRoboPlayer1() {
        return createRoboPlayer1(DecisionHandlerType.SIMPLE);
    }

    public static Player1 createRoboPlayer1(DecisionHandlerType type) {
        return new Player1(type.createHandler());
    }

    public static Player1 createRoboPlayer1(DecisionHandler handler) {
        return new Player1(handler);
    }
}
