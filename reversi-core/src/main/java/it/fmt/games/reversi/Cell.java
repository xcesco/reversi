package it.fmt.games.reversi;

public class Cell {

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Piece getPiece() {
        return piece;
    }

    private final Coordinates coordinates;
    private final Piece piece;

    public Cell(Coordinates c, Piece p) {
        this.coordinates = c;
        this.piece = p;
    }



}
