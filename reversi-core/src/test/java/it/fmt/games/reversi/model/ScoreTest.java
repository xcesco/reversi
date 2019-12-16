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
        Score score1 = new Score(6, 7);
        Score score2 = new Score(6, 7);
        Score score3 = new Score(16, 27);
        Score score4 = new Score(6, 27);

        assertThat(score1.equals(score1), is(true));
        assertThat(score1.equals(score3), is(false));
        assertThat(score1.equals(score4), is(false));
        assertThat(score1.equals(null), is(false));
        assertThat(score1.equals("dummy"), is(false));
        assertThat(score1.equals(score2), is(true));
        assertThat(score1.hashCode() == score2.hashCode(), is(true));
    }

}
