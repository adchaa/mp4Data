import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import atomType.*;
import util.*;

class Metadata {
    private static InputStream Stream;
    private static String Path;
    private static ArrayList<String> Types = new ArrayList<String>();
    private static long FileSize = 0;
    public static ftyp ftyp;
    public static moov moov;
    public static mdat mdat;

    public Metadata(String path) {
        Path = path;
        try {
            byte[] b;
            String type;
            long typesize;
            Stream = new FileInputStream(Path);
            while ((b = Stream.readNBytes(globalVariables.ATOM_HEADER_SIZE)).length == 8) {
                typesize = converter.arrayByteToUnsignedLong(Arrays.copyOfRange(b, 0, 4));
                type = converter.arrayByteToString(Arrays.copyOfRange(b, 4, 8));
                Types.add(type);
                FileSize += typesize;
                switch (type) {
                    case "ftyp":
                        ftyp = new ftyp(Stream, typesize);
                        ftyp.printFtyp();
                        break;
                    case "moov":
                        moov = new moov(Stream, typesize);
                        break;
                    case "mdat":
                        mdat = new mdat(Stream);
                        break;
                    default:
                        Log.logError("atom type (" + type + ") not supported.", true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Metadata("video.mp4");
    }

    public static ArrayList<String> getTypes() {
        return Types;
    }

    public long getFileSize() {
        return FileSize;
    }
}