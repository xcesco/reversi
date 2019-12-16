package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidInsertOperationException;
import it.fmt.games.reversi.exceptions.InvalidPieceSelectedException;
import it.fmt.games.reversi.model.operators.EnemyPiecesHunter;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static it.fmt.games.reversi.model.Coordinates.of;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerMoveTest {

    @Test
    public void testCreation() {
        assertThrows(InvalidInsertOperationException.class, () -> {
            PlayerMove playerMove = new PlayerMove(null, of("a4"), Collections.emptyList());
        });


    }
}