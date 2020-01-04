package it.fmt.games.reversi.console;

import it.fmt.games.reversi.GameRenderer;
import it.fmt.games.reversi.console.drawers.*;
import it.fmt.games.reversi.model.GameSnapshot;

public class ConsoleRenderer extends TextDrawer implements GameRenderer {

    @Override
    public void render(GameSnapshot gameSnapshot) {
        println("");
        BoardDrawer.drawBoard(gameSnapshot.getBoard(), gameSnapshot.getAvailableMoves().getMovesActivePlayer());
        ScoreDrawer.drawScore(gameSnapshot.getScore());
        PlayerMoveDrawer.drawPlayerMove(gameSnapshot.getLastMove());
        if (gameSnapshot.getStatus().isGameOver()) {
            EndGameDrawer.drawEndGame(gameSnapshot.getStatus(), gameSnapshot.getScore());
        } else {
            CurrentPlayerDrawer.drawCurrentPlayer(gameSnapshot.getActivePiece());
            AvailableMovesDrawer.drawAvailableMoves(gameSnapshot.getAvailableMoves().getMovesActivePlayer());
        }
    }
}