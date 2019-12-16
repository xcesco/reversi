package it.fmt.games.reversi.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ScoreTest {

    @Test
    public void playersScoresTest() {
        Score scores = new Score(6, 7);

        assertThat(scores.getPlayer1Score(), is(6));
        assertThat(scores.getPlayer2Score(), is(7));
    }

    @Test
    public void playersScoresEqualsAndHashcodeTest() {
        Score scores1 = new Score(6, 7);
        Score scores2 = new Score(6, 7);

        assertThat(scores1.equals(null), is(false));
        assertThat(scores1.equals("dummy"), is(false));
        assertThat(scores1.equals(scores2), is(true));
        assertThat(scores1.hashCode() == scores2.hashCode(), is(true));
    }

}
