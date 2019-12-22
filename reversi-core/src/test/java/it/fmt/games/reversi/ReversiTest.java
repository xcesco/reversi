package it.fmt.games.reversi;

import it.fmt.games.reversi.model.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReversiTest {
    @Test
    public void checkGameSnapshot() {
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
        }, new DummyUserInputReader(), PlayerFactory.createRoboPlayer1(), PlayerFactory.createRoboPlayer2());
        GameSnapshot result = reversi.play();

        assertThat(result.getStatus().isGameOver(), is(true));
    }

    @Test
    public void playOnConsoleCpuVsCpu() throws Exception {
        Reversi reversi = new Reversi(new DummyRenderer(), new DummyUserInputReader(), PlayerFactory.createRoboPlayer1(),
                PlayerFactory.createRoboPlayer2());
        GameSnapshot result = reversi.play();
        checkEndGame(result);
    }

    @Test
    public void playOnConsoleP1VsCpu() throws Exception {
        Reversi reversi = new Reversi(new DummyRenderer(), new DummyUserInputReader(), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createRoboPlayer2());
        GameSnapshot result = reversi.play();

        checkEndGame(result);
    }

    private void checkEndGame(GameSnapshot result) throws Exception {
        Board board = BoardReader.read("reversi_final_state00");
        assertThat(result.getBoard().equals(board), is(true));
        assertThat(result.getStatus().isGameOver(), is(true));
        assertThat(result.getStatus(), is(GameStatus.PLAYER2_WIN));
        assertThat(result.getScore().getPlayer1Score(), is(19));
        assertThat(result.getScore().getPlayer2Score(), is(45));
    }
}