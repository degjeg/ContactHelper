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
package com.witness.utils.db.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Title TAPrimaryKey 主键配置
 * @Package com.ta.util.db.annotation
 * @Description 不配置的时候默认找类的id或_id字段作为主键，column不配置的是默认为字段名，如果不设置主键，自动生成ID
 * @author Danger
 * @date 2013-1-20
 * @version V1.0
 */
@Target(
{ METHOD, FIELD })
@Retention(RUNTIME)
public @interface DBPrimaryKey
{
	/**
	 * 设置主键名
	 * 
	 * @return
	 */
	public String name() default "";

	/**
	 * 字段默认值
	 * 
	 * @return
	 */
	public String defaultValue() default "";

	/**
	 * 是否自动自增
	 * 
	 * @return
	 */
	boolean autoIncrement() default false;
}