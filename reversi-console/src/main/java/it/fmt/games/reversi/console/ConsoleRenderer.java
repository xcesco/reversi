package it.fmt.games.reversi.console;

import it.fmt.games.reversi.GameRenderer;
import it.fmt.games.reversi.model.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleRenderer extends Drawer implements GameRenderer {

    @Override
    public void render(GameSnapshot gameSnapshot) {
        new PlayerMoveDrawer(gameSnapshot.getLastMove()).drawPlayerMove();
        new CurrentPlayerDrawer(gameSnapshot.getActivePiece()).drawCurrentPlayer();
        new AvailableMovesDrawer(gameSnapshot.getAvailableMoves().getMovesActivePlayer()).drawAvailableMoves();
        new BoardDrawer(gameSnapshot.getBoard(), gameSnapshot.getAvailableMoves().getMovesActivePlayer()).drawBoard();
        new ScoreDrawer(gameSnapshot.getScore()).drawScore();
        new EndGameDrawer(gameSnapshot).drawEndGame();
    }
}