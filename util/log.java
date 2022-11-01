package util;

public class log extends textColor {
    public static void LogMatrix(String element, float[][] o) {
        System.out.println(changeColor(element + ":", Color.CYAN));
        for (int i = 0; i < o.length; i++) {
            System.out.print("[");
            for (int j = 0; j < o.length - 1; j++) {
                System.out.print(o[i][j] + ",");
            }
            System.out.println(o[i][o.length - 1] + "]");

        }
    }

    public static void logError(String msg, boolean exit) {
        System.err.println(changeColor("ERROR :", Color.RED) + msg);
        if (exit) {
            System.exit(1);
        }
    }

    public static void line() {
        System.out.println("----------------------------------------------------------------------------------");
    }

    public static void logType(String type) {
        System.out.println("                               " + changeColor("******* " + type + " *******", Color.BLUE));
    }

    public static void logElement(String element, Object value) {
        System.out.println(changeColor(element + " : ", Color.CYAN) + value);
    }

    public static void logElement(String element) {
        System.out.println(changeColor(element + " : ", Color.CYAN));
    }
}
