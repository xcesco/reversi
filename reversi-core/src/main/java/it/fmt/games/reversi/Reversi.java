package it.fmt.games.reversi;

import it.fmt.games.reversi.model.*;

public class Reversi {
    private final GameRenderer renderer;

    public Reversi(GameRenderer renderer) {
        this.renderer = renderer;
    }

    public GameSnapshot play() {
        GameLogic gameLogic = new GameLogic();

        AvailableMoves availableMoves = gameLogic.initialize();
        while (availableMoves.isAnyAvailableMoves()) {
            renderer.render(gameLogic.getGameSnapshot());

            if (availableMoves.isAvailableMovesForActivePlayer()) {
                Coordinates nextMove = availableMoves.getMovesActivePlayer().get(0);
                gameLogic.insertSelectedMove(nextMove);
            }

            gameLogic.switchPlayer();
            availableMoves = gameLogic.findMovesForPlayers();
        }

        renderer.render(gameLogic.getGameSnapshot());
        return gameLogic.getGameSnapshot();
    }
}
