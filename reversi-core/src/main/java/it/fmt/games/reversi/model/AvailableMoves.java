package it.fmt.games.reversi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AvailableMoves {
    private final List<Coordinates> movesCurrentPlayer;
    private final List<Coordinates> movesOtherPlayer;

    @JsonCreator
    public AvailableMoves(@JsonProperty("availableMovesCurrentPlayer") List<Coordinates> availableMovesCurrentPlayer,
                          @JsonProperty("availableMovesOtherPlayer") List<Coordinates> availableMovesOtherPlayer) {
        this.movesCurrentPlayer = availableMovesCurrentPlayer;
        this.movesOtherPlayer = availableMovesOtherPlayer;
    }

    public boolean isAnyAvailableMoves() {
        return isAvailableMovesForActivePlayer() || !(movesOtherPlayer.isEmpty());
    }

    public List<Coordinates> getMovesActivePlayer() {
        return movesCurrentPlayer;
    }

    public List<Coordinates> getMovesOtherPlayer() {
        return movesOtherPlayer;
    }

    public boolean isAvailableMovesForActivePlayer() {
        return !(movesCurrentPlayer.isEmpty());
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
}
