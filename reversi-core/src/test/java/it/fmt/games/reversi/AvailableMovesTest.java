package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static it.fmt.games.reversi.Coordinates.of;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AvailableMovesTest {

    @Test
    public void noMovesForAllPlayers() throws Exception {
        AvailableMoves availableMoves = checkAvailableMoves("available_moves00");
        assertThat(availableMoves.isAvailableMovesCurrentPlayer(), is(false));
        assertThat(availableMoves.availableMovesForPlayers(), is(false));
        assertEquals(availableMoves.getMovesCurrentPlayer().size(), availableMoves.getMovesOtherPlayer().size());
    }

    @Test
    public void noMovesForOtherPlayer() throws Exception {
        AvailableMoves availableMoves = checkAvailableMoves("available_moves01");
        assertThat(availableMoves.isAvailableMovesCurrentPlayer(), is(true));
        assertThat(availableMoves.availableMovesForPlayers(), is(true));
        assertThat(availableMoves.getMovesOtherPlayer().size(), is(0));
    }

    @Test
    public void noMovesForCurrentPlayer() throws Exception {
        AvailableMoves availableMoves = checkAvailableMoves("available_moves02");
        assertThat(availableMoves.isAvailableMovesCurrentPlayer(), is(false));
        assertThat(availableMoves.availableMovesForPlayers(), is(true));
        assertThat(availableMoves.getMovesCurrentPlayer().size(), is(0));
    }

    @Test
    public void availableMovesForAllPlayers() throws Exception {
        AvailableMoves availableMoves = checkAvailableMoves("available_moves03");
        assertThat(availableMoves.isAvailableMovesCurrentPlayer(), is(true));
        assertThat(availableMoves.availableMovesForPlayers(), is(true));
        assertThat(availableMoves.getMovesCurrentPlayer().size(), is(4));
        assertThat(availableMoves.getMovesOtherPlayer().size(), is(4));
    }

    public AvailableMoves checkAvailableMoves(String fileName) throws Exception {
        Board board = BoardReader.read(fileName);

        AvailableMovesFinder movesPlayer1Finder = new AvailableMovesFinder(Piece.PLAYER_1, board);
        AvailableMovesFinder movesPlayer2Finder = new AvailableMovesFinder(Piece.PLAYER_2, board);

        List<Coordinates> availableMovesForPlayer1 = movesPlayer1Finder.findMoves();
        List<Coordinates> availableMovesForPlayer2 = movesPlayer2Finder.findMoves();

        return new AvailableMoves(availableMovesForPlayer1, availableMovesForPlayer2);
    }
}
