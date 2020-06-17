package it.fmt.games.reversi;

import it.fmt.games.reversi.model.*;

public class Reversi {
    private final GameRenderer renderer;
    private final GameLogic gameLogic;

    public Reversi(GameRenderer renderer, UserInputReader userInputReader, Player1 player1, Player2 player2) {
        this.gameLogic = new GameLogicImpl(player1, player2, userInputReader);
        this.renderer = renderer;
    }

    public Reversi(GameRenderer renderer, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.renderer = renderer;
    }

    public GameSnapshot play() {
        AvailableMoves availableMoves = gameLogic.initialize();
        while (availableMoves.isAnyAvailableMoves()) {
            renderer.render(gameLogic.getGameSnapshot());

            if (availableMoves.isAvailableMovesForActivePlayer()) {
                Coordinates nextMove = gameLogic.readActivePlayerMove(availableMoves.getMovesActivePlayer());
                gameLogic.insertSelectedMove(nextMove);
            }

            gameLogic.switchPlayers();
            availableMoves = gameLogic.findMovesForPlayers();
        }

        renderer.render(gameLogic.getGameSnapshot());
        return gameLogic.getGameSnapshot();
    }
}
