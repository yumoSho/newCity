package com.Newcity.libs.logger;

/**
 * Created by dev on 2017/5/3.
 */
public interface ILogger {

    void info(String message);
    void debug(String message);
    void error(String message);
    void warn(String message);
}
