package com.Newcity.libs.mybatis;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.Newcity.libs.sql.ISqlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class SimpleDaoSpringImpl extends SqlSessionDaoSupport implements ISqlDao {

	private static final Logger logger = LoggerFactory.getLogger(SimpleDaoSpringImpl.class);

	@Override
	public <E> E get(String selectId, Object params) {
		SqlSession session = getSqlSession();
		return session.selectOne(selectId, params);
	}

	@Override
	public <E> List<E> query(String selectId, Object params) {
		SqlSession session = getSqlSession();
		return session.selectList(selectId, params);
	}

	@Override
	public <E> List<E> query(String selectId, Object params,int start,int limit) {
		RowBounds rowBound = new RowBounds(start, limit);
		SqlSession session = getSqlSession();
		return session.selectList(selectId, params, rowBound );
	}


	@Override
	public <E, T> Map<E, T> getMap(String selectId, String key, Object params) {
		SqlSession session = getSqlSession();
		return session.selectMap(selectId, params, key);
	}

	@Override
	public int add(String insertId, Object params) {
		SqlSession session = getSqlSession();
		return session.insert(insertId, params);
	}

	@Override
	public int add(String insertId, Object params, boolean autoCommit) {
		SqlSession session = getSqlSession();
		try {
			session.getConnection().setAutoCommit(autoCommit);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return session.insert(insertId, params);
	}


	@Override
	public int update(String updateId, Object params) {
		SqlSession session = getSqlSession();
		return session.update(updateId, params);
	}

	@Override
	public int update(String updateId, Object params,boolean autoCommit) {
		SqlSession session = getSqlSession();
		try {
			session.getConnection().setAutoCommit(autoCommit);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return session.update(updateId, params);
	}

	@Override
	public int delete(String deleteId, Object params) {
		SqlSession session = getSqlSession();
		return session.delete(deleteId, params);
	}

	@Override
	public int delete(String deleteId, Object params, boolean autoCommit) {
		SqlSession session = getSqlSession();
		try {
			session.getConnection().setAutoCommit(autoCommit);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return session.delete(deleteId, params);
	}

	@Override
	public int save(String insertId, Object params) {
		SqlSession session = getSqlSession();
		return session.insert(insertId, params);
	}

	@Override
	public int save(String insertId, Object params, boolean autoCommit) {
		SqlSession session = getSqlSession();
		try {
			session.getConnection().setAutoCommit(autoCommit);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return session.insert(insertId, params);
	}

}
