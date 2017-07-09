package com.Newcity.lib.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * xml工具类
 */
public class XMLUtils {

    /**
     * 获取Document对象
     * @param inputStream
     * @return
     */
    public static Document getDocument(InputStream inputStream){
        SAXReader saxReader = new SAXReader();
        Document document = null ;
        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
