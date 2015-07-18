package com.tiyi.tyapp.service;

import java.util.List;

import com.tianyi.util.PaginationData;
import com.tiyi.tyapp.model.Demo;

/**
 * 业务层接口
 * 
 * @author apple
 * 
 */
public interface DemoService {
	
	
	public void add(Demo demo, String usrName);
	
	
	public Demo getByKey(Integer demoId);
	
	
	public void update(Demo demo, String usrName);
	
	
	public void delete(Integer[] demoIds, String usrName);
	
	
	public int findCount(Demo queryDemo);
	
	
	public List<Demo> findPageList(Demo queryDemo, PaginationData page);
	

}
