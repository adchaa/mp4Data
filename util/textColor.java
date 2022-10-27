package util;

import java.util.HashMap;

public abstract class textColor {
    enum Color {
        BLACK,
        RED,
        GREEN,
        YELLOW,
        BLUE,
        MAGENTA,
        CYAN,
        WHITE
    }

    enum BackgroundColor {
        BLACK,
        RED,
        GREEN,
        YELLOW,
        BLUE,
        MAGENTA,
        CYAN,
        WHITE
    }

    public static final String SANE = "\u001B[0m";

    final static HashMap<Color, String> ColorMap = new HashMap<Color, String>();
    final static HashMap<BackgroundColor, String> BackgroundColorMap = new HashMap<BackgroundColor, String>();
    static {
        ColorMap.put(Color.BLACK, "\u001B[30m");
        ColorMap.put(Color.RED, "\u001B[31m");
        ColorMap.put(Color.GREEN, "\u001B[32m");
        ColorMap.put(Color.YELLOW, "\u001B[33m");
        ColorMap.put(Color.BLUE, "\u001B[34m");
        ColorMap.put(Color.MAGENTA, "\u001B[35m");
        ColorMap.put(Color.CYAN, "\u001B[36m");
        ColorMap.put(Color.WHITE, "\u001B[37m");

        BackgroundColorMap.put(BackgroundColor.BLACK, "\u001B[30m");
        BackgroundColorMap.put(BackgroundColor.RED, "\u001B[31m");
        BackgroundColorMap.put(BackgroundColor.GREEN, "\u001B[32m");
        BackgroundColorMap.put(BackgroundColor.YELLOW, "\u001B[33m");
        BackgroundColorMap.put(BackgroundColor.BLUE, "\u001B[34m");
        BackgroundColorMap.put(BackgroundColor.MAGENTA, "\u001B[35m");
        BackgroundColorMap.put(BackgroundColor.CYAN, "\u001B[36m");
        BackgroundColorMap.put(BackgroundColor.WHITE, "\u001B[37m");
    }

    public static String changeColor(String msg, Color c) {
        return ColorMap.get(c) + msg + SANE;
    }

    public static String changeBackground(String msg, BackgroundColor c) {
        return BackgroundColorMap.get(c) + msg + SANE;
    }

}
