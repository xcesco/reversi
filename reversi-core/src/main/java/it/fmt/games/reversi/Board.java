package it.fmt.games.reversi;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {
    public static final int BOARD_SIZE = 8;
    final Cell[] cells;

    public Board(){
        this.cells = new Cell[BOARD_SIZE*BOARD_SIZE];
        IntStream.range(0,BOARD_SIZE)
                .forEach(row -> IntStream.range(0,BOARD_SIZE)
                        .forEach(col -> setCell(Coordinates.of(row,col),Piece.EMPTY)));

    }
    public Stream<Cell> getCellStream(){
        return Arrays.stream(cells);
    }
    public void setCell(Coordinates coordinates, Piece content) {
        if (!coordinates.isValid()) throw new RuntimeException("Invalid coordinates!");
        cells[coordinates.getRow() * BOARD_SIZE + coordinates.getColumn()] = new Cell(coordinates, content);
    }

    public Piece getCellContent(Coordinates coordinates) {
        if (!coordinates.isValid()) throw new RuntimeException("Invalid coordinates!");
        return cells[coordinates.getRow() * BOARD_SIZE + coordinates.getColumn()].getPiece();
    }

    public boolean isCellContentEqualsTo(Coordinates coordinates, Piece currentPlayer) {
        return coordinates.isValid() && getCellContent(coordinates) == currentPlayer;
    }


}


