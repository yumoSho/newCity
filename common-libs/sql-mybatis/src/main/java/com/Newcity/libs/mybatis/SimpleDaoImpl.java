package com.Newcity.libs.mybatis;


import com.Newcity.libs.sql.ISqlDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by dev on 2017/4/5.
 */
public class SimpleDaoImpl implements ISqlDao {

    protected SqlSessionFactory sessionFactory;

    @Override
    public <E> E get(String selectId, Object params){
        SqlSession sqlSession = null;
        try{
            sqlSession = sessionFactory.openSession();
            E result = sqlSession.selectOne(selectId,params);
            return result;
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }

    @Override
    public <E> List<E> query(String selectId, Object params) {
        SqlSession session = null;
        try{
            session = sessionFactory.openSession();
            List<E> list= session.selectList(selectId, params);
            session.commit(true);
            return list;
        }finally {
            if(session != null){
                session.close();
            }
        }

    }

    @Override
    public <E> List<E> query(String selectId, Object params, int start, int limit) {
        return null;
    }

    @Override
    public <E, T> Map<E, T> getMap(String selectId, String key, Object params) {
        SqlSession session = null;
        try{
            session = sessionFactory.openSession();
            Map<E,T> map = session.selectMap(selectId, params, key);
            session.commit(true);
            return map;
        }finally {
            if(session != null){
                session.close();
            }
        }

    }

    @Override
    public int add(String insertId, Object params) {
        SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            int result = session.insert(insertId, params);
            session.commit(true);
            return result;
        }finally {
            if(session != null){
                session.close();
            }
        }

    }

    @Override
    public int add(String insertId, Object params, boolean autoCommit) {
        SqlSession session = null;
        try {
            session = sessionFactory.openSession(autoCommit);
            return session.insert(insertId, params);
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public int update(String updateId, Object params) {
        SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            int result = session.update(updateId, params);
            session.commit(true);
            return result;
        }finally {
            if(session != null){
                session.close();
            }
        }

    }

    @Override
    public int update(String updateId, Object params, boolean autoCommit) {
        SqlSession session = null;
        try {
            session = sessionFactory.openSession(autoCommit);
            return session.update(updateId, params);
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public int delete(String deleteId, Object params) {
        SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            int result = session.delete(deleteId, params);
            session.commit(true);
            return result;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public int delete(String deleteId, Object params,boolean autoCommit) {
        SqlSession session = null;
        try {
            session = sessionFactory.openSession(autoCommit);
            return session.delete(deleteId, params);
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public int save(String insertId, Object params) {
        return add(insertId, params);
    }

    @Override
    public int save(String insertId, Object params,boolean autoCommit) {
        return add(insertId, params, autoCommit);
    }

    @Resource
    public void setSessionFactory(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
