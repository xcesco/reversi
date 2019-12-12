package it.fmt.games.reversi.model;

import it.fmt.games.reversi.Player1;
import it.fmt.games.reversi.Player2;
import it.fmt.games.reversi.PlayerFactory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static it.fmt.games.reversi.model.Coordinates.of;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    @Test
    public void getPieceTest() {
        Player player1 = PlayerFactory.createUserPlayer1();
        Player player2 = PlayerFactory.createUserPlayer2();

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
}

