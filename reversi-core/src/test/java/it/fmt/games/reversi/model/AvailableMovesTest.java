package it.fmt.games.reversi.model;

import it.fmt.games.reversi.model.operators.AvailableMovesFinder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvailableMovesTest {

    @Test
    public void noMovesForAllPlayers() throws Exception {
        AvailableMoves availableMoves = checkAvailableMoves("available_moves00");
        assertThat(availableMoves.isAvailableMovesForActivePlayer(), is(false));
        assertThat(availableMoves.isAnyAvailableMoves(), is(false));
        assertEquals(availableMoves.getMovesActivePlayer().size(), availableMoves.getMovesOtherPlayer().size());
    }

    @Test
    public void noMovesForOtherPlayer() throws Exception {
        AvailableMoves availableMoves = checkAvailableMoves("available_moves01");
        assertThat(availableMoves.isAvailableMovesForActivePlayer(), is(true));
        assertThat(availableMoves.isAnyAvailableMoves(), is(true));
        assertThat(availableMoves.getMovesOtherPlayer().size(), is(0));
    }

    @Test
    public void noMovesForCurrentPlayer() throws Exception {
        AvailableMoves availableMoves = checkAvailableMoves("available_moves02");
        assertThat(availableMoves.isAvailableMovesForActivePlayer(), is(false));
        assertThat(availableMoves.isAnyAvailableMoves(), is(true));
        assertThat(availableMoves.getMovesActivePlayer().size(), is(0));
    }

    @Test
    public void availableMovesForAllPlayers() throws Exception {
        AvailableMoves availableMoves = checkAvailableMoves("available_moves03");
        assertThat(availableMoves.isAvailableMovesForActivePlayer(), is(true));
        assertThat(availableMoves.isAnyAvailableMoves(), is(true));
        assertThat(availableMoves.getMovesActivePlayer().size(), is(4));
        assertThat(availableMoves.getMovesOtherPlayer().size(), is(4));
    }

    @Test
    public void testEquals() throws Exception {
        AvailableMoves availableMoves1 = checkAvailableMoves("available_moves03");
        AvailableMoves availableMoves2 = checkAvailableMoves("available_moves03");

        assertThat(availableMoves1.equals(null), is(false));
        assertThat(availableMoves1.equals("dummy"), is(false));
        assertThat(availableMoves1.equals(availableMoves2), is(true));
        assertThat(availableMoves1.hashCode() == availableMoves2.hashCode(), is(true));
    }

    @Test
    public void testHashCode() {
        Coordinates coords1 = Coordinates.of("A1");
        Coordinates coords2 = Coordinates.of("a1");

        assertThat(coords1.hashCode(), equalTo(coords2.hashCode()));
    }

    private AvailableMoves checkAvailableMoves(String fileName) throws Exception {
        Board board = BoardReader.read(fileName);

        List<Coordinates> availableMovesForPlayer1 = AvailableMovesFinder.findMoves(board, Piece.PLAYER_1);
        List<Coordinates> availableMovesForPlayer2 = AvailableMovesFinder.findMoves(board, Piece.PLAYER_2);

        return new AvailableMoves(availableMovesForPlayer1, availableMovesForPlayer2);
    }

}
