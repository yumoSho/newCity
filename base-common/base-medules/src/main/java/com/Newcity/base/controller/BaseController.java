package com.Newcity.base.controller;

import com.Newcity.libs.logger.ILogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dev on 2017/5/3.
 */
public class BaseController implements ILogger {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void info(String message){
        logger.info(" Controller  info  "+message);
    }
    public void debug(String message){
        logger.debug(" Controller debug "+message);
    }
    public void error(String message){
        logger.error(" Controller error "+message);
    }
    public void warn(String message){
        logger.warn(" Controller warn "+message);
    }
}
