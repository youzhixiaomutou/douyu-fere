package me.linxiaowei.douyu.fere.sdk.domain.data.client;

import me.linxiaowei.douyu.fere.sdk.domain.data.AbstractData;

public class JoinGroupData extends AbstractData {

    private final static String COLUMN_TYPE = "type";
    private final static String COLUMN_RID = "rid";
    private final static String COLUMN_GID = "gid";

    public JoinGroupData(int rId, int gId) {
        dataObject.put(COLUMN_TYPE, ClientDataTypeEnum.JOINGROUP.getString());
        dataObject.put(COLUMN_RID, String.valueOf(rId));
        dataObject.put(COLUMN_GID, String.valueOf(gId));
    }

}
