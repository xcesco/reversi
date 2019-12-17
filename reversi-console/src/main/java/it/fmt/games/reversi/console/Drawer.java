package it.fmt.games.reversi.console;

public class Drawer {
    static final String PREFIX = "\t";
    static final String NEW_LINE = "\n";
    static final String ROW_SEPARATOR = "  +-----+-----+-----+-----+-----+-----+-----+-----+";

    static void draw(String string) {
        System.out.print(PREFIX+string);
    }
}
