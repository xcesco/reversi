package it.fmt.games.reversi.console;

import it.fmt.games.reversi.GameRenderer;
import it.fmt.games.reversi.model.*;

public class ConsoleRenderer extends TextDrawer implements GameRenderer {

    @Override
    public void render(GameSnapshot gameSnapshot) {
        println("");
        BoardTextDrawer.drawBoard(gameSnapshot.getBoard(), gameSnapshot.getAvailableMoves().getMovesActivePlayer());
        ScoreTextDrawer.drawScore(gameSnapshot.getScore());
        PlayerMoveTextDrawer.drawPlayerMove(gameSnapshot.getLastMove());
        if (gameSnapshot.getStatus().isGameOver()) {
            EndGameTextDrawer.drawEndGame(gameSnapshot.getStatus(), gameSnapshot.getScore());
        } else {
            CurrentPlayerTextDrawer.drawCurrentPlayer(gameSnapshot.getActivePiece());
            AvailableMovesTextDrawer.drawAvailableMoves(gameSnapshot.getAvailableMoves().getMovesActivePlayer());
        }
    }
}