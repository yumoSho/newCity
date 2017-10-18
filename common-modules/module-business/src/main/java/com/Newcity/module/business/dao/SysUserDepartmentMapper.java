package com.Newcity.module.business.dao;

import com.Newcity.libs.dmo.vo.Page;
import com.Newcity.libs.sql.ISqlDao;
import com.Newcity.module.business.entity.SysUserDepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SysUserDepartmentMapper {

    private final String NAMESPACE = "com.Newcity.module.business.dao.SysUserDepartmentMapper.";

    @Autowired
    private ISqlDao sqlDao;



    public int insert(SysUserDepartmentEntity record){
        return sqlDao.add(NAMESPACE + "insert",record);
    }

    public SysUserDepartmentEntity selectByPrimaryKey(String departmentId){
        return sqlDao.get(NAMESPACE + "selectByPrimaryKey",departmentId);
    }

    public int updateByPrimaryKeySelective(SysUserDepartmentEntity record){
        return sqlDao.update(NAMESPACE + "updateByPrimaryKeySelective",record);
    }

    public List<SysUserDepartmentEntity> queryInfo(Page page){
        return sqlDao.query(NAMESPACE + "queryInfo",page);
    }

    public Long queryCount(Page page){
        return sqlDao.get(NAMESPACE + "queryCount",page);
    }

    public int deleteInfo(Map<String,Object> map){
        return sqlDao.update(NAMESPACE+"deleteInfo",map);
    }

    /**
     * 获取个别值
     * @return
     */
    public List<SysUserDepartmentEntity> getDepartmentName(){
        return sqlDao.query(NAMESPACE+"getDepartmentName",null);
    }
}