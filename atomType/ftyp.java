package atomType;

import java.io.IOException;
import java.io.InputStream;
import util.converter;

public class ftyp {
    public byte[] majorBrand = new byte[4];
    public byte[] minorBrand = new byte[4];
    public byte[][] compatibleBrand;

    public void printFtyp() {
        System.out.println("Major Brand : " + converter.arrayByteToString(majorBrand));
        System.out.println("Minor Brand : " + converter.arrayByteToUnsignedLong(minorBrand));
        System.out.print("Compatible brands : ");
        for (int i = 0; i < compatibleBrand.length - 1; i++) {
            System.out.print(converter.arrayByteToString(compatibleBrand[i]) + ",");
        }
        System.out.println(converter.arrayByteToString(compatibleBrand[compatibleBrand.length - 1]));
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
}
