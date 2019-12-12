package it.fmt.games.reversi.model;

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

    public AvailableMoves checkAvailableMoves(String fileName) throws Exception {
        Board board = BoardReader.read(fileName);

        AvailableMovesFinder movesPlayer1Finder = new AvailableMovesFinder(board, Piece.PLAYER_1);
        AvailableMovesFinder movesPlayer2Finder = new AvailableMovesFinder(board, Piece.PLAYER_2);

        List<Coordinates> availableMovesForPlayer1 = movesPlayer1Finder.findMoves();
        List<Coordinates> availableMovesForPlayer2 = movesPlayer2Finder.findMoves();

        return new AvailableMoves(availableMovesForPlayer1, availableMovesForPlayer2);
    }
}
