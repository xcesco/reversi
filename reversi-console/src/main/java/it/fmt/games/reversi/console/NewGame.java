package it.fmt.games.reversi.console;
import java.util.Scanner;

import static it.fmt.games.reversi.console.TextDrawer.*;

public abstract class NewGame {
    private static Scanner scanner = new Scanner(System.in);
    private NewGame() {

    }

    public static boolean playAgain() {
        int value = -1;
        do {
            println("");
            println("  Do you want to play again?");
            println("  1 - YES");
            println("  0 - EXIT");
            print("  Insert your choice: ");

            String input = scanner.next();
            value = toNumber(input);
        } while (!isValidChoice(value));

        return value == 1 ? true : false;
    }

    private static boolean isValidChoice(int value) {
        boolean valid = value >= 0 && value <= 4;
        if (!valid) {
            println("Invalid choice!");
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
