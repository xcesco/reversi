package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GameLogicTest {

    @Test
    void initBoard() throws Exception {
        GameLogic gameLogic=new GameLogic();
        gameLogic.initializeBoard();
        Board aspectedBoard= BoardReader.read("gameLogicTest");

        assertThat(gameLogic.getBoard(), is(aspectedBoard));
        assertThat(gameLogic.getCurrentPlayer().getPiece(),is(Piece.PLAYER_1));
        assertThat(gameLogic.getOtherPlayer().getPiece(),is(Piece.PLAYER_2));
    }

}