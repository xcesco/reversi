package it.fmt.games.reversi.console;

public class TextDrawer {
    static final String PREFIX = "\t";
    static final String NEW_LINE = "\n";

    static void print(String string) {
        System.out.print(PREFIX + string);
    }
    static void println(String string) {
        System.out.print(PREFIX + string + NEW_LINE);
    }
}
