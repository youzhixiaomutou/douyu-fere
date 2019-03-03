package me.linxiaowei.douyu.fere.sdk.domain.data.client;

/**
 * 数据部分消息类型值的枚举
 *
 * @author Lin
 * @date 2018/12/11
 */
public enum ClientDataTypeEnum {

    /**
     * 登录请求消息
     */
    LOGIN_REQ("loginreq"),
    /**
     * 客户端旧版心跳消息
     *
     * @Desp
     */
    KEEP_LIVE("keeplive"),
    /**
     * 客户端新版心跳消息
     */
    MRKL("mrkl"),
    /**
     * 入组消息
     */
    JOINGROUP("joingroup"),
    /**
     * 登出消息
     */
    LOGOUT("logout");

    private String string;

    ClientDataTypeEnum(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
