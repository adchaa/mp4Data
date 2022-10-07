package atomType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import java.util.Date;
import util.*;

public class mvhd {
    public byte[] Version = new byte[1];
    // understand what is flag and add it to printMvhd
    public byte[] Flags = new byte[3];
    public byte[] CreationTime = new byte[4];
    public byte[] ModificationTime = new byte[4];
    public byte[] TimeScale = new byte[4];
    public byte[] Duration = new byte[4];
    public byte[] PreferredRate = new byte[4];

    public void printMvhd() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("mvhd:");
        System.out.println("Version : " + (Version[0] & 0XFF));
        System.out.println("Creation time : " + converter.arrayByteToDate(CreationTime));
        System.out.println("Modification time : " + converter.arrayByteToDate(ModificationTime));
        System.out.println("Duration : "
                + converter.arrayByteToUnsignedLong(Duration) / converter.arrayByteToUnsignedLong(TimeScale));
        System.out.println("---------------------------------------------------------------------------------");
    }

    public mvhd(InputStream S) {
        try {
            S.read(Version);
            S.read(Flags);
            S.read(CreationTime);
            S.read(ModificationTime);
            S.read(TimeScale);
            S.read(Duration);
            S.read(PreferredRate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
