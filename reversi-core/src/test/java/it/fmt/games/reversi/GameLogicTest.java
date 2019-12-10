package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    @Test
    void initBoard() throws Exception {
        GameLogic gameLogic=new GameLogic();
        gameLogic.initBoard();
        Board aspectedBoard= BoardReader.read("gameLogicTest");

        assertThat(gameLogic.getBoard(), is(aspectedBoard));
    }
}