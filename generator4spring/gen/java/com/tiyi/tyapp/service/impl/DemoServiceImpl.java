/**
 * 
 */
package com.tiyi.tyapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyi.util.PaginationData;
import com.tiyi.tyapp.dao.DemoMapper;
import com.tiyi.tyapp.model.Demo;
import com.tiyi.tyapp.service.DemoService;

/**
 * 业务层接口实现类
 * 
 * @author apple
 * 
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {
	
	@Resource(name="demoMapper")
	private DemoMapper demoMapper;
	

	
	@Override
	public void add(Demo demo, String usrName) {
		
		demoMapper.insert(demo);
	}

	
	@Override
	public void update(Demo demo, String usrName) {

		demoMapper.updateByPrimaryKey(demo);

	}

	
	@Override
	@Transactional
	public void delete(Integer[] ids, String usrName) {
		for (int i = 0; i < ids.length; i++) {
			// TODO	check rule
			demoMapper.deleteByPrimaryKey(ids[i]);
		}

	}

	
	@Override
	public int findCount(Demo demo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//if(StringUtil.isEmpty(demo.getName()) == false)
		//	map.put("name", demo.getName());
		int count = demoMapper.countByExample(map);
		return count;
	}

	
	@Override
	public List<Demo> findPageList(Demo demo, PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//if(StringUtil.isEmpty(demo.getName()) == false)
		//	map.put("name", demo.getName());
			
		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		map.put("orderByClause", page.getOrder());
		List<Demo> list = demoMapper.selectByExample(map);
		
		return list;
	}

	
	@Override
	public Demo getByKey(Integer id) {
		Demo demo = demoMapper.selectByPrimaryKey(id);
		return demo;
	}
	
	

}
