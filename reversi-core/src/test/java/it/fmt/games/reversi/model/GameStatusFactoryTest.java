package it.fmt.games.reversi.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static it.fmt.games.reversi.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GameStatusFactoryTest {

    @Test
    void createRunningStatus() {
        checkStatus(Arrays.asList(of("a1")), Collections.emptyList(), 12, 24, false, GameStatus.RUNNING);
    }

    @Test
    void createPlayer1Wins() {
        checkStatus(Collections.emptyList(), Collections.emptyList(), 36, 24, true, GameStatus.PLAYER1_WIN);
    }

    @Test
    void createPlayer2Wins() {
        checkStatus(Collections.emptyList(), Collections.emptyList(), 12, 24, true, GameStatus.PLAYER2_WIN);
    }

    @Test
    void createDraw() {
        checkStatus(Collections.emptyList(), Collections.emptyList(), 12, 12, true, GameStatus.DRAW);
    }

    private void checkStatus(List<Coordinates> player1AvailMoves, List<Coordinates> player2AvailMoves, int player1Score, int player2Score, boolean finished, GameStatus aspectedStatus) {
        GameStatus gameStatus = GameStatusFactory.create(new AvailableMoves(player1AvailMoves, player2AvailMoves), new Score(player1Score, player2Score));
        assertThat(gameStatus.isGameOver(), is(finished));
        assertThat(gameStatus, is(aspectedStatus));
    }
}