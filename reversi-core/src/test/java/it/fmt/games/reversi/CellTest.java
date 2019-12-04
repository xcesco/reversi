package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CellTest {
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
