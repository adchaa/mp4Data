package atomType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import util.*;

public class mvhd implements atom {
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

    @Override
    public atom constractAtom(InputStream S, long size) {
        try {
            S.read(ByteVersion);
            S.read(ByteFlags);
            S.read(ByteCreationTime);
            S.read(ByteModificationTime);
            S.read(ByteTimeScale);
            S.read(ByteDuration);
            S.read(BytePreferredRate);
            S.read(BytePreferredVolume);
            // 10 bytes reserved for apple
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
        return this;
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
        printData();
    }

    public void printData() {
        log.logType("MVHD");
        log.logElement("Version", Version);
        log.logElement("Creation time", CreationTime);
        log.logElement("Modification time", ModificationTime);
        log.logElement("Duration", Duration);
        log.logElement("Preferred rate", PreferredRate);
        log.logElement("Preferred Volume", PreferredVolume);
        log.LogMatrix("Transformation matrix", MatrixStructure);
        log.logElement("Preview time", PreviewTime);
        log.logElement("Preview duration", PreviewDuration);
        log.logElement("Poster time", PosterTime);
        log.logElement("Selection time", SelectionTime);
        log.logElement("Selection Duration", SelectionDuration);
        log.logElement("Current time", CurrentTime);
        log.logElement("Next track ID", NextTrackID);
        log.line();
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
