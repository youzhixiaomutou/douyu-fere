package me.linxiaowei.douyu.fere.sdk.util;

public class ConverseUtil {

    /**
     * 以小端模式将int转成byte[]
     *
     * @param value
     * @return
     */
    public static byte[] intToBytesLittle(int value) {
        byte[] src = new byte[4];
        src[3] = (byte) ((value >> 24) & 0xFF);
        src[2] = (byte) ((value >> 16) & 0xFF);
        src[1] = (byte) ((value >> 8) & 0xFF);
        src[0] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * 以小端模式将byte[]转成int
     */
    public static int bytesToIntLittle(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF)
                | ((src[offset + 1] & 0xFF) << 8)
                | ((src[offset + 2] & 0xFF) << 16)
                | ((src[offset + 3] & 0xFF) << 24));
        return value;
    }

    /**
     * 以小端模式将short转成byte[]
     *
     * @param value
     * @return
     */
    public static byte[] shortToBytesLittle(short value) {
        byte[] src = new byte[2];
        src[1] = (byte) ((value >> 8) & 0xFF);
        src[0] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * 以小端模式将byte[]转成short
     */
    public static short bytesToShortLittle(byte[] src, int offset) {
        short value;
        value = (short) ((src[offset] & 0xFF)
                | (src[offset + 1] & 0xFF) << 8);
        return value;
    }

}
