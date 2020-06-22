package it.fmt.games.reversi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.fmt.games.reversi.exceptions.InvalidCoordinatesException;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static it.fmt.games.reversi.model.Coordinates.of;

public class Board {
    public static final int BOARD_SIZE = 8;
    final Cell[] cells;

    public Board() {
        this.cells = init(coordinates -> new Cell(coordinates, Piece.EMPTY));
    }

    @JsonCreator
    public Board(@JsonProperty("cells") Cell[] cells) {
        this.cells = cells;
    }

    public Board copy() {
        return new Board(init(coordinates -> new Cell(coordinates, getCellContent(coordinates))));
    }

    public Stream<Cell> getCellStream() {
        return Arrays.stream(cells);
    }

    public void setCell(Coordinates coordinates, Piece content) {
        if (!coordinates.isValid()) throw new InvalidCoordinatesException();
        cells[flatIndex(coordinates)] = new Cell(coordinates, content);
    }

    private Cell[] init(Function<Coordinates, Cell> filler) {
        return IntStream.range(0, BOARD_SIZE * BOARD_SIZE).mapToObj(this::asCoords).map(filler).toArray(Cell[]::new);
    }

    private int flatIndex(Coordinates coordinates) {
        return coordinates.getRow() * BOARD_SIZE + coordinates.getColumn();
    }

    private Coordinates asCoords(int flatIndex) {
        return of(flatIndex / BOARD_SIZE, flatIndex % BOARD_SIZE);
    }

    public Piece getCellContent(Coordinates coordinates) {
        if (!coordinates.isValid()) throw new InvalidCoordinatesException();
        return cells[flatIndex(coordinates)].getPiece();
    }

    public boolean isCellContentEqualsTo(Coordinates coordinates, Piece currentPlayer) {
        return coordinates.isValid() && getCellContent(coordinates) == currentPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;
        return Arrays.equals(cells, board.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cells);
    }
}


