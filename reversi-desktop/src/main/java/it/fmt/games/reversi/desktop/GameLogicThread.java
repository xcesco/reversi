package it.fmt.games.reversi.desktop;

import java.util.ArrayList;
import java.util.List;

import it.fmt.games.reversi.GameRenderer;
import it.fmt.games.reversi.Player1;
import it.fmt.games.reversi.Player2;
import it.fmt.games.reversi.Reversi;
import it.fmt.games.reversi.UserInputReader;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.Player;

public class GameLogicThread extends Thread {
    private final Reversi reversi;
    public final Move acceptedMove = new Move();
    private final App app;
    private GameRenderer uiRenderer;
    public List<Coordinates> availableMoves = new ArrayList<>();

    public GameLogicThread(App app, Player1 player1, Player2 player2, GameRenderer uiRenderer) {
        this.app=app;
        this.uiRenderer=uiRenderer;
        UserInputReader userInputReader = this::getCoordinates;
        GameRenderer gamerRenderer = this::dispatchToUiRenderer;
        this.reversi = new Reversi(gamerRenderer, userInputReader, player1, player2);
    }

    private void dispatchToUiRenderer(GameSnapshot gameSnapshot) {
        uiRenderer.render(gameSnapshot);
        /*app.gameSnapshot=gameSnapshot;
        app.repaint();*/
        /*app.runOnUiThread(() -> {
            uiRenderer.render(gameSnapshot);
        });*/
    }

    private Coordinates getCoordinates(Player player, List<Coordinates> list) {
        availableMoves = list;

        synchronized (acceptedMove) {
            while (isInvalidMove()) {
                try {
                    acceptedMove.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return acceptedMove.getCoordinates();
        }
    }

    private boolean isInvalidMove() {
        return acceptedMove.getCoordinates() == null || availableMoves.indexOf(acceptedMove.getCoordinates()) == -1;
    }

    @Override
    public void run() {
        reversi.play();
    }
}