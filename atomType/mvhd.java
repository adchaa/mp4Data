package atomType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import util.*;

public class mvhd {
    private byte[] Version = new byte[1];
    // useless flags (not sure)
    private byte[] Flags = new byte[3];
    private byte[] CreationTime = new byte[4];
    private byte[] ModificationTime = new byte[4];
    private byte[] TimeScale = new byte[4];
    private byte[] Duration = new byte[4];
    private byte[] PreferredRate = new byte[4];
    private byte[] PreferredVolume = new byte[2];
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
        Log.logType("MVHD");
        Log.logElement("Version", getVersion());
        Log.logElement("Creation time", getCreationTime());
        Log.logElement("Modification time", getModificationTime());
        Log.logElement("Duration", getDuration());
        Log.logElement("Preferred rate", getPreferredRate());
        Log.logElement("Preferred Volume", getPreferredVolume());
        // TODO : find solution how to print matrix
        // TODO : Solution is to create another function called Log.logMatrixElement()
        Log.logElement("Transformation matrix", getMatrixStructure());
        Log.logElement("Preview time", getPreviewTime());
        Log.logElement("Preview duration", getPreviewDuration());
        Log.logElement("Poster time", getPosterTime());
        Log.logElement("Selection time", getSelectionTime());
        Log.logElement("Selection Duration", getSelectionDuration());
        Log.logElement("Current time", getCurrentTime());
        Log.logElement("Next track ID", getNextTrackID());
        Log.line();
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

    public float[][] getMatrixStructure() {
        return converter.toTransformationMatrix(MatrixStructure);
    }

}
