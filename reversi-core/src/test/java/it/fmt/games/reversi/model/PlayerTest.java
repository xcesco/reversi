package it.fmt.games.reversi.model;

import it.fmt.games.reversi.DecisionHandlerType;
import it.fmt.games.reversi.Player1;
import it.fmt.games.reversi.Player2;
import it.fmt.games.reversi.PlayerFactory;
import it.fmt.games.reversi.exceptions.HumanPlayerNeedUserInputException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static it.fmt.games.reversi.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    @Test
    public void getPieceTest() {
        Player player1 = PlayerFactory.createHumanPlayer1();
        Player player2 = PlayerFactory.createHumanPlayer2();

        assertThat(player1.getPiece(), is(Piece.PLAYER_1));
        assertThat(player2.getPiece(), is(Piece.PLAYER_2));
        assertThat(player1.getPiece() == player2.getPiece(), is(false));
    }

    @Test
    public void playersTest() {
        Player1 player1 = new Player1();
        Player2 player2 = new Player2();

        assertThat(player1.getPiece(), is(Piece.PLAYER_1));
        assertThat(player2.getPiece(), is(Piece.PLAYER_2));
        assertThat(player1.getPiece() == player2.getPiece(), is(false));
    }

    @Test
    public void humanPlayerNeedUserInterface() {
        assertThrows(HumanPlayerNeedUserInputException.class, () -> {
            Player1 player1 = PlayerFactory.createHumanPlayer1();

            player1.computeNextMove(Collections.singletonList(of("a2")));
        });
    }

    @Test
    public void randomCpuPlayer() {
        Player2 player2 = PlayerFactory.createCpuPlayer2(DecisionHandlerType.RANDOM);

        Coordinates move=player2.computeNextMove(Collections.singletonList(of("a2")));
        assertThat(move, is(of("a2")));

        List<Coordinates> availMoves = Arrays.asList(of("a2"), of("a3"));
        Coordinates move2=player2.computeNextMove(availMoves);
        assertThat(availMoves, hasItem(move2));
    }
}

