package it.fmt.games.reversi.console;

public class SelectGameDrawer extends Drawer{

    public SelectGameDrawer() {
    }

    public static void drawSelectGame() {
        draw("1 - Player 1 vs Player 2"+NEW_LINE);
        draw("2 - Player 1 vs CPU"+NEW_LINE);
        draw("3 - CPU vs Player 2"+NEW_LINE);
        draw("4 - CPU vs CPU"+NEW_LINE);
        draw("Choose one game options: ");
    }
}
