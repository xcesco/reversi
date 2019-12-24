package it.fmt.games.reversi.desktop;

import java.util.List;

import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.cpu.RandomDecisionHandler;

public class DesktopDecisionHandler extends RandomDecisionHandler {
    public static final int THINKING_TIME = 700;

    @Override
    public Coordinates compute(List<Coordinates> availableMoves) {
        try {
            Thread.sleep(THINKING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.compute(availableMoves);
    }
}