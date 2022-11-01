package atomType;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.Track;

import util.converter;
import util.log;

//unfinised
public class elst implements atom {
    private class editEntrie {
        public long TrackDuration;
        public long MediaTime;
        public float MediaRate;

        public editEntrie(long trackduration, long mediatime, float mediarate) {
            this.TrackDuration = trackduration;
            this.MediaTime = mediatime;
            this.MediaRate = mediarate;
        }
    }

    private long Size;
    private byte[] ByteVersion = new byte[1];
    private byte[] ByteFlag = new byte[3];
    private byte[] ByteNumberOfEntries = new byte[4];
    private byte[][][] ByteEditList;

    private int Version;
    private long NumberOfEntries;
    private editEntrie[] EditList;

    @Override
    public long getSize() {
        return Size;
    }

    @Override
    public void constractAtom(InputStream S, long size) {
        this.Size = size;
        ByteEditList = new byte[(int) (Size - 16) / 12][3][4]; // Size - 8 - 1 - 3 - 4
        try {
            S.read(ByteVersion);
            S.read(ByteFlag);
            S.read(ByteNumberOfEntries);
            for (int i = 0; i < ByteEditList.length; i++) {
                for (int j = 0; j < 3; j++) {
                    S.read(ByteEditList[i][j]);
                }
            }
            initValues();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printData() {
        log.logType("ELST");
        log.logElement("Version", Version);
        log.logElement("Number of entries", NumberOfEntries);
        log.logElement("Edit list table");
        for (editEntrie e : EditList) {
            System.out.println("[" + e.TrackDuration + ", " + e.MediaTime + ", " + e.MediaRate + "]");
        }
        log.line();
    }

    private void initValues() {
        Version = (ByteVersion[0] & 0xFF);
        NumberOfEntries = converter.arrayByteToUnsignedLong(ByteNumberOfEntries);
        EditList = new editEntrie[(int) NumberOfEntries];
        for (int i = 0; i < NumberOfEntries; i++) {
            EditList[i] = new editEntrie(
                    converter.ToTimeScale(ByteEditList[i][0]),
                    converter.arrayByteToUnsignedLong(ByteEditList[i][1]),
                    converter.arrayByteToUnsignedFixedPoint(ByteEditList[i][2]));
        }
        printData();
    }
}
