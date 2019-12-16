package it.fmt.games.reversi.model.operators;

import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.Piece;
import it.fmt.games.reversi.model.Score;

public abstract class ScoreCalculator {

    private ScoreCalculator() {

    }

    public static Score computeScore(Board board) {
        int[] score = new int[Piece.values().length];
        board.getCellStream().forEach(cell -> score[cell.getPiece().ordinal()]++);
        return new Score(score[Piece.PLAYER_1.ordinal()], score[Piece.PLAYER_2.ordinal()]);
    }

}
