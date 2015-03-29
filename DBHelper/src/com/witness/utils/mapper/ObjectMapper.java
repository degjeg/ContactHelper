package com.witness.utils.mapper;

/**
 * Created by Danger on 2014/11/18.
 * 字符串解析为对象
 */
public interface ObjectMapper {
    public Object parseObjString(Object obj, String data);

    public Object parseObjObj(Object obj, Object data);

    public Object parseClsString(Class<?> cls, String data);

    public Object parseClsObj(Class<?> cls, Object data);
}
