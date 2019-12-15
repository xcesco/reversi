package it.fmt.games.reversi.desktop;

import java.util.List;

import it.fmt.games.reversi.model.Coordinates;
import it.fmt.games.reversi.model.RandomDecisionHandler;

public class DesktopDecisionHandler extends RandomDecisionHandler {
        @Override
        public Coordinates compute(List<Coordinates> availableMoves) {
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return super.compute(availableMoves);
        }
    }