package it.fmt.games.reversi;

public class Coordinates {
    private int row;
    private int column;

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

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinates of(int row, int column) {
        return new Coordinates(row, column);
    }

    public static Coordinates of(String value) {
        String v=value.toUpperCase();

        return new Coordinates(v.charAt(1)-'0'-1, v.charAt(0)-'A');
    }

    @Override
    public String toString() {
        return ""+((char)('A'+column))+(row+1);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isValid() {
        return (row >= 0 && row <= 7 && column >= 0 && column <= 7);
    }

    public Coordinates translate(Direction direction) {
        return translate(direction, 1);
    }

    public Coordinates translate(Direction direction, int count) {
        int new_row = direction.getOffsetRow()*count + row;
        int new_col = direction.getOffsetCol()*count + column;
        return Coordinates.of(new_row, new_col);
    }
}