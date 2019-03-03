package me.linxiaowei.douyu.fere.sdk.domain.data.client;

import me.linxiaowei.douyu.fere.sdk.domain.data.AbstractData;

public class MrklData extends AbstractData {

    private final static String COLUMN_TYPE = "type";

    public MrklData() {
        dataObject.put(COLUMN_TYPE, ClientDataTypeEnum.MRKL.getString());
    }

}
