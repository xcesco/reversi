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
        Board b = new Board();
        cellCheck(b, "a1", Piece.PLAYER_1);
    }

    @Test
    public void setInvalidCell() {
        Board b = new Board();
        boardInvalidCheck(b, "z1", Piece.PLAYER_1);

    }

    public void cellCheck(Board board, String coord, Piece p) {
        Coordinates c = Coordinates.of(coord);
        board.setCell(c, p);

        assertThat(board.getCellContent(c), equalTo(p));
    }

    public void boardInvalidCheck(Board board, String coord, Piece p) {
        Coordinates c = Coordinates.of(coord);

        assertThrows(RuntimeException.class, () -> {
            board.setCell(c, p);
        });
    }

}