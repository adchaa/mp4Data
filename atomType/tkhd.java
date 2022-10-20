package atomType;

import java.io.InputStream;

import util.Log;
import util.converter;
import java.io.IOException;
import java.util.Date;

public class tkhd {
    private byte[] ByteVersion = new byte[1];
    // TODO : understand what is flag and add it to print
    private byte[] ByteFlags = new byte[3];
    private byte[] ByteCreationTime = new byte[4];
    private byte[] ByteModificationTime = new byte[4];
    private byte[] ByteTrackID = new byte[4];
    private byte[] ByteDuration = new byte[4];
    private byte[] ByteLayer = new byte[2];
    // TODO : understand what is alternategroup and add it to print
    private byte[] ByteAlternateGroup = new byte[2];
    // TODO : volume print 0.0 it should be 1.0 i think because video have no audio
    private byte[] ByteVolume = new byte[2];
    // TODO : understand what is MatrixStructure and add it to print
    private byte[] ByteMatrixStructure = new byte[36];
    private byte[] ByteWidth = new byte[4];
    private byte[] ByteHeight = new byte[4];

    // add flag alternategroup and matrix
    private int Version;
    private Date CreationTime;
    private Date ModificationTime;
    private long TrackID;
    private long Duration;
    private long Layer;
    private float Volume;
    private float Width;
    private float Height;

    public void printTkhd() {
        Log.logType("TKHD");
        Log.logElement("Version", Version);
        Log.logElement("Creation Time", CreationTime);
        Log.logElement("Modification Time", ModificationTime);
        Log.logElement("Track ID", TrackID);
        Log.logElement("Duration", Duration);
        Log.logElement("Layer", Layer);
        Log.logElement("Volume", Volume);
        Log.logElement("Width", Width);
        Log.logElement("Height", Height);
        Log.line();
    }

    private void initValues() {
        Version = (ByteVersion[0] & 0xFF);
        CreationTime = converter.arrayByteToDate(ByteCreationTime);
        ModificationTime = converter.arrayByteToDate(ByteModificationTime);
        TrackID = converter.arrayByteToUnsignedLong(ByteTrackID);
        Duration = converter.ToTimeScale(ByteDuration);
        Volume = converter.arrayByteToFloat(ByteVolume);
        Height = converter.arrayByteToUnsignedFixedPoint(ByteHeight);
        Width = converter.arrayByteToUnsignedFixedPoint(ByteWidth);
    }

    public tkhd(InputStream S) {
        // S.skip is used to skip bytes that is reseverd for apple
        try {
            S.read(ByteVersion);
            S.read(ByteFlags);
            S.read(ByteCreationTime);
            S.read(ByteModificationTime);
            S.read(ByteTrackID);
            S.skip(4);
            S.read(ByteDuration);
            S.skip(8);
            S.read(ByteLayer);
            S.read(ByteAlternateGroup);
            S.read(ByteVolume);
            S.skip(2);
            S.read(ByteMatrixStructure);
            S.read(ByteWidth);
            S.read(ByteHeight);
            initValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getVersion() {
        return Version;
    }

    public Date getCreationTime() {
        return CreationTime;
    }

    public Date getModificationTime() {
        return ModificationTime;
    }

    public long getTrackID() {
        return TrackID;
    }

    public long getDuration() {
        return Duration;
    }

    public float getVolume() {
        return Volume;
    }

    public long getLayer() {
        return Layer;
    }

    public float getHeight() {
        return Height;
    }

    public float getWidth() {
        return Width;
    }
}
