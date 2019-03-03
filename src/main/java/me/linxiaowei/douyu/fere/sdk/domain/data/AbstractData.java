package me.linxiaowei.douyu.fere.sdk.domain.data;

import java.util.LinkedHashMap;

/**
 * 消息中数据部分封装基类pojo
 *
 * @author Lin
 * @date 2018/12/11
 */
public abstract class AbstractData {

    /**
     * 数据项键 key 和值 value 间采用 '@=' 分割
     */
    private final static String DATA_OBJECT_SEPARATOR = "@=";
    /**
     * 数据项间采用 '/' 分割
     */
    private final static String DATA_OBJECT_SUFFIX = "/";
    /**
     * 数据的结尾必须为'\0'
     */
    //private final static byte DATA_SUFFIX = '\0';

    /**
     * 数据部分的数据具体内容 key-value 封装
     */
    protected LinkedHashMap<String, String> dataObject = new LinkedHashMap<>();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String column : dataObject.keySet()) {
            result.append(column)
                .append(DATA_OBJECT_SEPARATOR)
                .append(dataObject.get(column))
                .append(DATA_OBJECT_SUFFIX);
        }
        //result.append(DATA_SUFFIX);
        return result.toString();
    }

}
