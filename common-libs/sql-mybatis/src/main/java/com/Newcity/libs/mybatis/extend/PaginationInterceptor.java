package com.Newcity.libs.mybatis.extend;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;


@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})

public class PaginationInterceptor implements Interceptor{

 

    private final static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
   

    @Override

    public Object intercept(Invocation invocation) throws Throwable {

       StatementHandler statementHandler = (StatementHandler)invocation.getTarget();

       BoundSql boundSql = statementHandler.getBoundSql();

       MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,DEFAULT_OBJECT_FACTORY,
               DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTOR_FACTORY);

       RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");

       if(rowBounds ==null|| rowBounds == RowBounds.DEFAULT){

           return invocation.proceed();

       }

       Configuration configuration = (Configuration)metaStatementHandler.getValue("delegate.configuration");

       Dialect.Type databaseType  =null;

       try{

           databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());

       } catch(Exception e){

           //ignore

       }

       if(databaseType ==null){

           throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : "+ configuration.getVariables().getProperty("dialect"));

       }

       Dialect dialect =null;

       switch(databaseType){

           case MYSQL:

              dialect =new MySql5Dialect();

             

       }

      

       String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");

       metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()) );

       metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET );

       metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT );

       if(log.isDebugEnabled()){

           log.debug("生成分页SQL : "+ boundSql.getSql());

       }

       return invocation.proceed();

    }

 

    @Override

    public Object plugin(Object target) {

       return Plugin.wrap(target, this);

    }

 

    @Override

    public void setProperties(Properties properties) {

    }

 

}
