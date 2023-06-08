package atomType;

import java.io.InputStream;

public interface atom {
    public void constractAtom(InputStream S, long size);

    public long getSize();
}
