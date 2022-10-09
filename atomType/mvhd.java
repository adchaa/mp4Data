package atomType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import util.*;

public class mvhd {
    public byte[] Version = new byte[1];
    // TODO : understand what is flag and add it to printMvhd
    public byte[] Flags = new byte[3];
    public byte[] CreationTime = new byte[4];
    public byte[] ModificationTime = new byte[4];
    public byte[] TimeScale = new byte[4];
    public byte[] Duration = new byte[4];
    public byte[] PreferredRate = new byte[4];
    public byte[] PreferredVolume = new byte[2];
    // TODO understand what is matrixStructure and add it to printmvhd
    public byte[] MatrixStructure = new byte[36];
    public byte[] PreviewTime = new byte[4];
    public byte[] PreviewDuration = new byte[4];
    public byte[] PosterTime = new byte[4];
    public byte[] SelectionTime = new byte[4];
    public byte[] SelectionDuration = new byte[4];
    public byte[] CurrentTime = new byte[4];
    public byte[] NextTrackID = new byte[4];

    private static String ToTimeScale(byte[] b, byte[] timescale) {
        return converter.arrayByteToUnsignedLong(b) / converter.arrayByteToUnsignedLong(timescale) + "s";
    }

    public void printMvhd() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("mvhd:");
        System.out.println("Version : " + (Version[0] & 0XFF));
        System.out.println("Creation time : " + converter.arrayByteToDate(CreationTime));
        System.out.println("Modification time : " + converter.arrayByteToDate(ModificationTime));
        System.out.println("Duration : " + ToTimeScale(Duration, TimeScale));
        System.out.println("Preferred rate : " + converter.arrayByteToUnsignedFixedPoint(PreferredRate));
        System.out.println("Preferred Volume : " + converter.arrayByteToUnsignedFixedPoint(PreferredVolume));
        // useless values to print
        // System.out.println("Preview time : " +
        // converter.arrayByteToUnsignedLong(PreviewTime));
        // System.out.println("Preview duration : " + ToTimeScale(PreviewDuration,
        // TimeScale));
        // System.out.println("Poster time : " +
        // converter.arrayByteToUnsignedLong(PosterTime));
        // System.out.println("Selection time : " +
        // converter.arrayByteToUnsignedLong(PosterTime));
        // System.out.println("Selection time : " +
        // converter.arrayByteToUnsignedLong(PosterTime));
        // System.out.println("Current time : " +
        // converter.arrayByteToUnsignedLong(CurrentTime));
        System.out.println("Next track ID : " + converter.arrayByteToUnsignedLong(NextTrackID));
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
            S.read(PreferredVolume);
            // skipped 10 byte because it is reserved for apple
            S.skip(10);
            S.read(MatrixStructure);
            S.read(PreviewTime);
            S.read(PreviewDuration);
            S.read(PosterTime);
            S.read(SelectionTime);
            S.read(SelectionDuration);
            S.read(CurrentTime);
            S.read(NextTrackID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
