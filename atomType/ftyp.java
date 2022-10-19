package atomType;

import java.io.IOException;
import java.io.InputStream;
import util.*;
import java.util.ArrayList;

public class ftyp {
    private byte[] byteMajorbrand = new byte[4];
    private byte[] byteMinorbrand = new byte[4];
    private byte[][] byteCompatiblebrand;

    private String Majorbrand;
    private long Minorbrand;
    private ArrayList<String> Compatiblebrand;

    public void printFtyp() {
        Log.logType("FTYP");
        Log.logElement("Major Brand", Majorbrand);
        Log.logElement("Minor Brand", Minorbrand));
        Log.logElement("Compatible brands", Compatiblebrand);
        Log.line();
    }

    public void initValues(){
        Majorbrand = converter.arrayByteToString(ByteMajorBrand);
        Minorbrand = converter.arrayByteToUnsignedLong(ByteMinorBrand);
        Compatiblebrand = new ArrayList<String>();
        for (int i = 0; i < compatibleBrand.length; i++) {
            Compatiblebrand.add(converter.arrayByteToString(ByteCompatibleBrand[i]));
        }
    }
    public ftyp(InputStream S, long size) {
        compatibleBrand = new byte[(int) (size - 16) / 4][4];
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

    public String getMajorBrand() {
        return Majorbrand;
    }

    public long getMinorBrand() {
        return Minorbrand;
    }

    public ArrayList<String> getCompatibleBrand() {
        return Compatiblebrand;
    }
}
