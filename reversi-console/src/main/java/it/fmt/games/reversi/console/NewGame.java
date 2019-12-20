package it.fmt.games.reversi.console;
import java.util.Scanner;

import static it.fmt.games.reversi.console.TextDrawer.*;

public abstract class NewGame {
    private static Scanner scanner = new Scanner(System.in);
    private NewGame() {

    }

    public static boolean playAgain() {
        int value;
        do {
            println("");
            println("  Do you want to play again?");
            println("  1 - YES");
            println("  0 - EXIT");
            print("  Insert your choice: ");

            String input = scanner.nextLine().replace(" ", "");
            value = toNumber(input);
        } while (!isValidChoice(value));
        println("");
        return value > 0;
    }

    private static boolean isValidChoice(int value) {
        boolean valid = value >= 0 && value <= 1;
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
