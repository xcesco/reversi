package it.fmt.games.reversi.model;

import it.fmt.games.reversi.exceptions.InvalidCoordinatesException;
import it.fmt.games.reversi.support.BoardReader;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    @Test
    public void boardSize() {
        Board b = new Board();
        assertThat(b.cells.length, is(Board.BOARD_SIZE * Board.BOARD_SIZE));
    }

    @Test
    public void setCell() {
        Board b = new Board();
        cellCheck(b, "a1", Piece.PLAYER_1);
    }

    @Test
    public void invalidCoordinates() {
        Board b = new Board();
        assertThrows(InvalidCoordinatesException.class, () -> {
            b.setCell(Coordinates.of(99, 99), Piece.PLAYER_1);
        });
    }

    @Test
    public void setInvalidCell() throws Exception {
        Board b = new Board();
        boardInvalidCheck(b, "z1", Piece.PLAYER_1);
    }


    @Test
    public void readBoardConfiguration() throws Exception {
        Board board = BoardReader.read("boardConfig");
        assertThrows(InvalidCoordinatesException.class, () -> {
            board.getCellContent(Coordinates.of(0, 10));
        });
    }

    @Test
    public void setOnePieceFromFileConfiguration() throws Exception {
        Board board = BoardReader.read("boardConfig");

        assertThat(board.getCellContent(Coordinates.of("a1")), equalTo(Piece.PLAYER_2));
    }

    @Test
    public void setTwoPieceFromFileConfiguration() throws Exception {
        Board board = BoardReader.read("boardConfig");

        assertThat(board.getCellContent(Coordinates.of("a1")), equalTo(Piece.PLAYER_2));
        assertThat(board.getCellContent(Coordinates.of("a2")), equalTo(Piece.EMPTY));
    }

    @Test
    public void wrongMarkerInConfiguration() {
        assertThrows(RuntimeException.class, () -> {
            BoardReader.read("boardConfigWrong");
        });
    }

    @Test
    public void testEquals() {
        String dummyObject = "No cooordinates";

        Board board1 = new Board();
        Board board2 = board1.copy();
        Board board3 = board1.copy();
        board3.setCell(Coordinates.of("A1"), Piece.PLAYER_1);

        assertThat(board1.equals(board1), is(true));
        assertThat(board1.equals(null), is(false));
        assertThat(board1.equals(dummyObject), is(false));
        assertThat(board1.equals(board2), is(true));
        assertThat(board1.equals(board3), is(false));
    }

    @Test
    public void testHashcode() {
        Board board1 = new Board();
        Board board2 = board1.copy();
        Board board3 = board1.copy();
        board3.setCell(Coordinates.of("A1"), Piece.PLAYER_1);

        assertThat(board1.hashCode() == board2.hashCode(), is(true));
        assertThat(board1.hashCode() == board3.hashCode(), is(false));
    }

    private void cellCheck(Board board, String coord, Piece p) {
        Coordinates c = Coordinates.of(coord);
        board.setCell(c, p);

        assertThat(board.getCellContent(c), equalTo(p));
    }

    private void boardInvalidCheck(Board board, String coord, Piece p) {
        Coordinates c = Coordinates.of(coord);

        assertThrows(InvalidCoordinatesException.class, () -> {
            board.setCell(c, p);
        });

    }

}