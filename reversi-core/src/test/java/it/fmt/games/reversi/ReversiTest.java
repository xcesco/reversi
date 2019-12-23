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
        checkEndGame(result, "reversi_final_state00", GameStatus.PLAYER2_WIN, 19, 45);
    }

    @Test
    public void playOnConsoleP1VsCpu() throws Exception {
        Reversi reversi = new Reversi(new DummyRenderer(), new DummyUserInputReader(), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createRoboPlayer2());
        GameSnapshot result = reversi.play();

        checkEndGame(result, "reversi_final_state00", GameStatus.PLAYER2_WIN, 19, 45);
    }

    @Test
    public void draw() throws Exception {
        GameLogic gameLogic = new DummyGameLogic(BoardReader.read("reversi_draw"), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createRoboPlayer2(), new DummyUserInputReader());
        Reversi reversi = new Reversi(new DummyRenderer(), gameLogic);
        GameSnapshot result = reversi.play();

        checkEndGame(result, "reversi_draw", GameStatus.DRAW, 0, 0);
    }

    @Test
    public void player1Wins() throws Exception {
        GameLogic gameLogic = new DummyGameLogic(BoardReader.read("reversi_player1_wins_start"), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createRoboPlayer2(), new DummyUserInputReader());
        Reversi reversi = new Reversi(new DummyRenderer(), gameLogic);
        GameSnapshot result = reversi.play();

        checkEndGame(result, "reversi_player1_wins_end", GameStatus.PLAYER1_WIN, 3, 0);
    }

    @Test
    public void player2Wins() throws Exception {
        GameLogic gameLogic = new DummyGameLogic(BoardReader.read("reversi_player2_wins_start"), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createRoboPlayer2(), new DummyUserInputReader());
        Reversi reversi = new Reversi(new DummyRenderer(), gameLogic);
        GameSnapshot result = reversi.play();

        checkEndGame(result, "reversi_player2_wins_end", GameStatus.PLAYER2_WIN, 0, 3);
    }

    private void checkEndGame(GameSnapshot result, String finalState, GameStatus gameStatus, int scorePlayer1, int scorePlayer2) throws Exception {
        Board board = BoardReader.read(finalState);
        assertThat(result.getBoard().equals(board), is(true));
        assertThat(result.getStatus().isGameOver(), is(true));
        assertThat(result.getStatus(), is(gameStatus));
        assertThat(result.getScore().getPlayer1Score(), is(scorePlayer1));
        assertThat(result.getScore().getPlayer2Score(), is(scorePlayer2));
    }
}