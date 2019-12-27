package it.fmt.games.reversi.model;

import java.util.List;

public interface GameLogic {
    AvailableMoves initialize();

    AvailableMoves findMovesForPlayers();

    void insertSelectedMove(Coordinates moveCoords);

    void switchPlayers();

    GameSnapshot getGameSnapshot();

    Coordinates readActivePlayerMove(List<Coordinates> availableMoves);
}
