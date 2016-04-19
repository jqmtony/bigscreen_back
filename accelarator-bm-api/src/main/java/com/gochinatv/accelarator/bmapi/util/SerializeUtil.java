package com.gochinatv.accelarator.bmapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class SerializeUtil {

    private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    public static byte[] serialize(Object object) throws Exception {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            baos.close();
            oos.close();
            return bytes;
        } catch (Exception e) {
            logger.error("serialize object[" + object + "] error", e);
            throw new Exception("serialize  error" + e.getMessage());
        }
    }

    public static Object unserialize(byte[] bytes) throws Exception {
        if (bytes == null) {
            return null;
        }
        Object object = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            object = ois.readObject();
            bais.close();
            ois.close();
            return object;
        } catch (Exception e) {
            logger.error("unserialize  error", e);
            throw new Exception("unserialize  error" + e.getMessage());
        }
    }

    /**
     * =================== serializeList===========================
     *
     * @param list
     * @return
     * @throws Exception
     */
    public static byte[] serializeList(List list) throws Exception {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            for (Object object : list)
                oos.writeObject(object);
            oos.writeObject(null);////解决EOF的关键，加入一个空的对象
            byte[] bytes = baos.toByteArray();
            baos.close();
            oos.close();
            return bytes;
        } catch (Exception e) {
            logger.error("serializeList list  error", e);
            throw new Exception("serializeList list  error" + e.getMessage());
        }
    }

    public static List unserializeList(byte[] bytes) throws Exception {
        List list = new ArrayList();
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object object = null;
            while ((object = ois.readObject()) != null) {
                list.add(object);
            }
            bais.close();
            ois.close();
        } catch (Exception e) {
            logger.error("unserialize  error", e);
            throw new Exception("unserialize  error" + e.getMessage());
        }
        finally {
            return list;
        }
    }


}
