package util;

public class Log {
    private static final String SANE = "\u001B[0m";

    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    private static final String BACKGROUND_BLACK = "\u001B[40m";
    private static final String BACKGROUND_RED = "\u001B[41m";
    private static final String BACKGROUND_GREEN = "\u001B[42m";
    private static final String BACKGROUND_YELLOW = "\u001B[43m";
    private static final String BACKGROUND_BLUE = "\u001B[44m";
    private static final String BACKGROUND_MAGENTA = "\u001B[45m";
    private static final String BACKGROUND_CYAN = "\u001B[46m";
    private static final String BACKGROUND_WHITE = "\u001B[47m";

    public static void logError(String msg, boolean exit) {
        System.err.println(RED + "ERROR" + " : " + SANE + msg);
        if (exit) {
            System.exit(1);
        }
    }

    public static void line() {
        System.out.println("----------------------------------------------------------------------------------");
    }

    public static void logType(String type) {
        System.out.println("                               " + BLUE + "******* " + type + " *******" + SANE);
    }

    public static void logElement(String element, Object value) {
        System.out.println(CYAN + element + " : " + SANE + value);
    }
}
