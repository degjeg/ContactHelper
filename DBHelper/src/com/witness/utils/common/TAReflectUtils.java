/*
 * Copyright (C) 2013  WhiteCat Danger (www.thinkandroid.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.witness.utils.common;





import com.witness.utils.annotation.Field;
import com.witness.utils.annotation.Transparent;

import java.util.Date;

/**
 * @Title TAReflectUtils
 * @Package com.ta.common
 * @Description 反射的一些工具
 * @author Danger
 * @date 2013-1-20
 * @version V1.0
 */
public class TAReflectUtils
{
	/**
	 * 检测实体属性是否已经被标注为 不被识别
	 * 
	 * @param field
	 *            字段
	 * @return
	 */
	public static boolean isTransient(java.lang.reflect.Field field)
	{
		return field.getAnnotation(Transparent.class) != null;
	}

	/**
	 * 是否为基本的数据类型
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isBaseDateType(java.lang.reflect.Field field)
	{
		Class<?> clazz = field.getType();
		return clazz.equals(String.class) || clazz.equals(Integer.class)
				|| clazz.equals(Byte.class) || clazz.equals(Long.class)
				|| clazz.equals(Double.class) || clazz.equals(Float.class)
				|| clazz.equals(Character.class) || clazz.equals(Short.class)
				|| clazz.equals(Boolean.class) || clazz.equals(Date.class)
				|| clazz.equals(Date.class)
				|| clazz.equals(java.sql.Date.class) || clazz.isPrimitive();
	}

	/**
	 * 获得配置名
	 * 
	 * @param field
	 * @return
	 */
	public static String getFieldName(java.lang.reflect.Field field)
	{
		Field column = field.getAnnotation(Field.class);
		if (column != null && column.name().trim().length() != 0)
		{
			return column.name();
		}
		return field.getName();
	}
}
