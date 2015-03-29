package com.witness.utils.mapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/11/18.
 * 基本功能，实际功能在JSONMapper中实现
 */
public class JsonSerial extends BaseMapper implements ObjectSerial {

    public JsonSerial() {
    }

    @Override
    public String toString(Object obj) {

        JSONObject json = new JSONObject();

        solveNode(obj, json);

        return json.toString();
    }

    private void solveNode(Object obj, JSONObject json) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field filed : fields) {
                if (isTransient(filed)) {
                    continue;
                }

                Class<?> clazz = filed.getType();//
                String name = getName(filed);

                if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
                    json.put(name, filed.get(obj));
                } else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
                    json.put(name, filed.get(obj));
                } else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
                    json.put(name, filed.get(obj));
                } else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
                    json.put(name, filed.get(obj));
                } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
                    json.put(name, filed.get(obj));
                } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
                    json.put(name, filed.get(obj));
                } else if (clazz.equals(Character.class)) {
                    //                filed.set(obj, jsonObject.get(name));
                } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
                    json.put(name, filed.get(obj));
                } else if (clazz.equals(String.class)) {
                    json.put(name, filed.get(obj));
                } else if (clazz.equals(List.class) || clazz.equals(ArrayList.class)) {

                    if (filed.get(obj) != null) {
                        ArrayList list = (ArrayList)filed.get(obj);
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < list.size(); i++) {
                            JSONObject jNode = new JSONObject();
                            solveNode(list.get(i), jNode);
                            jsonArray.put(jNode);
                        }

                        json.put(name, jsonArray);
                    }
                } else {
                    if (filed.get(obj) != null) {
                        JSONObject jNode = new JSONObject();
                        solveNode(filed.get(obj), jNode);
                        json.put(name, jNode);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
