package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CoordinatesTest {

    @Test
    public void checkRow_1Col_1() {
        checkCoordinateValidity(-1, -1, false);
    }

    @Test
    public void checkRow_0Col_0() {
        checkCoordinateValidity(0, 0, true);
    }

    @Test
    public void checkRow_4Col_10() {
        checkCoordinateValidity(4, 10, false);
    }

    @Test
    public void checkRow99Col99() {
        checkCoordinateValidity(99, 99, false);
    }

    @Test
    public void checkRow0Col0() {
        checkCoordinateValidity(0, 0, true);
    }

    @Test
    public void checkRow3Col3() {
        checkCoordinateValidity(3, 3, true);
    }

    @Test
    public void checkRow7Col7() {
        checkCoordinateValidity(7, 7, true);
    }

    @Test
    public void checkCase() {
        Coordinates coords1 = Coordinates.of("A1");
        Coordinates coords2 = Coordinates.of("a1");

        assertThat(coords1, equalTo(coords2));
    }

    @Test
    public void testConversionA1() {
        checkValidConversion("A1", 0, 0);
    }

    @Test
    public void testConversionAz() {
        checkInvalidConversion("Az");
    }

    @Test
    public void validCoordinates() {
        Coordinates coords = Coordinates.of(0, 0);

        assertThat(coords.isValid(), is(true));
        assertThat(coords.toString(), is("A1"));
    }

    private void checkValidConversion(String label, int row, int col) {
        Coordinates coords1 = Coordinates.of(label);
        Coordinates coords2 = Coordinates.of(col, col);

        assertThat(coords1, equalTo(coords2));
        assertThat(coords1.isValid(), is(true));
    }

    private void checkInvalidConversion(String label) {
        Coordinates coords1 = Coordinates.of(label);
        assertThat(coords1.isValid(), is(false));
    }

    private void checkCoordinateValidity(int row, int col, boolean valid) {
        Coordinates coords = Coordinates.of(row, col);
        assertThat(coords.isValid(), is(valid));
    }

}