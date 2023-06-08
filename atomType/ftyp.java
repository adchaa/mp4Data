package atomType;

import java.io.IOException;
import java.io.InputStream;
import util.*;
import java.util.ArrayList;

public class ftyp implements atom {
    private byte[] ByteMajorBrand = new byte[4];
    private byte[] ByteMinorBrand = new byte[4];
    private byte[][] ByteCompatibleBrand;

    private long Size;

    private String Majorbrand;
    private long Minorbrand;
    private ArrayList<String> Compatiblebrand;

    public void printData() {
        log.logType("FTYP");
        log.logElement("Major Brand", Majorbrand);
        log.logElement("Minor Brand", Minorbrand);
        log.logElement("Compatible brands", Compatiblebrand);
        log.line();
    }

    public void initValues() {
        Majorbrand = converter.arrayByteToString(ByteMajorBrand);
        Minorbrand = converter.arrayByteToUnsignedLong(ByteMinorBrand);
        Compatiblebrand = new ArrayList<String>();
        for (int i = 0; i < ByteCompatibleBrand.length; i++) {
            Compatiblebrand.add(converter.arrayByteToString(ByteCompatibleBrand[i]));
        }
        printData();
    }

    @Override
    public void constractAtom(InputStream S, long size) {
        this.Size = size;
        ByteCompatibleBrand = new byte[(int) (size - 16) / 4][4];
        try {
            S.read(ByteMajorBrand);
            S.read(ByteMinorBrand);
            for (int i = 0; i < ByteCompatibleBrand.length; i++) {
                S.read(ByteCompatibleBrand[i]);
            }
            initValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ftyp(InputStream S, long size) {
        constractAtom(S, size);
    }

    public String getMajorBrand() {
        return Majorbrand;
    }

    public long getMinorBrand() {
        return Minorbrand;
    }

    public ArrayList<String> getCompatibleBrand() {
        return Compatiblebrand;
    }

    @Override
    public long getSize() {
        return Size;
    }
}
