package it.fmt.games.reversi;

public class Reversi {
    private final GameRenderer renderer;

    public Reversi(GameRenderer renderer) {
        this.renderer = renderer;
    }

    public GameSnapshot play() {
        GameLogic gameLogic = new GameLogic();
        gameLogic.initialize();

        AvailableMoves availableMoves = gameLogic.findMovesForPlayers();
        while (availableMoves.isAnyAvailableMoves()) {
            renderer.render(gameLogic.getGameSnapshot());

            if (availableMoves.isAvailableMovesCurrentPlayer()) {
                Coordinates nextMove = availableMoves.getMovesCurrentPlayer().get(0);
                gameLogic.insertSelectedMove(nextMove);
            }

            gameLogic.switchPlayer();
            availableMoves = gameLogic.findMovesForPlayers();
        }

        renderer.render(gameLogic.getGameSnapshot());
        return gameLogic.getGameSnapshot();
    }
}
