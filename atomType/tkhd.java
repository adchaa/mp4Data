package atomType;

import java.io.InputStream;

import util.converter;

import java.io.IOException;

public class tkhd {
    public byte[] Version = new byte[1];
    // TODO : understand what is flag and add it to printMvhd
    public byte[] Flags = new byte[3];
    public byte[] CreationTime = new byte[4];
    public byte[] ModificationTime = new byte[4];
    public byte[] TrackID = new byte[4];
    public byte[] Duration = new byte[4];
    public byte[] Layer = new byte[2];
    public byte[] AlternateGroup = new byte[2];
    public byte[] Volume = new byte[2];
    public byte[] MatrixStructure = new byte[36];
    public byte[] Width = new byte[4];
    public byte[] Height = new byte[4];

    // S.skip is used to skip bytes that is reseverd for apple
    public tkhd(InputStream S) {
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
}
