package atomType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import util.*;

public class mvhd {
    private byte[] Version = new byte[1];
    // TODO : understand what is flag and add it to print
    private byte[] Flags = new byte[3];
    private byte[] CreationTime = new byte[4];
    private byte[] ModificationTime = new byte[4];
    private byte[] TimeScale = new byte[4];
    private byte[] Duration = new byte[4];
    private byte[] PreferredRate = new byte[4];
    private byte[] PreferredVolume = new byte[2];
    // TODO : understand what is MatrixStructure and add it to print
    private byte[] MatrixStructure = new byte[36];
    private byte[] PreviewTime = new byte[4];
    private byte[] PreviewDuration = new byte[4];
    private byte[] PosterTime = new byte[4];
    private byte[] SelectionTime = new byte[4];
    private byte[] SelectionDuration = new byte[4];
    private byte[] CurrentTime = new byte[4];
    private byte[] NextTrackID = new byte[4];

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
        globalVariables.setTIMESCALE(converter.arrayByteToUnsignedLong(TimeScale));
    }

    public void printMvhd() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("mvhd:");
        System.out.println("Version : " + getVersion());
        System.out.println("Creation time : " + getCreationTime());
        System.out.println("Modification time : " + getModificationTime());
        System.out.println("Duration : " + getDuration());
        System.out.println("Preferred rate : " + getPreferredRate());
        System.out.println("Preferred Volume : " + getPreferredVolume());
        System.out.println("Preview time : " + getPreviewTime());
        System.out.println("Preview duration : " + getPreviewDuration());
        System.out.println("Poster time : " + getPosterTime());
        System.out.println("Selection time : " + getSelectionTime());
        System.out.println("Selection Duration : " + getSelectionDuration());
        System.out.println("Current time : " + getCurrentTime());
        System.out.println("Next track ID : " + getNextTrackID());
        System.out.println("---------------------------------------------------------------------------------");
    }

    public long getPreviewTime() {
        return converter.arrayByteToUnsignedLong(PreviewTime);
    }

    public long getPreviewDuration() {
        return converter.ToTimeScale(PreviewDuration);
    }

    public long getPosterTime() {
        return converter.arrayByteToUnsignedLong(PosterTime);
    }

    public long getSelectionTime() {
        return converter.arrayByteToUnsignedLong(SelectionTime);
    }

    public long getSelectionDuration() {
        return converter.ToTimeScale(SelectionDuration);

    }

    public long getCurrentTime() {
        return converter.arrayByteToUnsignedLong(CurrentTime);
    }

    public int getVersion() {
        return (Version[0] & 0XFF);
    }

    public Date getCreationTime() {
        return converter.arrayByteToDate(CreationTime);
    }

    public Date getModificationTime() {
        return converter.arrayByteToDate(ModificationTime);
    }

    public long getDuration() {
        return converter.ToTimeScale(Duration);
    }

    public float getPreferredRate() {
        return converter.arrayByteToUnsignedFixedPoint(PreferredRate);
    }

    public float getPreferredVolume() {
        return converter.arrayByteToUnsignedFixedPoint(PreferredVolume);
    }

    public long getNextTrackID() {
        return converter.arrayByteToUnsignedLong(NextTrackID);
    }

}
