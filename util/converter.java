package util;

import java.util.Arrays;
import java.util.Date;

public class converter {

    public static Date arrayByteToDate(byte[] b) {
        return new Date(1000 * (arrayByteToUnsignedLong(b) - globalVariables.EPOCH_ADJUSTER));
    }

    // never called
    public static float arrayByteToFloat(byte[] b) {
        return Float.intBitsToFloat((int) arrayByteToUnsignedLong(b));
    }

    // info :
    // https://developer.apple.com/library/archive/documentation/QuickTime/QTFF/QTFFChap4/qtff4.html#//apple_ref/doc/uid/TP40000939-CH206-18737
    public static float[][] toTransformationMatrix(byte[] b) {
        if (b.length > 36) {
            log.logError("array length is longer than expected(36). array length = " + b.length, true);
        }
        float[][] output = new float[3][3];
        for (byte i = 0; i < 3; i++) {
            for (byte j = 0; j < 2; j++) {
                output[i][j] = arrayByteToUnsignedFixedPoint(
                        Arrays.copyOfRange(b, ((i * 3) + j) * 4, ((i * 3) + j + 1) * 4));
            }
            output[i][2] = arrayByteToUnsignedFixedPoint2_30(
                    Arrays.copyOfRange(b, ((i * 3) + 2) * 4, ((i * 3) + 3) * 4));
        }
        return output;
    }

    public static float arrayByteToUnsignedFixedPoint2_30(byte[] b) {
        int intPart = b[0] >> 6;
        b[0] = (byte) (b[0] & 0x3F);
        long fracPart = arrayByteToUnsignedLong(b);
        return Float.parseFloat(intPart + "." + fracPart);
    }

    // return float because java doesn't support fixed point numbers
    public static float arrayByteToUnsignedFixedPoint(byte[] b) {
        int center = b.length / 2;
        long intPart = arrayByteToUnsignedLong(Arrays.copyOfRange(b, 0, center));
        long fracPart = arrayByteToUnsignedLong(Arrays.copyOfRange(b, center, b.length));
        return Float.parseFloat(intPart + "." + fracPart);
    }

    public static String byteToHex(byte i) {
        String output = Integer.toHexString((i & 0xff));
        if (output.length() < 2) {
            output = '0' + output;
        }
        return output;
    }

    public static String arrayByteToHex(byte[] b) {
        String output = "";
        for (byte i : b) {
            output = output + byteToHex(i) + ' ';
        }
        return output.substring(0, output.length() - 1);
    }

    public static long arrayByteToUnsignedLong(byte[] b) {
        long x = 0;
        for (byte i : b) {
            x = (x << 8) + (i & 0xFF);
        }
        return x;
    }

    public static String arrayByteToString(byte[] b) {
        String output = "";
        for (byte i : b) {
            output += (char) (i & 0XFF);
        }
        return output;
    }

    public static long ToTimeScale(byte[] b) {
        return arrayByteToUnsignedLong(b) / globalVariables.getTIMESCALE();
    }

}
