package com.tiyi.tyapp.dao;

import com.tianyi.util.PaginationData;
import com.tiyi.tyapp.dao.core.MyBatisRepository;
import com.tiyi.tyapp.model.Demo;
import com.tiyi.tyapp.model.DemoExample;
import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface DemoMapper {
    public int countByExample(DemoExample example);

    public int deleteByPrimaryKey(Integer id);

    public int insert(Demo record);

    public int insertSelective(Demo record);

    public List<Demo> selectByExample(DemoExample example);

    public Demo selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Demo record);

    public int updateByPrimaryKey(Demo record);

	/** add by code generator	2014-10-22*/
	public int countByMap(Map<String, Object> map);
	public List<Demo> selectByMap(Map<String, Object> map);

	
	
	
}
