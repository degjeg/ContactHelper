package com.witness.utils.common;

/**
 * Created by danger on 15/3/29.
 */

import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;



public final class FileUtil {
    public static String getTempFileName(String extName) {
        if (TextUtils.isEmpty(extName))
            return String.valueOf((new Date()).getTime());

        if (!extName.startsWith("."))
            extName = ("." + extName);
        return String.valueOf((new Date()).getTime()) + extName;
    }

    public static void copyFile(File src, File dst) throws Exception {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dst);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024 * 4];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * @param maxSize : -1���������
     */
    public static byte[] readFile(String filePath, int maxSize)
            throws Exception {
        File file = new File(filePath);
        byte[] buffer = null;
        InputStream ios = null;
        try {
            if ((maxSize != -1 && file.length() > maxSize)
                    || (file.length() == 0)) {
                throw new Exception(String.format(
                        "file length is invalid(%d): %s", file.length(),
                        filePath));
            }

            buffer = new byte[(int) file.length()];
            ios = new FileInputStream(file);
            if (ios.read(buffer) == -1) {
                throw new Exception("EOF reached: " + filePath);
            }
        } finally {
            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }

        return buffer;
    }

    public static void writeFile(String filePath, byte[] b) throws Exception {
        OutputStream output = null;
        try {
            output = new BufferedOutputStream(new FileOutputStream(filePath));
            output.write(b);
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (IOException e) {
            }
        }
    }

    public static void appendFile(String filePath, byte[] b) {
        OutputStream output = null;
        try {
            if (filePath == null || filePath.equalsIgnoreCase("") || b == null) {
                return;
            }

            File f = new File(filePath);
            File parent = f.getParentFile();
            if (!parent.exists()) {
                if (!parent.mkdirs()) {
                    return;
                }
            }
            output = new BufferedOutputStream(new FileOutputStream(filePath, true));
            output.write(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (IOException e) {
            }
        }
    }

    public static boolean renameTo(String from, String to) {

        if (from == null || to == null) {
            return false;
        }

        File f1 = new File(from);
        File f2 = new File(to);

        return f1.renameTo(f2);
    }


    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    public static String combinePath(String... paths) {
        File file = new File(paths[0]);

        for (int i = 1; i < paths.length; i++) {
            file = new File(file, paths[i]);
        }

        return file.getPath();
    }

    public static String getFileNameFromPath(String filePath) {
        File f = new File(filePath);
        return f.getName();
    }

    public static String getDirFromPath(String filePath) {
        File f = new File(filePath);
        return f.getParentFile().getAbsolutePath();
    }

    public static long getFileLength(String filePath) {
        File file = new File(filePath);
        return file.length();
    }

    public static String formatFileLength(String filePath) {
        double d1 = 1024 * 1024;
        double d2 = 1024 * 1024 * 1024;
        long len = getFileLength(filePath);
        if (len < 1024)
            return String.format("%dBytes", len);
        else if (len < 1024 * 1024)
            return String.format("%dKB", len / 1024);
        else if (len < 1024 * 1024 * 1024)
            return String.format("%.2fMB", Double.valueOf(len) / d1);
        else
            return String.format("%.2fGB", Double.valueOf(len) / d2);
    }

    public static boolean exists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 删除文件 or 文件夹
     * */
    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }
}

