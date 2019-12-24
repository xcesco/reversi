package it.fmt.games.reversi.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DirectionTest {

    @Test
    public void testDirectionUp() {
        checkValidTranslation("B2", Direction.UP, "B1");
    }

    @Test
    public void testDirectionUpLeft() {
        checkValidTranslation("B2", Direction.UP_LEFT, "A1");
    }

    @Test
    public void testDirectionUpRight() {
        checkValidTranslation("B2", Direction.UP_RIGHT, "C1");
    }

    @Test
    public void testA1DirectionDown() {
        checkValidTranslation("A1", Direction.DOWN, "A2");
    }

    @Test
    public void testDirectionLeft() {
        checkValidTranslation("B2", Direction.LEFT, "A2");
    }

    @Test
    public void testDirectionDown() {
        checkValidTranslation("B2", Direction.DOWN, "B3");
    }

    @Test
    public void testDirectionDownLeft() {
        checkValidTranslation("B2", Direction.DOWN_LEFT, "a3");
    }

    @Test
    public void testDirectionDownRight() {
        checkValidTranslation("B2", Direction.DOWN_RIGHT, "C3");
    }

    @Test
    public void checkInvalidTranslationA1() {
        checkInvalidTranslation("a1", Direction.UP);
    }

    @Test
    public void checkInvalidTranslationG8() {
        checkInvalidTranslation("g8", Direction.DOWN);
    }

    private void checkValidTranslation(String intialPosition, Direction direction, String finalPosition) {
        Coordinates coords1 = Coordinates.of(intialPosition);
        coords1 = coords1.translate(direction);

        Coordinates coords2 = Coordinates.of(finalPosition);

        assertThat(coords1, equalTo(coords2));
        assertThat(coords1.isValid(), is(true));
    }

    private void checkInvalidTranslation(String intialPosition, Direction direction) {
        Coordinates coords1 = Coordinates.of(intialPosition);
        coords1 = coords1.translate(direction);

        assertThat(coords1.isValid(), is(false));
    }
}
