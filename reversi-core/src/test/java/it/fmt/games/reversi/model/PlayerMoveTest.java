package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static it.fmt.games.reversi.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerMoveTest {

    @Test
    public void testInvalidCreation() {
        assertThrows(InvalidInsertOperationException.class, () -> {
            PlayerMove playerMove = new PlayerMove(null, of("a4"), Collections.emptyList());
        });

        assertThrows(InvalidInsertOperationException.class, () -> {
            PlayerMove playerMove = new PlayerMove(Piece.EMPTY , of("a4"), Collections.emptyList());
        });
    }

    @Test
    public void testCreation() {
        PlayerMove playerMove = new PlayerMove(Piece.PLAYER_1, of("a4"), Collections.emptyList());

        assertThat(playerMove.getPiece(), is(Piece.PLAYER_1));
        assertThat(playerMove.getMoveCoords(), is(of("a4")));
    }
}