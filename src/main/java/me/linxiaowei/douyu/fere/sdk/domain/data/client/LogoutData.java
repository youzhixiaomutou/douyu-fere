package me.linxiaowei.douyu.fere.sdk.domain.data.client;

import me.linxiaowei.douyu.fere.sdk.domain.data.AbstractData;

public class LogoutData extends AbstractData {

    private final static String COLUMN_TYPE = "type";

    public LogoutData() {
        dataObject.put(COLUMN_TYPE, ClientDataTypeEnum.LOGOUT.getString());
    }

}
