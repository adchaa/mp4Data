package atomType;

import java.io.InputStream;

public class moov extends container implements atom {
    public mvhd mvhd = new mvhd();
    public trak trak = new trak();

    @Override
    public atom constractAtom(InputStream S, long size) {
        atomMap.put("mvhd", mvhd);
        atomMap.put("trak", trak);
        fetchData(S, size);
        return this;
    }

    public moov(InputStream S, long size) {
        constractAtom(S, size);
    }

}
