package it.fmt.games.reversi;

import it.fmt.games.reversi.model.GameSnapshot;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ReversiTest {

    @Test
    void checkGameSnapshot() {
        Reversi reversi = new Reversi(gameSnapshot -> {
            assertThat(gameSnapshot, notNullValue());
            assertThat(gameSnapshot.getStatus(), notNullValue());
            assertThat(gameSnapshot.getScore(), notNullValue());
            assertThat(gameSnapshot.getActivePiece(), notNullValue());
            assertThat(gameSnapshot.getAvailableMoves(), notNullValue());
            assertThat(gameSnapshot.getAvailableMoves().getMovesActivePlayer(), notNullValue());
            assertThat(gameSnapshot.getAvailableMoves().getMovesOtherPlayer(), notNullValue());
            assertThat(gameSnapshot.getBoard(), notNullValue());
            assertThat(gameSnapshot.getStatus(), notNullValue());
        });
        GameSnapshot result = reversi.play();

        assertThat(result.getStatus().isGameOver(), is(true));
    }

    @Test
    void playOnConsole() {
        Reversi reversi = new Reversi(new ConsoleRenderer());
        GameSnapshot result = reversi.play();

        assertThat(result.getStatus().isGameOver(), is(true));
    }
}