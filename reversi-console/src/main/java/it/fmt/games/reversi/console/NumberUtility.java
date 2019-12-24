package it.fmt.games.reversi.console;

public abstract class NumberUtility {
    private NumberUtility() {

    }

    public static boolean isInInterval(int value, int lowerLimitBound, int upperLimitBound) {
        return value >= lowerLimitBound && value <= upperLimitBound;
    }

    public static int toNumber(String value) {
        if (value == null) {
            return -1;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }
}
