package atomType;

import java.io.InputStream;

public class trak extends container implements atom {
    public tkhd tkhd = new tkhd();
    public edts edts = new edts();

    private long Size;

    @Override
    public long getSize() {
        return Size;
    }

    @Override
    public void constractAtom(InputStream S, long size) {
        this.Size = size;
        atomMap.put("tkhd", tkhd);
        atomMap.put("edts", edts);
        fetchData(S, size);
    }

}
