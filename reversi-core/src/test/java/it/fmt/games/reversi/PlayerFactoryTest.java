package it.fmt.games.reversi;

import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.model.Player;
import it.fmt.games.reversi.model.cpu.RandomDecisionHandler;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PlayerFactoryTest {

    @Test
    public void createUserPlayer2() {
        Player2 player = PlayerFactory.createHumanPlayer2();
        check(player, Piece.PLAYER_2);
    }

    @Test
    public void createUserPlayer1() {
        Player1 player = PlayerFactory.createHumanPlayer1();
        check(player, Piece.PLAYER_1);
    }

    @Test
    public void createRoboPlayer2() {
        Player2 player = PlayerFactory.createRoboPlayer2();
        check(player, Piece.PLAYER_2);
    }

    @Test
    public void testCreateRoboPlayer2() {
        Player2 player = PlayerFactory.createRoboPlayer2(DecisionHandlerType.RANDOM);
        check(player, Piece.PLAYER_2);
    }

    @Test
    public void testCreateRoboPlayer21() {
        Player2 player = PlayerFactory.createRoboPlayer2(new RandomDecisionHandler());
        check(player, Piece.PLAYER_2);
    }

    @Test
    public void createRoboPlayer1() {
        Player1 player = PlayerFactory.createRoboPlayer1();
        check(player, Piece.PLAYER_1);
    }

    @Test
    public void testCreateRoboPlayer1() {
        Player1 player = PlayerFactory.createRoboPlayer1(DecisionHandlerType.RANDOM);
        check(player, Piece.PLAYER_1);
    }

    @Test
    public void testCreateRoboPlayer11() {
        Player1 player = PlayerFactory.createRoboPlayer1(new RandomDecisionHandler());
        check(player, Piece.PLAYER_1);
    }

    private void check(Player player, Piece piece) {
        assertThat(player.getPiece(), is(piece));
    }
}