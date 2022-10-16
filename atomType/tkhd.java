package atomType;

import java.io.InputStream;

import util.Log;
import util.converter;
import java.io.IOException;
import java.util.Date;

public class tkhd {
    private byte[] Version = new byte[1];
    // TODO : understand what is flag and add it to print
    private byte[] Flags = new byte[3];
    private byte[] CreationTime = new byte[4];
    private byte[] ModificationTime = new byte[4];
    private byte[] TrackID = new byte[4];
    private byte[] Duration = new byte[4];
    private byte[] Layer = new byte[2];
    // TODO : understand what is alternategroup and add it to print
    private byte[] AlternateGroup = new byte[2];
    // TODO : volume print 0.0 it should be 1.0 i think because video have no audio
    private byte[] Volume = new byte[2];
    // TODO : understand what is MatrixStructure and add it to print
    private byte[] MatrixStructure = new byte[36];
    private byte[] Width = new byte[4];
    private byte[] Height = new byte[4];

    public void printTkhd() {
        Log.logType("TKHD");
        Log.logElement("Version", getVersion());
        Log.logElement("Creation Time", getCreationTime());
        Log.logElement("Modification Time", getModificationTime());
        Log.logElement("Track ID", getTrackID());
        Log.logElement("Duration", getDuration());
        Log.logElement("Layer", getLayer());
        Log.logElement("Volume", getVolume());
        Log.logElement("Width", getWidth());
        Log.logElement("Height", getHeight());
        Log.line();
    }

    public tkhd(InputStream S) {
        // S.skip is used to skip bytes that is reseverd for apple
        try {
            S.read(Version);
            S.read(Flags);
            S.read(CreationTime);
            S.read(ModificationTime);
            S.read(TrackID);
            S.skip(4);
            S.read(Duration);
            S.skip(8);
            S.read(Layer);
            S.read(AlternateGroup);
            S.read(Volume);
            S.skip(2);
            S.read(MatrixStructure);
            S.read(Width);
            S.read(Height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getVersion() {
        return (Version[0] & 0xFF);
    }

    public Date getCreationTime() {
        return converter.arrayByteToDate(CreationTime);
    }

    public Date getModificationTime() {
        return converter.arrayByteToDate(ModificationTime);
    }

    public long getTrackID() {
        return converter.arrayByteToUnsignedLong(TrackID);
    }

    public long getDuration() {
        return converter.ToTimeScale(Duration);
    }

    public float getVolume() {
        return converter.arrayByteToFloat(Volume);
    }

    public long getLayer() {
        return converter.arrayByteToUnsignedLong(Layer);
    }

    public float getHeight() {
        return converter.arrayByteToUnsignedFixedPoint(Height);
    }

    public float getWidth() {
        return converter.arrayByteToUnsignedFixedPoint(Width);
    }
}
