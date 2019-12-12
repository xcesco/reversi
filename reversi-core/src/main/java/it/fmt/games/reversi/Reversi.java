package it.fmt.games.reversi;

import it.fmt.games.reversi.model.*;

public class Reversi {
    private final GameRenderer renderer;
    private final UserInputReader userInputReader;
    private final Player player1;
    private final Player player2;

    public Reversi(GameRenderer renderer, UserInputReader userInputReader, Player1 player1, Player2 player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.renderer = renderer;
        this.userInputReader = userInputReader;
    }

    public GameSnapshot play() {
        GameLogic gameLogic = new GameLogic(player1, player2, userInputReader);

        AvailableMoves availableMoves = gameLogic.initialize();
        while (availableMoves.isAnyAvailableMoves()) {
            renderer.render(gameLogic.getGameSnapshot());

            if (availableMoves.isAvailableMovesForActivePlayer()) {
                Coordinates nextMove = gameLogic.readActivePlayeMove(availableMoves.getMovesActivePlayer());
                gameLogic.insertSelectedMove(nextMove);
            }

            gameLogic.switchPlayer();
            availableMoves = gameLogic.findMovesForPlayers();
        }

        renderer.render(gameLogic.getGameSnapshot());
        return gameLogic.getGameSnapshot();
    }
}
