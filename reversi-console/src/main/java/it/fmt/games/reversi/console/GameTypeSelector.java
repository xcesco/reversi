package it.fmt.games.reversi.console;

import java.util.Scanner;

import static it.fmt.games.reversi.console.TextDrawer.*;

public abstract class GameTypeSelector {

    private GameTypeSelector() {

    }

    public static int selectGameType(Scanner scanner) {
        int value;
        do {
            println("");
            println("  --------------- CHOOSE GAME TYPE ----------------");
            println("  1 - PLAYER_1 vs PLAYER_2");
            println("  2 - PLAYER_1 vs CPU");
            println("  3 - CPU vs PLAYER_2");
            println("  4 - CPU vs CPU");
            println("  0 - Exit");
            print("  Insert the number of game type: ");

            String input = scanner.nextLine().replace(" ","");
            value = toNumber(input);
        } while (!isValidChoice(value));

        return value;
    }

    private static boolean isValidChoice(int value) {
        boolean valid = value >= 0 && value <= 4;
        if (!valid) {
            println("  Invalid choice!");
        }
        return valid;
    }

    private static int toNumber(String value) {
        if (value == null || value.length() > 1) {
            return -1;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }
}
