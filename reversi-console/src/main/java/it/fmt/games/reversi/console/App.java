package it.fmt.games.reversi.console;

import it.fmt.games.reversi.GameRenderer;
import it.fmt.games.reversi.PlayerFactory;
import it.fmt.games.reversi.Reversi;
import it.fmt.games.reversi.model.*;

import java.util.Scanner;

public class App extends Drawer{
    final static Scanner scanner = new Scanner(System.in);
    private static SelectGameDrawer selectGameDrawer = new SelectGameDrawer();
    private static ConsoleRenderer consoleRenderer = new ConsoleRenderer();
    private static Reversi reversi;


    public static void main(String[] args) {
        draw("************ FMT Reversi ************"+NEW_LINE);
        selectGameDrawer.drawSelectGame();
        int choose = scanner.nextInt();

        while (choose < 1 || choose > 4) {
            draw("Invalid choose!"+NEW_LINE);
            draw("*************************************"+NEW_LINE);
            draw(NEW_LINE);

            selectGameDrawer.drawSelectGame();
            choose = scanner.nextInt();
        }

        switch (choose) {
            case 1:
                draw("************ Player 1 vs Player 2 ************"+NEW_LINE);
                //reversi = new Reversi(consoleRenderer, );
                break;
            case 2:
                draw("************ Player 1 vs CPU ************"+NEW_LINE);
                break;
            case 3:
                draw("************ CPU vs Player 2 ************"+NEW_LINE);
                break;
            case 4:
                draw("************ CPU vs CPU ************"+NEW_LINE);
                break;
        }
    }
}
