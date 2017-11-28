package com.singularity.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author architect.bian
 * @date 2017-11-28 12:56 PM
 */

public class MD5Util {

    public static String getMD5(byte[] byteArray) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Throwable e) {
            e.printStackTrace(); // handled
            return "";
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toLowerCase(Locale.ENGLISH);
    }

    public static String getMD5(String string) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Throwable e) {
            e.printStackTrace(); // handled
            return "";
        }
        byte[] md5Bytes = md5.digest(string.getBytes());
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toLowerCase(Locale.ENGLISH);
    }

    /**
     * 获取单个文件的MD5值！
     *
     * @param file 文件
     */
    public static String getMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest ;
        FileInputStream in;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 获取文件夹中文件的MD5值
     *
     * @param file 文件
     * @param listChild ;true递归子目录中的文件
     * @return MD5值
     */
    public static Map<String, String> getDirMD5(File file, boolean listChild) {
        if (!file.isDirectory()) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        String md5;
        File files[] = file.listFiles();
        for (File f : files) {
            if (f.isDirectory() && listChild) {
                //noinspection ConstantConditions
                map.putAll(getDirMD5(f, true));
            } else {
                md5 = getMD5(f);
                if (md5 != null) {
                    map.put(f.getPath(), md5);
                }
            }
        }
        return map;
    }

    public static boolean equals(String v1, String v2) {
        if (v1 != null && v2 != null) {
            v1 = v1.toUpperCase(Locale.ENGLISH);
            v2 = v2.toUpperCase(Locale.ENGLISH);
            return v1.equals(v2);
        }
        return false;
    }

    public static String getDoubleMD5(String string) {
        return getMD5(getMD5(string));
    }

}
