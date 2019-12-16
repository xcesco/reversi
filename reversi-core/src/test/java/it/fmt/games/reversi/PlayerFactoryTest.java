package it.fmt.games.reversi;

import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.model.Player;
import it.fmt.games.reversi.model.cpu.RandomDecisionHandler;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PlayerFactoryTest {

    @Test
    void createUserPlayer2() {
        Player2 player = PlayerFactory.createHumanPlayer2();
        check(player, Piece.PLAYER_2);
    }

    @Test
    void createUserPlayer1() {
        Player1 player = PlayerFactory.createHumanPlayer1();
        check(player, Piece.PLAYER_1);
    }

    @Test
    void createRoboPlayer2() {
        Player2 player = PlayerFactory.createRoboPlayer2();
        check(player, Piece.PLAYER_2);
    }

    @Test
    void testCreateRoboPlayer2() {
        Player2 player = PlayerFactory.createRoboPlayer2(DecisionHandlerType.RANDOM);
        check(player, Piece.PLAYER_2);
    }

    @Test
    void testCreateRoboPlayer21() {
        Player2 player = PlayerFactory.createRoboPlayer2(new RandomDecisionHandler());
        check(player, Piece.PLAYER_2);
    }

    @Test
    void createRoboPlayer1() {
        Player1 player = PlayerFactory.createRoboPlayer1();
        check(player, Piece.PLAYER_1);
    }

    @Test
    void testCreateRoboPlayer1() {
        Player1 player = PlayerFactory.createRoboPlayer1(DecisionHandlerType.RANDOM);
        check(player, Piece.PLAYER_1);
    }

    @Test
    void testCreateRoboPlayer11() {
        Player1 player = PlayerFactory.createRoboPlayer1(new RandomDecisionHandler());
        check(player, Piece.PLAYER_1);
    }

    private void check(Player player, Piece piece) {
        assertThat(player.getPiece(), is(piece));
    }
}