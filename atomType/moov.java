package atomType;

import java.io.InputStream;

public class moov extends container implements atom {
    public mvhd mvhd = new mvhd();
    public trak trak = new trak();

    private long Size;

    @Override
    public void constractAtom(InputStream S, long size) {
        atomMap.put("mvhd", mvhd);
        atomMap.put("trak", trak);
        fetchData(S, size);
    }

    public moov(InputStream S, long size) {
        constractAtom(S, size);
    }

    public long getSize() {
        return Size;
    }

}
