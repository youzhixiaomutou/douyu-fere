package me.linxiaowei.douyu.fere.sdk.util;

/**
 * @author Lin
 * @date 2018-11-15
 */
public class ConstantUtil {

    private static final String SERVER_IP = "openbarrage.douyutv.com";
    private static final int SERVER_PORT = 8601;

    /**
     * 获取连接斗鱼弹幕服务器的地址
     *
     * @return 斗鱼弹幕服务器地址
     */
    public static String SERVER_IP() {
        return SERVER_IP;
    }

    public static int SERVER_PORT() {
        return SERVER_PORT;
    }

}
