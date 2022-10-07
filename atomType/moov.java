package atomType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import util.*;

public class moov {
    public mvhd mvhd;

    public moov(InputStream S) {

        try {
            byte[] b;
            String type;
            long typesize;
            while ((b = S.readNBytes(globalVariables.ATOM_HEADER_SIZE)).length == 8) {
                typesize = converter.arrayByteToUnsignedLong(Arrays.copyOfRange(b, 0, 4));
                type = converter.arrayByteToString(Arrays.copyOfRange(b, 4, 8));
                switch (type) {
                    case "mvhd":
                        mvhd = new mvhd(S);
                        mvhd.printMvhd();
                        break;
                    default:
                        System.err.println(
                                "ERROR : atom type not supported. moov");
                        System.exit(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
