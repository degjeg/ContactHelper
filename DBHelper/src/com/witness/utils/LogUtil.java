package com.witness.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;

import com.witness.utils.common.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class LogUtil {
    //    private static boolean appendTimeStr2Log;

    private static final String LOG_DIR = "/data/net.monkey8.witness/files/log/";
    private static int logLevel = -1;
    private static String LOG_FILE_NAME;

    private static ILogUploadListener logUploadListener;
    private static int limitSize = 50 * 1024;

    public static void setLogLevel(int logLevel) {
        LogUtil.logLevel = logLevel;
    }


    public interface ILogUploadListener {
        public void uploadLog(String file);
    }


    public enum LOG_LEVEL {
        NONE(-1), ASSERT(0), ERROR(1), WARN(2), INFO(3), DEBUG(4), VERBOSE(5), ALL(100);
        public int level;

        LOG_LEVEL(int level) {
            this.level = level;
        }

        public static String getLevelString(Context ctx, int level) {
            Resources res = ctx.getResources();
            int arid = res.getIdentifier("log_level_array", "array", ctx.getPackageName());
            String ar[] = res.getStringArray(arid);


            if (logLevel < 0 || logLevel > LOG_LEVEL.VERBOSE.level) {
                logLevel = LOG_LEVEL.WARN.level;
            }

            return ar[level];
        }
    }

    private static HandlerThread writeLogThread;
    private static Handler handler;

    public static void init(int logLevel, int limit, ILogUploadListener logUploadListener) {
//        LogUtil.logLevel = logLevel;
//
//        if (logLevel < 0 || logLevel > LOG_LEVEL.VERBOSE.level) {
//            logLevel = LOG_LEVEL.WARN.level;
//        }
        LOG_FILE_NAME = Environment.getDataDirectory() + LOG_DIR + "log";
        limitSize = limit;
        LogUtil.logUploadListener = logUploadListener;

        FileUtil.delete(new File(Environment.getDataDirectory() + LOG_DIR));
    }



    public static void prepareThread() {
        if (writeLogThread == null
                || writeLogThread.isInterrupted()
                || !writeLogThread.isAlive()) {

            writeLogThread = new HandlerThread("logThread");
            writeLogThread.start();
            handler = new Handler(writeLogThread.getLooper());
        }

        if (TextUtils.isEmpty(LOG_FILE_NAME)) {
            LOG_FILE_NAME = Environment.getDataDirectory() + LOG_DIR + "log";
        }
    }

    //simplely write log to file
    public static void writeLog(String tag, String log, int level) {
        prepareThread();

        SimpleDateFormat format = new SimpleDateFormat("mm-dd hh:mm:ss.SSS", Locale.getDefault());
        String timeStr = format.format(new Date());

        final String logWithTime = timeStr + " " + tag + " " + log + "\n";

        handler.post(new Runnable() {
            @Override
            public void run() {
                FileUtil.appendFile(LOG_FILE_NAME, logWithTime.getBytes());

                File lFile = new File(LOG_FILE_NAME);

                if (lFile.length() >= limitSize) {
                    forceUpload("log");
                    /*Log.d("logutil", "start upload");
                    String newLogFile = Environment.getDataDirectory() + LOG_DIR + System.currentTimeMillis();

                    if (logUploadListener != null && FileUtil.renameTo(LOG_FILE_NAME, newLogFile)) {
                        logUploadListener.uploadLog(newLogFile);
                    } else {
                        lFile.delete();
                    }*/
                }
            }
        });
    }

    public static void v(String tag, String s) {
        Log.d(tag, s);

        if (logLevel < LOG_LEVEL.VERBOSE.level)
            return;

        writeLog(tag, s, LOG_LEVEL.VERBOSE.level);
    }

    public static void d(String tag, String s) {
        Log.d(tag, s);

        if (logLevel < LOG_LEVEL.DEBUG.level)
            return;

        writeLog(tag, s, LOG_LEVEL.DEBUG.level);
    }

    public static void i(String tag, String s) {
        Log.i(tag, s);

        if (logLevel < LOG_LEVEL.INFO.level)
            return;

        writeLog(tag, s, LOG_LEVEL.INFO.level);
    }

    public static void w(String tag, String s) {
        Log.w(tag, s);

        if (logLevel < LOG_LEVEL.WARN.level)
            return;

        writeLog(tag, s, LOG_LEVEL.WARN.level);
    }

    public static void e(String tag, String s) {
        e(tag, s, null);
    }

    public static void e(String tag, Exception e) {
        e(tag, "", e);
    }

    public static void e(String tag, String s, Throwable e) {
        Log.e(tag, s, e);

        if (logLevel < LOG_LEVEL.ERROR.level)
            return;

        String trace = (e == null) ? "" : Log.getStackTraceString(e);
        writeLog(tag, s + " " + trace, LOG_LEVEL.ERROR.level);
    }


    public static void forceUpload(final String from) {
        prepareThread();

        handler.post(new Runnable() {
            @Override
            public void run() {
                File lFile = new File(LOG_FILE_NAME);
                if (lFile.length() < 1000) {
                    Log.e("LogUtil", "forceUpload " + from + "," + lFile.length());
                    return;
                }

                String newLogFile = Environment.getDataDirectory() + LOG_DIR + System.currentTimeMillis() + ".txt";


                if (logUploadListener != null && FileUtil.renameTo(LOG_FILE_NAME, newLogFile)) {
                    logUploadListener.uploadLog(newLogFile);
                } else {
                    lFile.delete();
                }
            }
        });
    }
}