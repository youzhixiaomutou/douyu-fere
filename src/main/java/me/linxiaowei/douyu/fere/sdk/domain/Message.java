package me.linxiaowei.douyu.fere.sdk.domain;

import me.linxiaowei.douyu.fere.sdk.domain.data.AbstractData;
import me.linxiaowei.douyu.fere.sdk.util.ConverseUtil;

/**
 * 斗鱼弹幕协议封装POJO
 *
 * @author Lin
 * @date 2018/12/11
 */
public class Message {

    /**
     * 消息长度：4字节小端整数，表示整条消息（包括自身）长度（字符数）
     * 消息出现两遍，二者相同
     */
    private int length;

    /**
     * 消息类型：2字节小端整数，表示消息类型。
     * 取值如下：
     * 689：客户端发送给弹幕服务器的文本格式数据
     * 690：弹幕服务器发送给客户端的文本格式数据
     */
    private short type;

    /**
     * 加密字段：暂时未用，默认为0
     */
    private final static byte ENCRYPTED = 0;

    /**
     * 保留字段：暂时未用，默认为0
     */
    private final static byte REMAINED = 0;

    /**
     * 数据部分
     */
    private AbstractData data;

    public Message(short type, AbstractData data) {
        this.length = N_INT + N_SHORT + 2 * N_BYTE + data.toString().length() + 1;
        this.type = type;
        this.data = data;
    }

    private static final int SRC_POS = 0;
    private static final int N_INT = 4;
    private static final int N_SHORT = 2;
    private static final int N_BYTE = 1;

    public byte[] getBytes() {
        byte[] lengthBytes = ConverseUtil.intToBytesLittle(length);
        byte[] typeBytes = ConverseUtil.shortToBytesLittle(type);
        byte[] encryptedByte = new byte[]{ENCRYPTED};
        byte[] remainedByte = new byte[]{REMAINED};
        byte[] dataByte = data.toString().getBytes();
        byte[] result = new byte[N_INT + length];
        System.arraycopy(lengthBytes, SRC_POS, result, SRC_POS, N_INT);
        System.arraycopy(lengthBytes, SRC_POS, result, SRC_POS + N_INT, N_INT);
        System.arraycopy(typeBytes, SRC_POS, result, SRC_POS + 2 * N_INT, N_SHORT);
        System.arraycopy(encryptedByte, SRC_POS, result, SRC_POS + 2 * N_INT + N_SHORT, N_BYTE);
        System.arraycopy(remainedByte, SRC_POS, result, SRC_POS + 2 * N_INT + N_SHORT + N_BYTE, N_BYTE);
        System.arraycopy(dataByte, SRC_POS, result, SRC_POS + 2 * N_INT + N_SHORT + 2 * N_BYTE, dataByte.length);
        return result;
    }

}
