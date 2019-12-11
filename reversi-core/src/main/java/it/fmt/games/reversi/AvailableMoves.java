package it.fmt.games.reversi;

import java.util.List;

public class AvailableMoves {
    private final List<Coordinates> movesCurrentPlayer;
    private final List<Coordinates> movesOtherPlayer;

    public AvailableMoves(List<Coordinates> availableMovesCurrentPlayer, List<Coordinates> availableMovesOtherPlayer) {
        this.movesCurrentPlayer = availableMovesCurrentPlayer;
        this.movesOtherPlayer = availableMovesOtherPlayer;
    }

    public boolean isAnyAvailableMoves() {
        return isAvailableMovesCurrentPlayer() || !(movesOtherPlayer.isEmpty());
    }

    public List<Coordinates> getMovesCurrentPlayer() {
        return movesCurrentPlayer;
    }

    public List<Coordinates> getMovesOtherPlayer() {
        return movesOtherPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailableMoves that = (AvailableMoves) o;

        if (!movesCurrentPlayer.equals(that.movesCurrentPlayer)) return false;
        return movesOtherPlayer.equals(that.movesOtherPlayer);
    }

    @Override
    public int hashCode() {
        int result = movesCurrentPlayer.hashCode();
        result = 31 * result + movesOtherPlayer.hashCode();
        return result;
    }

    public boolean isAvailableMovesCurrentPlayer() {
        return !(movesCurrentPlayer.isEmpty());
    }

}
