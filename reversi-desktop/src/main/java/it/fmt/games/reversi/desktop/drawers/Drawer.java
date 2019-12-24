package it.fmt.games.reversi.desktop.drawers;

import it.fmt.games.reversi.model.GameSnapshot;

import java.awt.*;

public interface Drawer {
    void draw(GameSnapshot gameSnapshot, Graphics g);
}
