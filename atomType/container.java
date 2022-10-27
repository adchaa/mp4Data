package atomType;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;

import util.*;

public abstract class container {
    protected HashMap<String, atom> atomMap = new HashMap<String, atom>();

    protected void fetchData(InputStream S, long typesize) {
        try {
            byte[] b;
            String type;
            long subtypesize;
            long size = 0;
            while (size != typesize) {
                b = S.readNBytes(globalVariables.ATOM_HEADER_SIZE);
                subtypesize = converter.arrayByteToUnsignedLong(Arrays.copyOfRange(b, 0, 4));
                type = converter.arrayByteToString(Arrays.copyOfRange(b, 4, 8));
                size += subtypesize;
                if (atomMap.containsKey(type)) {
                    atomMap.put(type, atomMap.get(type).constractAtom(S, subtypesize));
                } else {
                    log.logError("atom type \"" + type + "\" not supported.", true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
