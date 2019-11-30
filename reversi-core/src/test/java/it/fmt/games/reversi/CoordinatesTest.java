package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CoordinatesTest {

    @Test
    public void invalidCoordinates() {
        Coordinates coords = Coordinates.of(99, 99);

        assertThat(coords.isValid(), is(false));
    }

    @Test
    public void testEquals() {
        Coordinates coords1 = Coordinates.of("A1");
        Coordinates coords2 = Coordinates.of(0, 0);

        assertThat(coords1, equalTo(coords2));
        assertThat(coords1.isValid(), is(true));
    }

    @Test
    public void testDirectionUp() {
        Coordinates coords1 = Coordinates.of("A1");
        coords1 = coords1.translate(Direction.UP);

        Coordinates coords2 = Coordinates.of(-1, 0);

        assertThat(coords1, equalTo(coords2));
        assertThat(coords1.isValid(), is(false));
    }

    @Test
    public void testDirectionDown() {
        Coordinates coords1 = Coordinates.of("A1");
        coords1 = coords1.translate(Direction.DOWN);

        Coordinates coords2 = Coordinates.of(1, 0);

        assertThat(coords1, equalTo(coords2));
        assertThat(coords1.isValid(), is(true));
    }

    @Test
    public void testDirectionLeft() {
        Coordinates coords1 = Coordinates.of("A1");
        coords1 = coords1.translate(Direction.LEFT);

        Coordinates coords2 = Coordinates.of(0, 1);

        assertThat(coords1, equalTo(coords2));
        assertThat(coords1.isValid(), is(true));
    }


    @Test
    public void validCoordinates() {
        Coordinates coords = Coordinates.of(0, 0);

        assertThat(coords.isValid(), is(true));
        assertThat(coords.toString(), is("A1"));
    }


}