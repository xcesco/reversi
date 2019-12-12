package it.fmt.games.reversi;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class BoardDrawer {
    private Board board;
    private Piece player = Piece.PLAYER_1;
    private List<Coordinates> playerMoves;
    private final String prefix = "\t";
    static final String ROW_SEPARATOR = "  +-----+-----+-----+-----+-----+-----+-----+-----+";

    public BoardDrawer(Board board) {
        this.board = board;
        AvailableMovesFinder moves = new AvailableMovesFinder(this.board, player);
        this.playerMoves = moves.findMoves();
    }

    public void drawBoard() {

        System.out.print(prefix+"  ");
        IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                System.out.print(String.format("   %s  ", (char) ('A' + col))));
        System.out.println("\n" + prefix + ROW_SEPARATOR);
        IntStream.range(0, Board.BOARD_SIZE).forEach(row -> {
            System.out.print(String.format(prefix + "%s |", row + 1));
            IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                    System.out.print(String.format("  %s  |", drawAvailableMovesOnBoard(Coordinates.of(row, col)))));
            System.out.println("\n" + prefix + ROW_SEPARATOR);
        });
    }

    public String drawAvailableMovesOnBoard(Coordinates coords) {
        return board.getCellContent(coords) == Piece.EMPTY ? (isAvailableMove(coords) ? "?" : " ")
                : board.getCellContent(coords) == Piece.PLAYER_1 ? "O" : "X";
    }

    public boolean isAvailableMove(Coordinates coordinates) {
        return playerMoves.indexOf(coordinates)!=-1;
    }
}

//public class BoardDrawer {
//    private GameSnapshot gameSnapshot;
//
//    static final String PREFIX = "\t";
//    static final String ROW_SEPARATOR = "  +-----+-----+-----+-----+-----+-----+-----+-----+";
//
//    public BoardDrawer(GameSnapshot snapshot) {
//        this.gameSnapshot = snapshot;
//    }
//
//    public void drawGameStatus() {
//        drawBoard();
//        drawCurrentPlayer();
//        drawScore();
//        drawPlayerMoves();
//    }
//
//    public void drawBoard() {
//        System.out.print(PREFIX + "  ");
//        IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
//                System.out.print(String.format("   %s  ", (char) ('A' + col))));
//        System.out.println("\n" + PREFIX + ROW_SEPARATOR);
//        IntStream.range(0, Board.BOARD_SIZE).forEach(row -> {
//                System.out.print(String.format(PREFIX + "%s |", row + 1));
//                IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
//                        System.out.print(String.format("  %s  |", drawAvailableMovesOnBoard(Coordinates.of(row, col)))));
//                System.out.println("\n" + PREFIX + ROW_SEPARATOR);
//        });
//    }
//
//    public String drawAvailableMovesOnBoard(Coordinates coord) {
//        return gameSnapshot.getBoard().getCellContent(coord) == Piece.EMPTY ? (isAvailableMove(coord) ? "?" : " ")
//                 : gameSnapshot.getBoard().getCellContent(coord) == Piece.PLAYER_1 ? "O" : "X";
//    }
//
//    public boolean isAvailableMove(Coordinates coord) {
//        return gameSnapshot.getAvailableMoves().getMovesCurrentPlayer().indexOf(coord)!=-1;
//    }
//
//    public void drawCurrentPlayer() {
//        System.out.print("\n" + PREFIX + "  ");
//        System.out.print("***************");
//        System.out.print(" PLAYER " + (gameSnapshot.getCurrentPlayer().getPiece() == Piece.PLAYER_1 ? "1 (O)" : "2 (X)") + " TURN! ");
//        System.out.println("**************");
//    }
//
//    public void drawPlayerMoves() {
//        System.out.print(PREFIX + "  ");
//        System.out.print("AVAILABLE MOVES: ");
//        gameSnapshot.getAvailableMoves().
//                getMovesCurrentPlayer().forEach(moves -> System.out.print(String.format(moves + " ")));
//    }
//
//    public void drawScore() {
//        System.out.print(PREFIX + "  ");
//        System.out.println("PLAYER 1 (O) SCORE: " + gameSnapshot.getScore().getPlayer1Scores());
//        System.out.print(PREFIX + "  ");
//        System.out.println("PLAYER 2 (X) SCORE: " + gameSnapshot.getScore().getPlayer2Scores());
//    }
//}
