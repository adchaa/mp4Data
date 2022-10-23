package atomType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import util.*;

public class mvhd {
    private byte[] ByteVersion = new byte[1];
    private byte[] ByteFlags = new byte[3];
    private byte[] ByteCreationTime = new byte[4];
    private byte[] ByteModificationTime = new byte[4];
    private byte[] ByteTimeScale = new byte[4];
    private byte[] ByteDuration = new byte[4];
    private byte[] BytePreferredRate = new byte[4];
    private byte[] BytePreferredVolume = new byte[2];
    private byte[] ByteMatrixStructure = new byte[36];
    private byte[] BytePreviewTime = new byte[4];
    private byte[] BytePreviewDuration = new byte[4];
    private byte[] BytePosterTime = new byte[4];
    private byte[] ByteSelectionTime = new byte[4];
    private byte[] ByteSelectionDuration = new byte[4];
    private byte[] ByteCurrentTime = new byte[4];
    private byte[] ByteNextTrackID = new byte[4];

    private int Version;
    private Date CreationTime;
    private Date ModificationTime;
    private long Duration;
    private float PreferredRate;
    private float PreferredVolume;
    private float[][] MatrixStructure;
    private long PreviewTime;
    private long PreviewDuration;
    private long PosterTime;
    private long SelectionTime;
    private long SelectionDuration;
    private long CurrentTime;
    private long NextTrackID;

    public mvhd(InputStream S) {
        try {
            S.read(ByteVersion);
            S.read(ByteFlags);
            S.read(ByteCreationTime);
            S.read(ByteModificationTime);
            S.read(ByteTimeScale);
            S.read(ByteDuration);
            S.read(BytePreferredRate);
            S.read(BytePreferredVolume);
            // skipByteped 10 byte because it is reserved for apple
            S.skip(10);
            S.read(ByteMatrixStructure);
            S.read(BytePreviewTime);
            S.read(BytePreviewDuration);
            S.read(BytePosterTime);
            S.read(ByteSelectionTime);
            S.read(ByteSelectionDuration);
            S.read(ByteCurrentTime);
            S.read(ByteNextTrackID);
            initValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initValues() {
        globalVariables.setTIMESCALE(converter.arrayByteToUnsignedLong(ByteTimeScale));
        Version = (ByteVersion[0] & 0xFF);
        CreationTime = converter.arrayByteToDate(ByteCreationTime);
        ModificationTime = converter.arrayByteToDate(ByteModificationTime);
        Duration = converter.ToTimeScale(ByteDuration);
        PreferredRate = converter.arrayByteToUnsignedFixedPoint(BytePreferredRate);
        PreferredVolume = converter.arrayByteToUnsignedFixedPoint(BytePreferredVolume);
        MatrixStructure = converter.toTransformationMatrix(ByteMatrixStructure);
        PreviewTime = converter.arrayByteToUnsignedLong(BytePreviewTime);
        PreviewDuration = converter.ToTimeScale(BytePreviewDuration);
        PosterTime = converter.arrayByteToUnsignedLong(BytePosterTime);
        SelectionTime = converter.arrayByteToUnsignedLong(ByteSelectionTime);
        SelectionDuration = converter.ToTimeScale(ByteSelectionDuration);
        CurrentTime = converter.arrayByteToUnsignedLong(ByteCurrentTime);
        NextTrackID = converter.arrayByteToUnsignedLong(ByteNextTrackID);
    }

    public void printMvhd() {
        Log.logType("MVHD");
        Log.logElement("Version", Version);
        Log.logElement("Creation time", CreationTime);
        Log.logElement("Modification time", ModificationTime);
        Log.logElement("Duration", Duration);
        Log.logElement("Preferred rate", PreferredRate);
        Log.logElement("Preferred Volume", PreferredVolume);
        Log.LogMatrix("Transformation matrix", MatrixStructure);
        Log.logElement("Preview time", PreviewTime);
        Log.logElement("Preview duration", PreviewDuration);
        Log.logElement("Poster time", PosterTime);
        Log.logElement("Selection time", SelectionTime);
        Log.logElement("Selection Duration", SelectionDuration);
        Log.logElement("Current time", CurrentTime);
        Log.logElement("Next track ID", NextTrackID);
        Log.line();
    }

    public Date getCreationTime() {
        return CreationTime;
    }

    public long getCurrentTime() {
        return CurrentTime;
    }

    public int getVersion() {
        return Version;
    }

    public Date getModificationTime() {
        return ModificationTime;
    }

    public long getDuration() {
        return Duration;
    }

    public float getPreferredRate() {
        return PreferredRate;
    }

    public float getPreferredVolume() {
        return PreferredVolume;
    }

    public float[][] getMatrixStructure() {
        return MatrixStructure;
    }

    public long getPreviewDuration() {
        return PreviewDuration;
    }

    public long getPreviewTime() {
        return PreviewTime;
    }

    public long getPosterTime() {
        return PosterTime;
    }

    public long getSelectionDuration() {
        return SelectionDuration;
    }

    public long getSelectionTime() {
        return SelectionTime;
    }

    public long getNextTrackID() {
        return NextTrackID;
    }

}
