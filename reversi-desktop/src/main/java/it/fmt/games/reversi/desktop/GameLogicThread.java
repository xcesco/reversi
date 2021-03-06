package it.fmt.games.reversi.desktop;

import it.fmt.games.reversi.*;
import it.fmt.games.reversi.desktop.pages.EndPage;
import it.fmt.games.reversi.desktop.pages.GamePage;
import it.fmt.games.reversi.desktop.pages.StartPage;
import it.fmt.games.reversi.desktop.pages.SwingUtils;
import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.GameSnapshot;
import it.fmt.games.reversi.model.Player;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameLogicThread extends Thread {
    private final Reversi reversi;
    public final Move acceptedMove = new Move();
    private final JFrame jFrame;
    private final GamePage gamePage;
    private GameRenderer uiRenderer;
    public List<Coordinates> availableMoves = new ArrayList<>();

    public GameLogicThread(JFrame jFrame, GamePage gamePage, Player1 player1, Player2 player2, GameRenderer uiRenderer) {
        this.jFrame = jFrame;
        this.gamePage = gamePage;
        this.uiRenderer = uiRenderer;
        UserInputReader userInputReader = this::getCoordinates;
        GameRenderer gamerRenderer = this::dispatchToUiRenderer;
        this.reversi = new Reversi(gamerRenderer, userInputReader, player1, player2);
    }

    private void dispatchToUiRenderer(GameSnapshot gameSnapshot) {
        SwingUtilities.invokeLater(() ->
                uiRenderer.render(gameSnapshot));
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
        GameSnapshot gameSnapshot = reversi.play();
        try {
            TimeUnit.MILLISECONDS.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            gamePage.setVisible(false);
            jFrame.getContentPane().removeAll();
            jFrame.getContentPane().add(new EndPage(jFrame, gameSnapshot));
            jFrame.validate();
        });

    }
}