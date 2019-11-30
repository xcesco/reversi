package it.fmt.games.reversi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Test coordinates class")
public class CoordinatesTest {

    @Test
    public void invalidCoordinates() {
        Coordinates coords = Coordinates.of(99, 99);

        assertThat(coords.isValid(), is(false));
    }

    @Test
    public void testEquals() {
        Coordinates coords1 = Coordinates.of("A1");
        Coordinates coords2 = Coordinates.of(0,0);

        assertThat(coords1.equals(coords2), is(true));
    }

    @Test
    public void validCoordinates() {
        Coordinates coords = Coordinates.of(0, 0);

        assertThat(coords.isValid(), is(true));
        assertThat(coords.toString(), is("A1"));
    }


}