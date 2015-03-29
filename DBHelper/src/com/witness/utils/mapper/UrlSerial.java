package com.witness.utils.mapper;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2014/11/18.
 * 基本功能，实际功能在JSONMapper中实现
 */
public class UrlSerial extends BaseMapper implements ObjectSerial {
    public UrlSerial() {
    }

    @Override
    public String toString(Object obj) {
        return toString(obj, null);
    }

    public String toString(Object obj, Class<?> superCls) {

        if (obj == null) {
            return null;
        }
        Class<?> cls = obj.getClass();
        StringBuilder sb = new StringBuilder();
        do {
            Field[] fields = cls.getDeclaredFields();
            try {
                for (Field filed : fields) {
                    if (isTransient(filed)) {
                        continue;
                    }

                    Class<?> clazz = filed.getType();//
                    String name = getName(filed);
                    filed.setAccessible(true);
                    Object value = filed.get(obj);

                    if (value != null) {
                        sb.append(name);
                        sb.append("=");
                        sb.append(URLEncoder.encode("" + value, "utf-8"));
                        sb.append("&");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (superCls == null || TextUtils.equals(cls.getName(), superCls.getName())) {
                break;
            }

            cls = cls.getSuperclass();
        } while (cls != null && !TextUtils.equals(cls.getName(), Object.class.getName()));

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
