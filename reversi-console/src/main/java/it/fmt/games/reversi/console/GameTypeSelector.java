package it.fmt.games.reversi.console;

import java.util.Scanner;

import static it.fmt.games.reversi.console.TextDrawer.*;

public abstract class GameTypeSelector {

    private GameTypeSelector() {

    }

    public static int selectGameType(Scanner scanner) {
        int value = -1;
        do {
            print("  ----- CHOOSE A GAME TYPE: -----"+NEW_LINE);
            print("  1 - PLAYER_1 vs PLAYER_2"+NEW_LINE);
            print("  2 - PLAYER_1 vs CPU"+NEW_LINE);
            print("  3 - CPU vs PLAYER_2"+NEW_LINE);
            print("  4 - CPU vs CPU"+NEW_LINE);
            print("  0 - Exit"+NEW_LINE);
            print("  Insert the number of game type: ");
            String input = scanner.next();

            value = toNumber(input);
        } while (!isValidChoice(value));

        return value;
    }

    private static boolean isValidChoice(int value) {
        boolean valid = value >= 0 && value <= 4;
        if (!valid) {
            println("  Invalid choice!");
            println("");
        }
        return valid;
    }

    private static int toNumber(String value) {
        if (value == null) {
            return -1;
        }
        try {
            int d = Integer.parseInt(value);
            return d;
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }
}
