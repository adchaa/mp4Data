package atomType;

import java.io.IOException;
import java.io.InputStream;
import util.converter;
import java.util.ArrayList;

public class ftyp {
    private byte[] majorBrand = new byte[4];
    private byte[] minorBrand = new byte[4];
    private byte[][] compatibleBrand;

    public void printFtyp() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Major Brand : " + getMajorBrand());
        System.out.println("Minor Brand : " + getMinorBrand());
        System.out.println("Compatible brands : " + getCompatibleBrand());
        System.out.println("---------------------------------------------------------------------------------");
    }

    public ftyp(InputStream S, long size) {
        compatibleBrand = new byte[(int) (size - 16) / 4][4];
        try {
            S.read(majorBrand);
            S.read(minorBrand);
            for (int i = 0; i < compatibleBrand.length; i++) {
                S.read(compatibleBrand[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMajorBrand() {
        return converter.arrayByteToString(majorBrand);
    }

    public long getMinorBrand() {
        return converter.arrayByteToUnsignedLong(minorBrand);
    }

    public ArrayList<String> getCompatibleBrand() {
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i < compatibleBrand.length; i++) {
            output.add(converter.arrayByteToString(compatibleBrand[i]));
        }
        return output;
    }
}
