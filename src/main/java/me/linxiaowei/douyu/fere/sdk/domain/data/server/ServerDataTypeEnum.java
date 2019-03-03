package me.linxiaowei.douyu.fere.sdk.domain.data.server;

public enum ServerDataTypeEnum {

    /**
     * 登录响应消息
     */
    LOGINRES("loginres"),
    /**
     * 弹幕消息
     */
    CHATMSG("chatmsg"),
    /**
     * 领取在线鱼丸暴击消息
     */
    ONLINEGIFT("onlinegift"),
    /**
     * 赠送礼物消息
     */
    DGB("dgb"),
    /**
     * 用户进房通知
     */
    UENTER("uenter"),
    /**
     * 用户赠送酬勤通知消息
     */
    BC_BUY_DESERVE("bc_buy_deserve"),
    /**
     * 房间开关播信息
     */
    RSS("rss");

    private String string;

    ServerDataTypeEnum(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
