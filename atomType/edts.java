package atomType;

import java.io.InputStream;

public class edts extends container implements atom {
    private long Size;
    elst elst = new elst();

    @Override
    public void constractAtom(InputStream S, long size) {
        this.Size = size;
        atomMap.put("elst", elst);
        fetchData(S, size);
    }

    @Override
    public long getSize() {
        return Size;
    }
}
