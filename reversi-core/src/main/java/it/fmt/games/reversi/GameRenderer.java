package it.fmt.games.reversi;

import it.fmt.games.reversi.model.GameSnapshot;

public interface GameRenderer {
    void render(GameSnapshot gameSnapshot);
}
