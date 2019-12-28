package it.fmt.games.reversi.console.drawers;

public class TextDrawer {
    static final String PREFIX = "\t";
    static final String NEW_LINE = "\n";

    static String player1AsString;
    static String player2AsString;

    public static void setPlayersAsString(String player1, String player2) {
        player1AsString = player1;
        player2AsString = player2;
    }

    public TextDrawer() {
    }

    public static void print(int tabNumber, String string) {
        for (int i=0; i<tabNumber; i++) {
            System.out.print(PREFIX);
        }
        System.out.print(string);
    }

    public static void print(String string) {
        print(1,string);
    }

    public static void println(String string) {
        println(1,string);
    }

    public static void println(int tabNumber, String string) {
        for (int i=0; i<tabNumber; i++) {
            System.out.print(PREFIX);
        }
        System.out.print(string + NEW_LINE);
    }
}
