package it.fmt.games.reversi;

public class Player {
    private final Piece piece;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return piece == player.piece;
    }

    @Override
    public int hashCode() {
        return piece.hashCode();
    }

    public Piece getPiece() {
        return piece;
    }

    public Player(Piece piece) {
        this.piece = piece;
    }
}
