package it.fmt.games.reversi;

public class GameLogic {
    public Board getBoard() {
        return board;
    }

    private Board board;

    public GameLogic(Board board) {
        this.board = board;
    }

    public GameLogic() {
        this.board = new Board();
    }

    public void initBoard() {

        board.setCell(Coordinates.of("e4"), Piece.PLAYER_1);
        board.setCell(Coordinates.of("d4"), Piece.PLAYER_2);
        board.setCell(Coordinates.of("d5"), Piece.PLAYER_1);
        board.setCell(Coordinates.of("e5"), Piece.PLAYER_2);

    }
}
