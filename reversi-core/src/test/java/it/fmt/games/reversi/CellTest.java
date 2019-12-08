package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CellTest {
    @Test
    public void testEquals() {
        int row = 1;
        int col = 1;
        Coordinates cor = new Coordinates(row,col);
        Coordinates cor2 = new Coordinates(++row, ++col);

        Cell cell = new Cell(cor,Piece.PLAYER_1);
        Cell cellEqual = new Cell(cor,Piece.PLAYER_1);
        Cell cellNotEqual = new Cell(cor,Piece.PLAYER_2);
        Cell cellNotEqual2 = new Cell(cor2,Piece.PLAYER_1);
        String dummyObject = "Not cell";

        assertThat(cell, equalTo(cell));
        assertThat(cell, equalTo(cellEqual));
        assertThat(cell, not(cellNotEqual));
        assertThat(cell, not(cellNotEqual2));
        assertThat(cell.equals(null), is(false));
        assertThat(cell.equals(dummyObject), is(false));
    }

    @Test
    public void testHashCode() {
        int row = 1;
        int col = 1;
        Coordinates cor = new Coordinates(row,col);
        Coordinates cor2 = new Coordinates(++row, ++col);

        Cell cell1 = new Cell(cor,Piece.PLAYER_1);
        Cell cell2 = new Cell(cor2,Piece.PLAYER_2);

        assertThat(cell1.hashCode() == cell1.hashCode(), is(true));
        assertThat(cell1.hashCode() == cell2.hashCode(), is(false));
    }

    @Test
    public void player1CellTest() {
        int row = 1;
        int col = 1;
        Coordinates cor = new Coordinates(row,col);
        Cell cell = new Cell(cor,Piece.PLAYER_1);
        testCell(cell, Piece.PLAYER_1, cor.getRow(), cor.getColumn());
    }

    @Test
    public void player2CellTest() {
        int row = 1;
        int col = 1;
        Coordinates cor = new Coordinates(row,col);
        Cell cell = new Cell(cor,Piece.PLAYER_2);
        testCell(cell, Piece.PLAYER_2, row, col);
    }

    @Test
    public void emptyCellTest() {
        int row = 1;
        int col = 1;
        Coordinates cor = new Coordinates(row,col);
        Cell cell = new Cell(cor,Piece.EMPTY);
        testCell(cell, Piece.EMPTY, row, col);
    }

    public void testCell (Cell c, Piece p, int row, int col) {
        assertThat(c.getCoordinates().getRow(), is(row));
        assertThat(c.getCoordinates().getColumn(), is(col));
        assertThat(c.getPiece(), is(p));
    }
}
