package com.Newcity.base.dao;

import com.Newcity.libs.logger.ILogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dev on 2017/5/3.
 */
public class BaseDao implements ILogger{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void info(String message){
        logger.info(" Dao  info  "+message);
    }
    public void debug(String message){
        logger.debug(" Dao debug "+message);
    }
    public void error(String message){
        logger.error(" Dao error "+message);
    }
    public void warn(String message){
        logger.warn(" Dao warn "+message);
    }
}
