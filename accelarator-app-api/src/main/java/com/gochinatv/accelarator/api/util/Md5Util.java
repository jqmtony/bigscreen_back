package com.gochinatv.accelarator.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

/**
 * Md5工具，用于计算输入的md5
 *
 * @author mahuimin
 * @version v1.0
 *          2013-05-29
 */
public class Md5Util {
    public static String md5(String plainText) {
        String str = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String makeKey(String clazzName, String methondName, TreeMap map) {
        StringBuffer buffer = new StringBuffer();
        if (map == null || map.isEmpty()) {
            return null;
        }
        buffer.append(clazzName + "_" + methondName + "_");
        for (Object key : map.keySet()) {
            buffer.append("_" + key);
        }
        return md5(buffer.toString());
    }

    public static String makeKey(String clazzName, String methondName, Object[] args) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(clazzName + "_" + methondName + "_");
        if (args != null && args.length > 0) {
            for (Object key : args) buffer.append("_" + key);
        }
        return clazzName+"_"+methondName+"_"+md5(buffer.toString());
    }

    public static void main(String[] args) {
        System.out.println(md5(""));
    }
}
