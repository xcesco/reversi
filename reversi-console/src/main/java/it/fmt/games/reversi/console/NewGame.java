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
            print(NEW_LINE);
            print("  Do you want to play again?"+NEW_LINE);
            print("  1 - YES"+NEW_LINE);
            print("  0 - EXIT"+NEW_LINE);
            print("  Insert your choice: ");

            String input = scanner.next();
            value = toNumber(input);
        } while (!isValidChoice(value));

        return value == 1 ? true : false;
    }

    private static boolean isValidChoice(int value) {
        boolean valid = value >= 0 && value <= 4;
        if (!valid) {
            print("Invalid choice!"+NEW_LINE);
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
