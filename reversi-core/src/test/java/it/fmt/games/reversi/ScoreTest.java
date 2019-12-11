package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ScoreTest {

    @Test
    public void playersScoresTest() {
        Score scores = new Score(6,7);
        assertThat(scores.getPlayer1Score(), is(6));
        assertThat(scores.getPlayer2Score(), is(7));
    }

}
