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
package com.witness.utils.exception;

/**
 * @Title TANoSuchNameLayoutException
 * @package com.ta.exception
 * @Description TANoSuchNameLayoutException是当没有找到相应名字布局，抛出此异常！
 * @author Danger
 * @date 2013-1-7 下午
 * @version V1.0
 */
public class TANoSuchNameLayoutException extends TAException
{
	private static final long serialVersionUID = 2780151262388197741L;

	public TANoSuchNameLayoutException()
	{
		super();
	}

	public TANoSuchNameLayoutException(String detailMessage)
	{
		super(detailMessage);

	}
}
