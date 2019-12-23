package it.fmt.games.reversi.console.drawers;

public class TextDrawer {
    static final String PREFIX = "\t";
    static final String NEW_LINE = "\n";

    public static void print(String string) {
        System.out.print(PREFIX + string);
    }
    public static void println(String string) {
        System.out.print(PREFIX + string + NEW_LINE);
    }
}
