package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void newBoard() {
        Board b = new Board();
    }

    @Test
    public void setCell() {
        Coordinates coords1 = Coordinates.of("A1");
        Board b =new Board();
        b.setCell(coords1,Piece.PLAYER_1);
        assertThat(b.getCell(coords1).getPiece(), equalTo(Piece.PLAYER_1));
    }


}