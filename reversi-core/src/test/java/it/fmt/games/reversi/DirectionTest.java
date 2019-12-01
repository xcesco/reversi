package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DirectionTest {

    @Test
    public void testDirectionUp() {
        checkTranslation("A1", Direction.UP, -1, 0, false);
    }

    private void checkTranslation(String intialPosition, Direction direction, int destinationRow, int destinationCol, boolean validCoordinate) {
        Coordinates coords1 = Coordinates.of(intialPosition);
        coords1 = coords1.translate(direction);

        Coordinates coords2 = Coordinates.of(destinationRow, destinationCol);

        assertThat(coords1, equalTo(coords2));
        assertThat(coords1.isValid(), is(validCoordinate));
    }

    @Test
    public void testA1DirectionDown() {
        checkTranslation("A1", Direction.DOWN, 1, 0, true);
    }

    @Test
    public void testDirectionLeft() {
        checkTranslation("A1", Direction.LEFT, 0, 1, true);
    }
}
