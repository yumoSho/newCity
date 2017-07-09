package com.Newcity.lib.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by tong on 2017/3/28.
 */
public class PropertiesUtils {
    public static Properties getProperties4Resource(String fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertiesUtils.class.getResourceAsStream(fileName));
        return properties;
    }
}
