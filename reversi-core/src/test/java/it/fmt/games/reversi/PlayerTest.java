package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static it.fmt.games.reversi.Coordinates.of;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {

    @Test
    void getPieceTest() {
        Player player1 = new Player(Piece.PLAYER_1);
        Player player2 = new Player(Piece.PLAYER_2);

        assertThat(player1.getPiece(), is(Piece.PLAYER_1));
        assertThat(player2.getPiece(), is(Piece.PLAYER_2));
        assertThat(player1.getPiece() == player2.getPiece(), is(false));
    }

    @Test
    void playersTest() {
        Player1 player1 = new Player1();
        Player2 player2 = new Player2();

        assertThat(player1.getPiece(), is(Piece.PLAYER_1));
        assertThat(player2.getPiece(), is(Piece.PLAYER_2));
        assertThat(player1.getPiece() == player2.getPiece(), is(false));
    }
}

