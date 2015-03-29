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
package com.witness.utils.extend;

import com.witness.utils.common.ResourceUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Danger
 * @version V1.0
 * @Title TimestampUtil
 * @Package com.ta.util
 * @Description 时间戳操作类分享的类
 * @date 2013-1-22 下午 14:57
 */
public class TimestampUtils {
    private static long day = 7;

    /**
     * 获得当前时间戳
     *
     * @return
     */
    public static String getTimestamp() {
        String unixTimeGMT;
        try {
            long unixTime = System.currentTimeMillis();
            unixTimeGMT = unixTime + "";
        } catch (Exception e) {
            // TODO: handle exception
            unixTimeGMT = "";
        }
        return unixTimeGMT;

    }

    /**
     * 获得当前时间戳
     *
     * @return
     */
    public static long getIntTimestamp() {
        long unixTimeGMT = 0;
        try {
            unixTimeGMT = System.currentTimeMillis();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return unixTimeGMT;

    }

    /**
     * 返回时间戳间隔
     *
     * @return
     */
    public static boolean compareTimestamp(long currentTimestap,
                                           long oldTimestap) {
        Boolean isExceed = false;
        if (gapTimestamp(currentTimestap, oldTimestap) > 86400 * day) {
            isExceed = true;
        }
        return isExceed;
    }

    public static long gapTimestamp(long currentTimestap, long oldTimestap) {
        return (currentTimestap - oldTimestap);
    }

    /**
     * 对时间戳格式进行格式化，保证时间戳长度为13位
     *
     * @param timestamp 时间戳
     * @return 返回为13位的时间戳
     */
    public static String formatTimestamp(String timestamp) {
        if (timestamp == null || "".equals(timestamp)) {
            return "";
        }
        String tempTimeStamp = timestamp + "00000000000000";
        StringBuffer stringBuffer = new StringBuffer(tempTimeStamp);
        return tempTimeStamp = stringBuffer.substring(0, 13);
    }

    /**
     * 根据 timestamp 生成各类时间状态串
     *
     * @param timestamp 距1970 00:00:00 GMT的秒数
     * @param format    格式
     * @return 时间状态串(如：刚刚5分钟前)
     */
    public static String getTimeState1(String timestamp, String format) {
//        <string name="format_just_now">刚刚</string>
//        <string name="format_1min">分钟前</string>
//        <string name="format_today">今天 HH:mm</string>
//        <string name="format_yesterday">昨天 HH:mm</string>
//        <string name="format_md">M月d日 HH:mm:ss</string>
//        <string name="format_ymd">yyyy年M月d日 HH:mm:ss</string>

        if (timestamp == null || "".equals(timestamp)) {
            return "";
        }

        try {
            timestamp = formatTimestamp(timestamp);
            long _timestamp = Long.parseLong(timestamp);
            if (System.currentTimeMillis() - _timestamp < 1 * 60 * 1000) {/*1分钟内:刚刚*/
                return ResourceUtil.getString("format_just_now");
            } else if (System.currentTimeMillis() - _timestamp < 60 * 60 * 1000) {/*1小时内：分钟前*/
                return ((System.currentTimeMillis() - _timestamp) / 1000 / 60)
                        + ResourceUtil.getString("format_1min");
            } else {
                Calendar now = Calendar.getInstance();
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(_timestamp);
                if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                        && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                        && c.get(Calendar.DATE) == now.get(Calendar.DATE)) {/*1天内：小时前*/
                    return ((System.currentTimeMillis() - _timestamp) / 1000 / 60 / 60)
                            + ResourceUtil.getString("format_today");
                } else if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {/*1天内：月-日*/
                    SimpleDateFormat sdf = new SimpleDateFormat(ResourceUtil.getString("format_md"));
                    return sdf.format(c.getTime());
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat(ResourceUtil.getString("format_ymd"));
                    return sdf.format(c.getTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
