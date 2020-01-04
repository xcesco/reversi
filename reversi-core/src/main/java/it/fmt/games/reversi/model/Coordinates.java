package it.fmt.games.reversi.model;

public class Coordinates implements Comparable<Coordinates> {
    private int row;
    private int column;

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Coordinates(String value) {
        value = value.toUpperCase();
        if ((value.length() == 2) && charInRange(value.charAt(0), 'A', 'H')
                && charInRange(value.charAt(1), '1', '8')) {
            row = value.charAt(1) - '0' - 1;
            column = value.charAt(0) - 'A';
        } else {
            row = -1;
            column = -1;
        }
    }

    public static Coordinates of(int row, int column) {
        return new Coordinates(row, column);
    }

    public static Coordinates of(String value) {
        return new Coordinates(value);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isValid() {
        return (row >= 0 && row < Board.BOARD_SIZE && column >= 0 && column < Board.BOARD_SIZE);
    }

    public Coordinates translate(Direction direction) {
        return translate(direction, 1);
    }

    public Coordinates translate(Direction direction, int count) {
        int newRow = direction.getOffsetRow() * count + row;
        int newCol = direction.getOffsetCol() * count + column;
        return Coordinates.of(newRow, newCol);
    }

    @Override
    public String toString() {
        return "" + ((char) ('A' + column)) + (row + 1);
    }

    @Override
    public int compareTo(Coordinates o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (row != that.row) return false;
        return column == that.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    private boolean charInRange(char input, char lowerBound, char upperBound) {
        return input >= lowerBound && input <= upperBound;
    }
}
