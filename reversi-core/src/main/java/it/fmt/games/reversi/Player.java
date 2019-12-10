package it.fmt.games.reversi;

public class Player {
    private final Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public Player(Piece piece) {
        this.piece = piece;
    }
}
