package me.linxiaowei.douyu.fere.sdk.domain.data.client;

import me.linxiaowei.douyu.fere.sdk.domain.data.AbstractData;

public class KeepliveData extends AbstractData {

    private final static String COLUMN_TYPE = "type";
    private final static String COLUMN_TICK = "tick";

    public KeepliveData() {
        dataObject.put(COLUMN_TYPE, ClientDataTypeEnum.KEEP_LIVE.getString());
        dataObject.put(COLUMN_TICK, String.valueOf(System.currentTimeMillis()));
    }

}
