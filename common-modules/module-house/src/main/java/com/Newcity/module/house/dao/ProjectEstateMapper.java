package com.Newcity.module.house.dao;

import com.Newcity.libs.sql.ISqlDao;
import com.Newcity.module.house.entity.ProjectEstateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectEstateMapper {

    private final String NAMESPACE = "com.Newcity.module.business.dao.ProjectEstateMapper.";
    @Autowired
    private ISqlDao sqlDao;

    public int insert(ProjectEstateEntity record){
        return sqlDao.add(NAMESPACE + "insert",record);
    }

    public int insertSelective(ProjectEstateEntity record){
        return sqlDao.add(NAMESPACE + "insertSelective",record);
    }

    public ProjectEstateEntity selectByPrimaryKey(String floorId){
        return sqlDao.get(NAMESPACE + "selectByPrimaryKey",floorId);
    }

    public int updateByPrimaryKeySelective(ProjectEstateEntity record){
        return sqlDao.get(NAMESPACE + "updateByPrimaryKeySelective",record);
    }

    public int updateByPrimaryKey(ProjectEstateEntity record){
        return sqlDao.get(NAMESPACE + "updateByPrimaryKey",record);
    }


}