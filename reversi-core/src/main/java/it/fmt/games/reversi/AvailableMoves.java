package it.fmt.games.reversi;

import java.util.List;

public class AvailableMoves {
    private final List<Coordinates> movesCurrentPlayer;
    private final List<Coordinates> movesOtherPlayer;

    public AvailableMoves(List<Coordinates> availableMovesCurrentPlayer, List<Coordinates> availableMovesOtherPlayer) {
        this.movesCurrentPlayer = availableMovesCurrentPlayer;
        this.movesOtherPlayer = availableMovesOtherPlayer;
    }

    public boolean availableMovesForPlayers() {
        return isAvailableMovesCurrentPlayer() || movesOtherPlayer.size() > 0;
    }

    public List<Coordinates> getMovesCurrentPlayer() {
        return movesCurrentPlayer;
    }

    public List<Coordinates> getMovesOtherPlayer() {
        return movesOtherPlayer;
    }

    public boolean isAvailableMovesCurrentPlayer() {
        return movesCurrentPlayer.size() > 0;
    }
}
