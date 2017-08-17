package com.zero.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * xml工具类
 *
 * @author yezhaoxing
 * @date 2017/7/18
 */
public class XmlUtil {

    private static final Logger LOG = LoggerFactory.getLogger(XmlUtil.class);

    /**
     * 将对象直接转换成String类型的 XML输出
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
        return sw.toString();
    }

    /**
     * 将对象根据路径转换成xml文件
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            // 创建输出流
            FileWriter fw = null;
            try {
                fw = new FileWriter(path);
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * 将String类型的xml转换成对象
     */
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
        return xmlObject;
    }

    /**
     * 将file类型的xml转换成对象
     */
    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            try {
                fr = new FileReader(xmlPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            xmlObject = unmarshaller.unmarshal(fr);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
        return xmlObject;
    }

    /**
     * 将输入流转换为JavaBean对象
     */
    public static <T> T convertOSToObject(Class<T> clazz, OutputStream outputStream) {
        T rtn = null;
        ByteArrayOutputStream os = (ByteArrayOutputStream) outputStream;
        try (ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray())) {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller u = context.createUnmarshaller();
            rtn = (T) u.unmarshal(is);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rtn;
    }
}
