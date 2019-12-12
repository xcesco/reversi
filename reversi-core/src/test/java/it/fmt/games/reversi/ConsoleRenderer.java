package it.fmt.games.reversi;

import it.fmt.games.reversi.model.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleRenderer implements GameRenderer {
    static final String ROW_SEPARATOR = "  +---+---+---+---+---+---+---+---+";
    private final String prefix;

    public ConsoleRenderer(String prefix) {
        this.prefix = prefix;
    }

    public ConsoleRenderer() {
        this.prefix = "\t";
    }


    private void drawAvailableMoves(List<Coordinates> availableMoves) {
        System.out.print(prefix + "Available moves:");
        availableMoves.forEach(item -> System.out.print(" " + item));
        System.out.println("");
    }

    private void drawPlayerMove(Piece piece, Coordinates nextMove, List<Coordinates> invertedCoordinates) {
        if (invertedCoordinates != null) {
            System.out.println(String.format(prefix + "%s moves on %s", piece, nextMove));
            String result = invertedCoordinates.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "{", "}"));
            System.out.println(String.format(prefix + "inverted pieces on (#%s): %s", invertedCoordinates.size(), result));
        }
    }

    private void drawBoard(Board board) {
        System.out.print(prefix + "  ");
        IntStream.range(0, Board.BOARD_SIZE).forEach(col ->
                System.out.print(String.format("  %s ", (char) ('A' + col)))
        );

        System.out.println("\n" + prefix + ROW_SEPARATOR);
        IntStream.range(0, Board.BOARD_SIZE).forEach(row -> {
            System.out.print(String.format(prefix + "%s |", row + 1));
            IntStream.range(0, Board.BOARD_SIZE).forEach(col -> {
                System.out.print(String.format(" %s |", getSymbol(board.getCellContent(Coordinates.of(row, col)))));
            });
            System.out.println("\n" + prefix + ROW_SEPARATOR);
        });
    }

    private String getSymbol(Piece piece) {
        return (piece.ordinal() == 0) ? " " : (piece == Piece.PLAYER_1) ? "o" : "x";
    }

    private void drawCurrentPlayer(Piece piece) {
        System.out.println("Player :" + piece);
    }

    public void drawBeginGame() {
        System.out.println("Reversi game - Console Version");
    }


    private void drawScore(Score score) {
        System.out.println(String.format(prefix + "Player1 score: %d, Player2 score: %d", score.getPlayer1Score(), score.getPlayer2Score()));
    }

    public void drawEndGame(GameSnapshot game) {
        GameStatus status = game.getStatus();
        Score score = game.getScore();
        switch (status) {
            case DRAW:
                System.out.println(prefix + "Draw");
                break;
            case PLAYER1_WIN:
                System.out.println(String.format(prefix + "Player 2 wins: player1 pieces: %d, player2 pieces: %d", score.getPlayer1Score(), score.getPlayer2Score()));
                break;
            case PLAYER2_WIN:
                System.out.println(String.format(prefix + "Player 1 wins: player1 pieces: %d, player2 pieces: %d", score.getPlayer1Score(), score.getPlayer2Score()));
                break;
        }
    }

    @Override
    public void render(GameSnapshot gameSnapshot) {
        //drawPlayerMove(gameSnapshot.getInactivePiece(), gameSnapshot.getLastMove(), gameSnapshot.getInvertedPiecesByInactivePiece());
        drawCurrentPlayer(gameSnapshot.getActivePiece());
        drawAvailableMoves(gameSnapshot.getAvailableMoves().getMovesActivePlayer());
        drawBoard(gameSnapshot.getBoard());
        drawScore(gameSnapshot.getScore());
    }
}