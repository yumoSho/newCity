package com.Newcity.base.service;

import com.Newcity.libs.logger.ILogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by dev on 2017/5/3.
 */
public class BaseService implements ILogger{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 手动回滚事务
     */
    public void rollback(){
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }


    public void info(String message){
        logger.info(" Service  info  "+message);
    }
    public void debug(String message){
        logger.debug(" Service debug "+message);
    }
    public void error(String message){
        logger.error(" Service error "+message);
    }
    public void warn(String message){
        logger.warn(" Service warn "+message);
    }


}
