package it.fmt.games.reversi;

public class GameLogic {
    private Board board;
    private Player currentPlayer;
    private Player otherPlayer;

    public Board getBoard() {
        return board;
    }

    public GameLogic(Board board) {
        this.board = board;
    }

    public GameLogic() {
        this.board = new Board();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    public void initializeBoard() {
        board.setCell(Coordinates.of("e4"), Piece.PLAYER_1);
        board.setCell(Coordinates.of("d4"), Piece.PLAYER_2);
        board.setCell(Coordinates.of("d5"), Piece.PLAYER_1);
        board.setCell(Coordinates.of("e5"), Piece.PLAYER_2);
        currentPlayer = new Player1();
        otherPlayer = new Player2();
    }




}
