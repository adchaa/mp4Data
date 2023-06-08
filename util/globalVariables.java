package util;

public class globalVariables {
    public final static byte ATOM_HEADER_SIZE = 8;
    // difference between Unix epoch and QuickTime epoch, in seconds
    public final static long EPOCH_ADJUSTER = 2082844800;
    private static long TIMESCALE;

    // it need to be set only once
    public static void setTIMESCALE(long x) {
        TIMESCALE = TIMESCALE == 0 ? x : TIMESCALE;
    }

    public static long getTIMESCALE() {
        return TIMESCALE;
    }
}
