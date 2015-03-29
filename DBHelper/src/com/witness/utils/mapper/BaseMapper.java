package com.witness.utils.mapper;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2014/11/18.
 * 基本功能，实际功能在JSONMapper中实现
 */
public abstract class BaseMapper {
    protected boolean isTransient(Field field) {
        return field.getAnnotation(Transparent.class) != null;
    }


    protected String getName(Field field) {
        Name name = field.getAnnotation(Name.class);
        if (name != null && !TextUtils.isEmpty(name.value())) {
            return name.value();
        }

        return field.getName();
    }

    protected Class<?> getActualType(Field field) {
        ParameterizedType type = (ParameterizedType) field.getGenericType();
        String typeStr = type + "";

        Pattern pattern = Pattern.compile(".*<(.*)>.*");
        Matcher matcher = pattern.matcher(typeStr);
        Class<?> cls = null;
        if (matcher.matches()) {
            try {
                cls = Class.forName(matcher.group(1));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return cls;
    }
}
