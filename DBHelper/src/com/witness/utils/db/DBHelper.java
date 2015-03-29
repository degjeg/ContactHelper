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
package com.witness.utils.db;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Title TADBHelper
 * @Package com.ta.util.db
 * @Description 管理数据库的创建和版本更新
 * @author Danger
 * @date 2013-1-20
 * @version V1.0
 */
public class DBHelper extends SQLiteOpenHelper
{
    public static final boolean logOn = true;
	/**
	 * 数据库更新监听器
	 */
	private ESQLiteDatabase.TADBUpdateListener mTadbUpdateListener;

	/**
	 * 构造函数
	 * 
	 * @param context
	 *            上下文
	 * @param name
	 *            数据库名字
	 * @param factory
	 *            可选的数据库游标工厂类，当查询(query)被提交时，该对象会被调用来实例化一个游标
	 * @param version
	 *            数据库版本
	 */
	public DBHelper(Context context, String name, CursorFactory factory,
                    int version)
	{
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造函数
	 * 
	 * @param context
	 *            上下文
	 * @param name
	 *            数据库名字
	 * @param factory
	 *            可选的数据库游标工厂类，当查询(query)被提交时，该对象会被调用来实例化一个游标
	 * @param version
	 *            数据库版本
	 * @param tadbUpdateListener
	 *            数据库更新监听器
	 */
	public DBHelper(Context context, String name, CursorFactory factory,
                    int version, ESQLiteDatabase.TADBUpdateListener tadbUpdateListener)
	{
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		this.mTadbUpdateListener = tadbUpdateListener;
	}

	/**
	 * 设置数据库更新监听器
	 * 
	 * @param
	 *            数据库更新监听器
	 */
	public void setOndbUpdateListener(ESQLiteDatabase.TADBUpdateListener tadbUpdateListener)
	{
		this.mTadbUpdateListener = tadbUpdateListener;
	}

	public void onCreate(android.database.sqlite.SQLiteDatabase db)
	{

	}

	public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion)
	{
		if (mTadbUpdateListener != null)
		{
			mTadbUpdateListener.onUpgrade(db, oldVersion, newVersion);
		}
	}

}
