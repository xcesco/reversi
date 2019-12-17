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
        AvailableMoves availableMoves = readAndCheck("available_moves00", false, false);
        assertEquals(availableMoves.getMovesActivePlayer().size(), availableMoves.getMovesOtherPlayer().size());
    }

    @Test
    public void noMovesForOtherPlayer() throws Exception {
        AvailableMoves availableMoves = readAndCheck("available_moves01", true, true);
        assertThat(availableMoves.getMovesOtherPlayer().size(), is(0));
    }

    @Test
    public void noMovesForCurrentPlayer() throws Exception {
        AvailableMoves availableMoves = readAndCheck("available_moves02", true, false);
        assertThat(availableMoves.getMovesActivePlayer().size(), is(0));
    }

    @Test
    public void availableMovesForAllPlayers() throws Exception {
        AvailableMoves availableMoves = readAndCheck("available_moves03", true, true);
        assertThat(availableMoves.getMovesActivePlayer().size(), is(4));
        assertThat(availableMoves.getMovesOtherPlayer().size(), is(4));
    }

    @Test
    public void testEquals() throws Exception {
        AvailableMoves availableMoves1 = readAvailableMoves("available_moves03");
        AvailableMoves availableMoves2 = readAvailableMoves("available_moves03");
        AvailableMoves availableMoves3 = readAvailableMoves("available_moves02");

        assertThat(availableMoves1.equals(availableMoves1), is(true));
        assertThat(availableMoves1.equals(null), is(false));
        assertThat(availableMoves1.equals("dummy"), is(false));
        assertThat(availableMoves1.equals(availableMoves2), is(true));
        assertThat(availableMoves1.equals(availableMoves3), is(false));
        assertThat(availableMoves1.hashCode() == availableMoves2.hashCode(), is(true));
    }

    @Test
    public void testHashCode() {
        Coordinates coords1 = Coordinates.of("A1");
        Coordinates coords2 = Coordinates.of("a1");

        assertThat(coords1.hashCode(), equalTo(coords2.hashCode()));
    }

    private AvailableMoves readAvailableMoves(String fileName) throws Exception {
        Board board = BoardReader.read(fileName);

        List<Coordinates> availableMovesForPlayer1 = AvailableMovesFinder.findMoves(board, Piece.PLAYER_1);
        List<Coordinates> availableMovesForPlayer2 = AvailableMovesFinder.findMoves(board, Piece.PLAYER_2);

        return new AvailableMoves(availableMovesForPlayer1, availableMovesForPlayer2);
    }

    private AvailableMoves readAndCheck(String available_moves01, boolean isAvailableMoves, boolean isAvailableMovesForActivePlayer) throws Exception {
        AvailableMoves availableMoves = readAvailableMoves(available_moves01);
        assertThat(availableMoves.isAnyAvailableMoves(), is(isAvailableMoves));
        assertThat(availableMoves.isAvailableMovesForActivePlayer(), is(isAvailableMovesForActivePlayer));
        return availableMoves;
    }

}
