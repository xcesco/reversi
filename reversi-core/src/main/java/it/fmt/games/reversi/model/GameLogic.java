package it.fmt.games.reversi.model;

import java.util.List;

public interface GameLogic {
    AvailableMoves initialize();

    AvailableMoves findMovesForPlayers();

    void insertSelectedMove(Coordinates moveCoords);

    void switchPlayer();

    GameSnapshot getGameSnapshot();

    Coordinates readActivePlayeMove(List<Coordinates> availableMoves);
}
