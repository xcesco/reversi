package it.fmt.games.reversi.console;

import java.util.Scanner;

import static it.fmt.games.reversi.console.TextDrawer.*;

public abstract class GameTypeSelector {

    private GameTypeSelector() {

    }

    public static int selectGameType(Scanner scanner) {
        int value = -1;
        do {
            print("Choose a type of game:"+NEW_LINE);
            print("1 - Player 1 vs Player 2"+NEW_LINE);
            print("2 - Player 1 vs CPU"+NEW_LINE);
            print("3 - CPU vs Player 2"+NEW_LINE);
            print("4 - CPU vs CPU"+NEW_LINE);
            print("0 - Exit"+NEW_LINE);
            print("Insert the number of one game type: ");
            String input = scanner.next();

            value = toNumber(input);
        } while (!isValidChoice(value));

        return value;
    }

    private static boolean isValidChoice(int value) {
        boolean valid = value >= 0 && value <= 4;
        if (!valid) {
            print("Invalid choose!"+NEW_LINE);
            print(NEW_LINE);
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
