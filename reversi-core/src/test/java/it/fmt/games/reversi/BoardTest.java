package it.fmt.games.reversi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void setCell() {
        Board b = new Board();
        cellCheck(b, "a1", Piece.PLAYER_1);
    }

    public void cellCheck(Board board, String coord, Piece p) {
        Coordinates c = Coordinates.of(coord);
        board.setCell(c, p);

        assertThat(board.getCellContent(c), equalTo(p));
    }

    @Test
    public void invalidCoordinates() {
        Board b = new Board();
        assertThrows(RuntimeException.class, () -> {
            b.setCell(Coordinates.of(99, 99), Piece.PLAYER_1);
        });
    }

    @Test
    public void setInvalidCell() throws Exception {
        Board b = new Board();
        boardInvalidCheck(b, "z1", Piece.PLAYER_1);
    }

    public void boardInvalidCheck(Board board, String coord, Piece p) {
        Coordinates c = Coordinates.of(coord);

        assertThrows(RuntimeException.class, () -> {
            board.setCell(c, p);
        });

    }

    @Test
    public void ReadBoardConfiguration() throws Exception {
        Board board = BoardReader.read("boardConfig");
        assertThrows(RuntimeException.class, () -> {
            board.getCellContent(Coordinates.of(0, 10));
        });
    }

    @Test
    public void SetOnePieceFromFileConfiguration() throws Exception {
        Board board = BoardReader.read("boardConfig");

        assertThat(board.getCellContent(Coordinates.of("a1")), equalTo(Piece.PLAYER_2));

    }

    @Test
    public void SetTwoPieceFromFileConfiguration() throws Exception {
        Board board = BoardReader.read("boardConfig");

        assertThat(board.getCellContent(Coordinates.of("a1")), equalTo(Piece.PLAYER_2));
        assertThat(board.getCellContent(Coordinates.of("a2")), equalTo(Piece.EMPTY));
    }

    @Test
    public void WrongMarkerInConfiguration() {
        assertThrows(RuntimeException.class, () -> {
            BoardReader.read("boardConfigWrong");
        });
    }
}