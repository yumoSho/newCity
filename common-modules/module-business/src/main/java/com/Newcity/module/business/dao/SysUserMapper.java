package com.Newcity.module.business.dao;

import com.Newcity.libs.dmo.vo.Page;
import com.Newcity.libs.sql.ISqlDao;
import com.Newcity.module.business.entity.SysUserEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SysUserMapper {

    private final String NAMESPACE = "com.Newcity.module.business.dao.SysUserMapper.";

    @Resource
    private ISqlDao sqlDao;


    public int insert(SysUserEntity record){
        return sqlDao.add(NAMESPACE+"insert",record);
    }

    public SysUserEntity selectByPrimaryKey(String id){
        return sqlDao.get(NAMESPACE + "selectByPrimaryKey",id);
    }

    public List<SysUserEntity> queryInfo(Page page){
        return sqlDao.query(NAMESPACE+"queryInfo",page);
    }

    public Long queryCount(Page page){
        return sqlDao.get(NAMESPACE+"queryCount",page);
    }

    public Long getDepartmentByCount(String departmentId){
        return sqlDao.get(NAMESPACE+"getDepartmentByCount",departmentId);
    }

    public SysUserEntity queryInfoByLogin(SysUserEntity sysUserMarket){
        return sqlDao.get(NAMESPACE+"queryInfoByLogin",sysUserMarket);
    }

    public Integer isExistLoginName(String loginName){
        return sqlDao.get(NAMESPACE+"isExistLoginName",loginName);
    }

    public SysUserEntity getInfo(String Id){
        return sqlDao.get(NAMESPACE+"getInfo",Id);
    }

    public Integer updateByPrimaryKeySelective(SysUserEntity entity){
        return sqlDao.update(NAMESPACE+"updateByPrimaryKeySelective",entity);
    }
}