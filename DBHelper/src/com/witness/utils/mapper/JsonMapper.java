package com.witness.utils.mapper;



import com.witness.utils.common.JSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danger on 14/11/17.
 * 将Json字符串解析为一个对象
 */
public class JsonMapper extends BaseMapper implements ObjectMapper {


    @Override
    public Object parseObjString(Object obj, String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            parseObjObj(obj, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Object parseObjObj(Object obj, Object data) {
        Field[] fields = obj.getClass().getDeclaredFields();
        JSONObject json = (JSONObject) data;

        try {
            for (Field filed : fields) {
                if (isTransient(filed)) {
                    continue;
                }

                Class<?> clazz = filed.getType();//
                String name = getName(filed);

                if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
                    filed.set(obj, (byte) JSONUtils.getInt(json, name, 0));
                } else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
                    filed.set(obj, (short) JSONUtils.getInt(json, name, 0));
                } else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
                    filed.set(obj, JSONUtils.getInt(json, name, 0));
                } else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
                    filed.set(obj, JSONUtils.getLong(json, name, 0));
                } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
                    filed.set(obj, (float) JSONUtils.getDouble(json, name, 0));
                } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
                    filed.set(obj, JSONUtils.getDouble(json, name, 0));
                } else if (clazz.equals(Character.class)) {
                    //                filed.set(obj, jsonObject.get(name));
                } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
                    filed.set(obj, JSONUtils.getBoolean(json, name, false));
                } else if (clazz.equals(String.class)) {
                    filed.set(obj, JSONUtils.getString(json, name, ""));
                } else if (clazz.equals(List.class) || clazz.equals(ArrayList.class)) {
                    JSONArray jsonArray = JSONUtils.getJSONArray(json, name, null);
                    if (jsonArray != null) {
                        ArrayList list = new ArrayList();
                        Class<?> itemCls = getActualType(filed);
                        filed.set(obj, list);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jObj1 = jsonArray.getJSONObject(i);
                            Object objListItem = parseClsObj(itemCls, jObj1);
                            list.add(objListItem);
                        }
                    }
                } else {
                    JSONObject jNode = JSONUtils.getJSONObject(json, name, null);
                    if (jNode != null) {
                        Object value = parseClsObj(clazz, jNode);
                        filed.set(obj, value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public Object parseClsString(Class<?> cls, String json) {
        Object obj = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            obj = parseClsObj(cls, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Object parseClsObj(Class<?> cls, Object json) {
        Object obj = null;

        try {
            Constructor constructor = cls.getConstructor();
            obj = constructor.newInstance();
            parseObjObj(obj, json);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return obj;
    }


}
