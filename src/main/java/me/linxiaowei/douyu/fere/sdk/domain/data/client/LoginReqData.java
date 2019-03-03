package me.linxiaowei.douyu.fere.sdk.domain.data.client;

import me.linxiaowei.douyu.fere.sdk.domain.data.AbstractData;

/**
 * 客户端登录请求封装类
 *
 * @author Lin
 * @date 2018/12/11
 */
public class LoginReqData extends AbstractData {

    private final static String COLUMN_TYPE = "type";
    private final static String COLUMN_ROOM_ID = "roomid";

    public LoginReqData(int roomId) {
        dataObject.put(COLUMN_TYPE, ClientDataTypeEnum.LOGIN_REQ.getString());
        dataObject.put(COLUMN_ROOM_ID, String.valueOf(roomId));
    }

}
