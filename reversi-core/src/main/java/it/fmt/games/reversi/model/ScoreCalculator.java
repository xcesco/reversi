package it.fmt.games.reversi.model;

public class ScoreCalculator {

    public static Score computeScore(Board board) {
        int[] score = new int[Piece.values().length];
        board.getCellStream().forEach(cell -> score[cell.getPiece().ordinal()]++);
        return new Score(score[Piece.PLAYER_1.ordinal()], score[Piece.PLAYER_2.ordinal()]);
    }

}
