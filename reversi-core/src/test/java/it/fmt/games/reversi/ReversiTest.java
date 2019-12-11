package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ReversiTest {

    @Test
    void play() {
        Reversi reversi=new Reversi(new GameRenderer() {
            @Override
            public void render(GameSnapshot gameSnapshot) {

            }
        });
        GameSnapshot result = reversi.play();

        assertThat(result.getStatus().isGameOver(), is(true));
    }
}