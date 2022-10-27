package atomType;

import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;
import util.*;

public class trak extends container implements atom {
    public tkhd tkhd = new tkhd();

    @Override
    public atom constractAtom(InputStream S, long size) {
        atomMap.put("tkhd", tkhd);
        fetchData(S, size);
        return this;
    }

}
