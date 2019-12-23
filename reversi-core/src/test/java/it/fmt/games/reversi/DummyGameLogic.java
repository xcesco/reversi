package it.fmt.games.reversi;

import it.fmt.games.reversi.model.AvailableMoves;
import it.fmt.games.reversi.model.Board;
import it.fmt.games.reversi.model.GameLogicImpl;
import it.fmt.games.reversi.model.Player;

import static it.fmt.games.reversi.model.operators.ScoreCalculator.computeScore;

public class DummyGameLogic extends GameLogicImpl {
    public DummyGameLogic(Board initialBoard, Player player1, Player player2, UserInputReader userInputReader) {
        super(player1, player2, userInputReader);
        this.board = initialBoard;
    }

    @Override
    public AvailableMoves initialize() {
        gameSnapshotBuilder.setActivePiece(currentPlayer.getPiece()).setScore(computeScore(board)).setBoard(board.copy());
        return findMovesForPlayers();
    }
}
