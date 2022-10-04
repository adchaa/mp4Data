import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import atomType.*;

class Metadata {
    final static byte ATOM_HEADER_SIZE = 8;
    // difference between Unix epoch and QuickTime epoch, in seconds
    final static long EPOCH_ADJUSTER = 2082844800;

    private static InputStream Stream;
    private static String Path;
    private static ArrayList<String> Types = new ArrayList<String>();
    private static long FileSize = 0;
    public static ftyp ftyp;
    public static moov moov;
    public static mdat mdat;

    private static String byteToHex(byte i) {
        String output = Integer.toHexString((i & 0xff));
        if (output.length() < 2) {
            output = '0' + output;
        }
        return output;
    }

    private static String arrayByteToHex(byte[] b) {
        String output = "";
        for (byte i : b) {
            output = output + byteToHex(i) + ' ';
        }
        return output.substring(0, output.length() - 1);
    }

    private static long arrayByteToUnsignedLong(byte[] b) {
        long x = 0;
        for (byte i : b) {
            x = (x << 8) + (i & 0xFF);
        }
        return x;
    }

    private static String arrayByteToString(byte[] b) {
        String output = "";
        for (byte i : b) {
            output += (char) (i & 0XFF);
        }
        return output;
    }

    public Metadata(String path) {
        Path = path;
        try {
            byte[] b;
            String type;
            long typesize;
            Stream = new FileInputStream(Path);
            while ((b = Stream.readNBytes(ATOM_HEADER_SIZE)).length != 0) {
                typesize = arrayByteToUnsignedLong(Arrays.copyOfRange(b, 0, 4));
                type = arrayByteToString(Arrays.copyOfRange(b, 4, 8));
                Types.add(type);
                FileSize += typesize;
                switch (type) {
                    case "ftyp":
                        ftyp = new ftyp(Stream);
                        break;
                    case "moov":
                        moov = new moov(Stream);
                        break;
                    case "mdat":
                        mdat = new mdat(Stream);
                        break;
                    default:
                        System.err.println(
                                "ERROR : atom type not supported. please check the file extension. it need to be mp4");
                        System.exit(0);
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